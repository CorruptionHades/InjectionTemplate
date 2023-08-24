package corruptionhades.utils;

import corruptionhades.Agent;
import corruptionhades.module.Module;
import corruptionhades.module.ModuleManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ServerListener implements Runnable {

    private boolean running = true;
    private final InputStream in;

    public ServerListener(Socket socket) throws IOException {
        this.in = socket.getInputStream();
    }

    @Override
    public void run() {
        try {
            while (running) {
                byte[] lengthBytes = new byte[4];
                in.read(lengthBytes);

                int messageLength = ByteBuffer.wrap(lengthBytes).getInt();

                if (messageLength == -1) {
                    break;
                }

                byte[] message = new byte[messageLength];
                in.read(message);

                String response = new String(message);

                if (response.startsWith("toggle")) {
                    String moduleName = response.split(" ")[1];
                    Module module = ModuleManager.getByName(moduleName);
                    if (module == null) {
                        continue;
                    }
                    module.toggle();
                }
            }
        } catch (Exception e) {
            System.out.println("Disconnected!");
            handleDisconnection();
        }
    }

    private void handleDisconnection() {
        int reconnectAttempts = 0;
        boolean connected = false;
        int MAX_RECONNECT_ATTEMPTS = 50;
        while (reconnectAttempts < MAX_RECONNECT_ATTEMPTS) {
            try {
                int RECONNECT_INTERVAL_MS = 1000;
                Thread.sleep(RECONNECT_INTERVAL_MS);
                Socket newSocket = new Socket("localhost", Agent.port); // Replace with your server address and port
                Agent.socket = newSocket;
                ServerListener newListener = new ServerListener(newSocket);
                ServerListener.sendMessage("_M:" + ModuleManager.getModuleString());
                new Thread(newListener).start();
                System.out.println("Successfully reconnected to the server!");
                connected = true;
                break; // Successfully reconnected, so break the loop
            } catch (Exception e) {
                System.out.println("Reconnection attempt failed, retrying...");
                reconnectAttempts++;
            }
        }
        if(!connected) System.out.println("Unable to reconnect to the server after multiple attempts. Exiting...");
    }

    public void stop() {
        running = false;
    }

    public static void sendMessage(String message) throws Exception {
        OutputStream out = Agent.socket.getOutputStream();
        byte[] response = message.getBytes();
        int messageLength = message.length(); // Get the length of the message as the number of characters
        out.write(ByteBuffer.allocate(4).putInt(messageLength).array());
        out.write(response);
        out.flush();
    }
}
