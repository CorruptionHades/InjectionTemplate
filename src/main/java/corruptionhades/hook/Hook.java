package corruptionhades.hook;

public abstract class Hook {

    private final Class<?> target;

    public Hook(Class<?> target) {
        this.target = target;
    }

    public Class<?> getTarget() {
        return target;
    }
}
