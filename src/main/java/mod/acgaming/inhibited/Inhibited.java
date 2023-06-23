package mod.acgaming.inhibited;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.GameType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
@Mod(modid = Inhibited.MOD_ID, name = Inhibited.NAME, version = Inhibited.VERSION, acceptedMinecraftVersions = Inhibited.ACCEPTED_VERSIONS)
public class Inhibited
{
    public static final String MOD_ID = "inhibited";
    public static final String NAME = "Inhibited";
    public static final String VERSION = "1.0.0";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final Potion INHIBITED_POTION = new InhibitedPotion().setPotionName("effect." + MOD_ID + "." + MOD_ID).setRegistryName(MOD_ID, MOD_ID);

    @SubscribeEvent
    public static void onRegisterPotion(RegistryEvent.Register<Potion> event)
    {
        event.getRegistry().register(INHIBITED_POTION);
    }

    @SubscribeEvent
    public static void onExpirePotion(PotionEvent.PotionExpiryEvent event)
    {
        if (event.getPotionEffect().getPotion().equals(INHIBITED_POTION)
            && event.getEntityLiving() instanceof EntityPlayer
            && !((EntityPlayer) event.getEntityLiving()).isCreative()
            && EntitySelectors.NOT_SPECTATING.apply(event.getEntityLiving()))
        {
            ((EntityPlayer) event.getEntityLiving()).setGameType(GameType.SURVIVAL);
        }
    }
}