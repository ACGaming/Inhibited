package mod.acgaming.inhibited;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.GameType;

public class InhibitedPotion extends Potion
{
    public InhibitedPotion()
    {
        super(true, 16711680);
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier)
    {
        if (entity instanceof EntityPlayer
            && !((EntityPlayer) entity).isCreative()
            && EntitySelectors.NOT_SPECTATING.apply(entity))
        {
            ((EntityPlayer) entity).setGameType(GameType.ADVENTURE);
        }
    }

    @Override
    public final boolean isReady(int duration, int amplifier)
    {
        return duration > 0;
    }

    @Override
    public boolean shouldRender(PotionEffect effect)
    {
        return InhibitedConfig.shouldRenderInv;
    }

    @Override
    public boolean shouldRenderHUD(PotionEffect effect)
    {
        return InhibitedConfig.shouldRenderHUD;
    }
}