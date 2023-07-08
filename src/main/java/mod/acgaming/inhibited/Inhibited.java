package mod.acgaming.inhibited;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
@Mod(modid = Inhibited.MOD_ID, name = Inhibited.NAME, version = Inhibited.VERSION, acceptedMinecraftVersions = Inhibited.ACCEPTED_VERSIONS)
public class Inhibited
{
    public static final String MOD_ID = "inhibited";
    public static final String NAME = "Inhibited";
    public static final String VERSION = "1.1.0";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final Potion INHIBITED_POTION = new InhibitedPotion().setPotionName("effect." + MOD_ID + "." + MOD_ID).setRegistryName(MOD_ID, MOD_ID);

    @SubscribeEvent
    public static void registerPotion(RegistryEvent.Register<Potion> event)
    {
        event.getRegistry().register(INHIBITED_POTION);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        InhibitedLogic.initBlockLists();
    }
}