package corruptionhades.hook.impl;

import corruptionhades.hook.Hook;
import corruptionhades.hook.IMethodHook;
import corruptionhades.injection.misc.Classes;
import corruptionhades.utils.CodeInjection;

public class ExampleHook extends Hook {

    public ExampleHook() {
        // Our target class to hook into
        super(Classes.inGameHud);
    }

    // Add a new method hook, with the method name, location, and params in obfuscated class names
    @IMethodHook(method = "a", location = CodeInjection.Location.BEFORE, params = {"eox", "float"})
     /**
     * Method should be a string and return the code in a code block
     * @see corruptionhades.hook.HookListener#onRender2D(Object)
     */
    public String renderHook() {
        return "{ corruptionhades.hook.HookListener.onRender2D($$0); }";
    }
}
