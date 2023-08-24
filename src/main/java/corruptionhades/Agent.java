package corruptionhades;

import corruptionhades.hook.HookManager;
import corruptionhades.module.Module;
import corruptionhades.module.ModuleManager;
import corruptionhades.utils.ServerListener;

import java.lang.instrument.Instrumentation;
import java.net.Socket;

public class Agent {

    public static final int port = 54321;
    public static Instrumentation instrumentation = null;
    public static Socket socket = null;

    public static void agentmain(String args, Instrumentation instrumentation) throws Exception {
        System.out.println("----- Started Java Injection Client -----");
        Agent.instrumentation = instrumentation;

        ModuleManager.init();

        HookManager hookManager = new HookManager();
        hookManager.registerHooks();

        // Connect to the server
        socket = new Socket("localhost", port);
        System.out.println("Connected!");
        new Thread(new ServerListener(socket)).start();

        // OnUpdate thread for modules
        while (!Thread.interrupted()) {
            long startTime = System.currentTimeMillis();

            for (Module module : ModuleManager.getModules()) {
                if (module.isEnabled()) {
                    module.onUpdate();
                }
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = Math.max(0, 50 - elapsedTime); // 1000ms (1 second) / 20 = 50ms

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
