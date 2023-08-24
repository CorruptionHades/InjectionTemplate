package corruptionhades.hook;

import corruptionhades.hook.impl.ExampleHook;
import corruptionhades.utils.CodeInjection;
import corruptionhades.utils.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HookManager {

    private final List<Hook> hooks = new ArrayList<>();

    public HookManager() {
        init();
    }

    private void init() {
        // Add your hooks here
        addHook(new ExampleHook());
    }

    public void registerHooks() throws Exception {
        for(Hook hook : hooks) {
            Class<?> hookClass = hook.getClass();
            for (final Method method : hookClass.getDeclaredMethods()) {
                if(isValidMethod(method)) {
                    IMethodHook annotation = method.getAnnotation(IMethodHook.class);
                    if(annotation != null) {
                        String methodName = annotation.method();
                        CodeInjection.Location location = annotation.location();
                        String[] parameters = annotation.params();
                        List<Class<?>> params = new ArrayList<>();
                        for (String parameter : parameters) {

                            if(defaultType(parameter)) {
                                Class<?> type = getType(parameter);
                                if(type != null) params.add(getType(parameter));
                            }
                            else {
                                Class<?> clazz = ReflectionHelper.getClass(parameter);
                                if (clazz != null) {
                                    params.add(clazz);
                                }
                            }
                        }

                        method.setAccessible(true);
                        String code = (String) method.invoke(hook);
                        CodeInjection.injectMethod(hook.getTarget(), methodName, code, location, params.toArray(new Class[0]));
                    }
                }
            }
        }
    }

    private boolean isValidMethod(Method method) {
        return method.isAnnotationPresent(IMethodHook.class) && method.getReturnType() == String.class;
    }

    private boolean defaultType(String s) {
        return "int".equals(s) || "float".equals(s) || "double".equals(s) || "long".equals(s) ||
                "short".equals(s) || "byte".equals(s) || "char".equals(s) || "boolean".equals(s);
    }

    private Class<?> getType(String s) {
        if ("int".equals(s)) {
            return int.class;
        } else if ("float".equals(s)) {
            return float.class;
        } else if ("double".equals(s)) {
            return double.class;
        } else if ("long".equals(s)) {
            return long.class;
        } else if ("short".equals(s)) {
            return short.class;
        } else if ("byte".equals(s)) {
            return byte.class;
        } else if ("char".equals(s)) {
            return char.class;
        } else if ("boolean".equals(s)) {
            return boolean.class;
        }
        return null;
    }


    private void addHook(Hook hook) {
        hooks.add(hook);
    }

    public List<Hook> getHooks() {
        return hooks;
    }
}
