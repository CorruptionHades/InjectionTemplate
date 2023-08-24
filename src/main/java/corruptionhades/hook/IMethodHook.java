package corruptionhades.hook;

import corruptionhades.utils.CodeInjection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IMethodHook {

    String method();
    CodeInjection.Location location();

    /**
     * Use official mapped class names and separate with ; even if its only 1 param
     * @return a String with the params
     */
    String[] params();
}
