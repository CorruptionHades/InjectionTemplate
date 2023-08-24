package corruptionhades.injection.net.minecraft.client.gui;

import corruptionhades.injection.OOPWrapper;
import corruptionhades.injection.misc.Classes;

import java.lang.reflect.Method;

public class DrawContextWrapper extends OOPWrapper {

    public DrawContextWrapper(Object instance) {
        super(Classes.drawContext, instance);
    }

    public void drawText(Object textRenderer, String text, int x, int y, int color, boolean shadow) throws Exception {
        Method method = clazz.getMethod("a", Classes.textRenderer, String.class, int.class, int.class, int.class, boolean.class);
        method.setAccessible(true);
        method.invoke(instance, textRenderer, text, x, y, color, shadow);
    }
}
