package mod.acgaming.inhibited;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InhibitedPotion extends Potion
{
    public static final ResourceLocation INHIBITED_ICON = new ResourceLocation(Inhibited.MOD_ID, "textures/gui/inhibited_icon.png");

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

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(PotionEffect effect, Gui gui, int x, int y, float z)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(INHIBITED_ICON);
        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(PotionEffect effect, Gui gui, int x, int y, float z, float alpha)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(INHIBITED_ICON);
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }
}