package corruptionhades.injection;

import corruptionhades.utils.ReflectionHelper;

public abstract class Wrapper {

    protected final Class<?> clazz;
    protected final ReflectionHelper rh;

    public Wrapper(Class<?> clazz) {
        this.clazz = clazz;
        rh = new ReflectionHelper(clazz);
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
