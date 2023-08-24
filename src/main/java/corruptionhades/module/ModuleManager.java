package corruptionhades.module;

import corruptionhades.setting.Setting;
import corruptionhades.setting.impl.BooleanSetting;
import corruptionhades.setting.impl.ModeSetting;
import corruptionhades.setting.impl.NumberSetting;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private final static List<Module> modules = new ArrayList<>();

    public static void init() throws Exception {
        // Add Modules Here
    }

    private static void add(Module module) {
        modules.add(module);
    }

    public static Module getByName(String name) {
        for (Module module : getModules()) {
            if(module.getName().equals(name)) return module;
        }
        return null;
    }

    public static String getModuleString() {
        StringBuilder modules = new StringBuilder();
        for (Module module : getModules()) {
            String moduleString = module.getName() + "#";
            int count = 0;
            for (Setting setting : module.getSettings()) {
                if(setting instanceof NumberSetting numberSetting) {
                    moduleString += count + "-" + "N?" +
                            setting.getName() + "%" + numberSetting.getName() + "%" +
                            numberSetting.getValue() + "%" + numberSetting.getMin() + "%" + numberSetting.getMax() + "|";
                }
                else if(setting instanceof BooleanSetting booleanSetting) {
                    moduleString += count + "-" + "B?" +
                            setting.getName() + "%" + booleanSetting.getName() + "%" +
                            booleanSetting.isEnabled() + "|";
                }
                else if(setting instanceof ModeSetting modeSetting) {
                    moduleString += count + "-" + "M?" +
                            setting.getName() + "%" + modeSetting.getName() + "%" +
                            modeSetting.getMode() + "%" + modeSetting.getModesString() + "|";
                }
            }

            modules.append(module.getName()).append(",");
        }
        return modules.toString();
    }

    public static List<Module> getModules() {
        return modules;
    }

}
