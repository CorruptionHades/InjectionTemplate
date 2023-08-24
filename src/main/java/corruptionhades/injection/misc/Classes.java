package corruptionhades.injection.misc;

import corruptionhades.utils.ReflectionHelper;

public class Classes {
    public static Class<?> minecraftClient = ReflectionHelper.getClass("enn");
    public static Class<?> threadExecutor = ReflectionHelper.getClass("bcn");
    public static Class<?> hand = ReflectionHelper.getClass("bdw");
    public static Class<?> inGameHud = ReflectionHelper.getClass("eow");
    public static Class<?> drawContext = ReflectionHelper.getClass("eox");
    public static Class<?> textRenderer = ReflectionHelper.getClass("eov");
    public static Class<?> clientWorld = ReflectionHelper.getClass("few");
}
