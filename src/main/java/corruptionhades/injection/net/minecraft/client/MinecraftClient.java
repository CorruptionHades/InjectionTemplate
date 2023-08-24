package corruptionhades.injection.net.minecraft.client;

import corruptionhades.injection.Wrapper;
import corruptionhades.injection.misc.Classes;

import java.lang.reflect.Method;

/**
 * Wrapper for net.minecraft.client.MinecraftClient.
 * Use My wrapper generator for generating these classes
 * @see Wrapper
 * @see Classes
 * @see corruptionhades.injection.OOPWrapper
 * @see corruptionhades.injection.OOPWrapper#rh
 */
public class MinecraftClient extends Wrapper {

    public MinecraftClient() {
        // Our target class which methods/fields we want to get
        super(Classes.minecraftClient);
    }

    public Object instance() throws Exception {
        return clazz.getMethod("N").invoke(null);
    }

    public void execute(Runnable runnable) throws Exception{
        Class<?> threadExecutor = Classes.threadExecutor;
        Method method = threadExecutor.getMethod("execute", Runnable.class);
        rh.invoke(method, instance(), runnable);
    }

    public Object textRenderer() throws Exception {
        return rh.getField("h", instance());
    }

    public Object getPlayer() throws Exception {
        return rh.getField("t", instance());
    }

    public Object getPlayerObj() {
        try {
            return getPlayer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getWorld() throws Exception {
        return rh.getField("s", instance());
    }

    public Object getInteractionManager() throws Exception {
        return rh.getField("r", instance());
    }

    public Object getInteractionManagerObj() {
        try {
            return getInteractionManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
