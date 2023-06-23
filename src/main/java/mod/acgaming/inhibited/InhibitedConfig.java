package mod.acgaming.inhibited;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Inhibited.MOD_ID, name = Inhibited.NAME)
public class InhibitedConfig
{
    @Config.Name("Render In Inventory")
    @Config.Comment("If the potion effect should be displayed in the player's inventory")
    public static boolean shouldRenderInv = true;

    @Config.Name("Render In HUD")
    @Config.Comment("If the potion effect should be displayed in the player's ingame HUD")
    public static boolean shouldRenderHUD = true;

    @Mod.EventBusSubscriber(modid = Inhibited.MOD_ID)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(Inhibited.MOD_ID))
            {
                ConfigManager.sync(Inhibited.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}