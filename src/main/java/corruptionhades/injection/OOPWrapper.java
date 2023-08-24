package corruptionhades.injection;

public abstract class OOPWrapper extends Wrapper{

    protected final Object instance;

    public OOPWrapper(Class<?> clazz, Object instance) {
        super(clazz);
        this.instance = instance;
    }

    public Object getInstance() {
        return instance;
    }
}
