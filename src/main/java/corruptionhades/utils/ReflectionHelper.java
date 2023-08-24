package corruptionhades.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionHelper {

    private final Class<?> clazz;

    public ReflectionHelper(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getField(String name, Object instance) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field.get(instance);
    }

    public void setField(String name, Object instance, Object toSetTo) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        field.set(instance, toSetTo);
    }

    public Object invokeGetter(Method method, Object instance, Object...args) throws Exception {
        method.setAccessible(true);
        return method.invoke(instance, args);
    }

    public void invoke(Method method, Object instance, Object...args) throws Exception {
        method.setAccessible(true);
        method.invoke(instance, args);
    }

    public Object invokeReturn(Method method, Object instance, Object...args) throws Exception {
        method.setAccessible(true);
        return method.invoke(instance, args);
    }

    public static Class<?> getClass(String className) {
        try {
            return ClassLoader.getSystemClassLoader().loadClass(className);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getField(Class<?> clazz, String name, Object instance) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field.get(instance);
    }
}
