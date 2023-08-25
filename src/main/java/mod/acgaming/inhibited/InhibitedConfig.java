package mod.acgaming.inhibited;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Inhibited.MOD_ID, name = Inhibited.NAME)
public class InhibitedConfig
{
    @Config.Name("Block Breaking List")
    @Config.Comment
        ({
            "List of blocks which is considered when breaking blocks while inhibited",
            "Behavior depends on the list mode",
            "Syntax: modid:block"
        })
    public static String[] blockBreakingList = new String[] {};

    @Config.Name("Block Breaking List Mode")
    @Config.Comment
        ({
            "Whitelist Mode: Blocks which can be broken, others can't",
            "Blacklist Mode: Blocks which can't be broken, others can"
        })
    public static EnumLists blockBreakingListMode = EnumLists.WHITELIST;

    @Config.Name("Block Placing List")
    @Config.Comment
        ({
            "List of blocks which is considered when placing blocks while inhibited",
            "Behavior depends on the list mode",
            "Syntax: modid:block"
        })
    public static String[] blockPlacingList = new String[] {};

    @Config.Name("Block Placing List Mode")
    @Config.Comment
        ({
            "Whitelist Mode: Blocks which can be placed, others can't",
            "Blacklist Mode: Blocks which can't be placed, others can"
        })
    public static EnumLists blockPlacingListMode = EnumLists.WHITELIST;

    @Config.Name("Curable")
    @Config.Comment("If the potion effect should be curable by milk")
    public static boolean isCurable = false;

    @Config.Name("Render In HUD")
    @Config.Comment("If the potion effect should be displayed in the player's ingame HUD")
    public static boolean shouldRenderHUD = true;

    @Config.Name("Render In Inventory")
    @Config.Comment("If the potion effect should be displayed in the player's inventory")
    public static boolean shouldRenderInv = true;

    @Config.Name("Display Status Message")
    @Config.Comment("If an Inhibited status message should be displayed in the action bar")
    public static boolean shouldDisplayMsg = true;

    public enum EnumLists
    {
        WHITELIST,
        BLACKLIST
    }

    @Mod.EventBusSubscriber(modid = Inhibited.MOD_ID)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(Inhibited.MOD_ID))
            {
                ConfigManager.sync(Inhibited.MOD_ID, Config.Type.INSTANCE);
                InhibitedLogic.initBlockLists();
            }
        }
    }
}