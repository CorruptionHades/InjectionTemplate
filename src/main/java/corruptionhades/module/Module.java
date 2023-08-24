package corruptionhades.module;

import corruptionhades.injection.misc.LocalPlayer;
import corruptionhades.injection.net.minecraft.client.MinecraftClient;
import corruptionhades.setting.Setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Module {

    private final String name;
    private int key;
    private boolean enabled;
    protected final MinecraftClient mc = new MinecraftClient();
    private final List<Setting> settings = new ArrayList<>();

    public Module(String name) {
        this.name = name;
        key = 0;
    }

    protected void addSettings(Setting...settings) {
        this.settings.addAll(Arrays.asList(settings));
    }

    public void toggle() throws Exception {
        this.enabled = !this.enabled;

        if(enabled) {
            onEnable();
        }
        else {
            onDisable();
        }
    }

    public void setEnabled(boolean toggled) throws Exception {
        this.enabled = toggled;
        if(enabled) {
            onEnable();
        }
        else {
            onDisable();
        }
    }

    public boolean nullCheck() throws Exception {
        return new LocalPlayer().getPlayer() == null || mc.getWorld() == null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void onRender2D(Object renderContext) throws Exception {}

    public void onUpdate() throws Exception{}

    protected void onEnable() throws Exception {}
    protected void onDisable() throws Exception{}

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
