package corruptionhades.injection.misc;

import corruptionhades.utils.ReflectionHelper;

public class Classes {
    public static Class<?> minecraftClient = ReflectionHelper.getClass("enn");
    public static Class<?> threadExecutor = ReflectionHelper.getClass("bcn");
    public static Class<?> drawContext = ReflectionHelper.getClass("eox");
    public static Class<?> textRenderer = ReflectionHelper.getClass("eov");
}
