package corruptionhades.injection.misc;

import corruptionhades.injection.net.minecraft.client.MinecraftClient;

/**
 * This class is used to get information about the local player.
 */
public class LocalPlayer {

    private final MinecraftClient mc = new MinecraftClient();

    public Object getPlayer() throws Exception {
        return mc.getPlayer();
    }
}
