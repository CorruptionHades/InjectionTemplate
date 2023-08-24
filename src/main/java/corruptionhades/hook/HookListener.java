package corruptionhades.hook;

import corruptionhades.injection.net.minecraft.client.MinecraftClient;
import corruptionhades.injection.net.minecraft.client.gui.DrawContextWrapper;
import corruptionhades.module.Module;
import corruptionhades.module.ModuleManager;

public class HookListener {

    // Our hook method that gets called from the example hook
    public static void onRender2D(Object renderContext) {
        try {
            MinecraftClient mc = new MinecraftClient();
            DrawContextWrapper drawContextWrapper = new DrawContextWrapper(renderContext);
            drawContextWrapper.drawText(mc.textRenderer(), "Venimi", 10, 10, -1, false);

            for (Module module : ModuleManager.getModules()) {
                if (module.isEnabled()) {
                    module.onRender2D(renderContext);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
