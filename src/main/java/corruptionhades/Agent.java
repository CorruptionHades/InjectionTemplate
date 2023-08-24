package corruptionhades;

import corruptionhades.hook.HookManager;
import corruptionhades.module.Module;
import corruptionhades.module.ModuleManager;

import java.lang.instrument.Instrumentation;
import java.net.Socket;

public class Agent {

    public static Instrumentation instrumentation = null;

    public static void agentmain(String args, Instrumentation instrumentation) throws Exception {
        System.out.println("----- Started Java Injection Client -----");
        Agent.instrumentation = instrumentation;

        ModuleManager.init();

        HookManager hookManager = new HookManager();
        hookManager.registerHooks();

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
