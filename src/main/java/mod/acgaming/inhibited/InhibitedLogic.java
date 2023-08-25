package mod.acgaming.inhibited;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod.EventBusSubscriber
public class InhibitedLogic
{
    public static List<Block> blockBreakingList = new ArrayList<>();
    public static List<Block> blockPlacingList = new ArrayList<>();

    public static void initBlockLists()
    {
        blockBreakingList.clear();
        blockPlacingList.clear();
        try
        {
            for (String entry : InhibitedConfig.blockBreakingList)
            {
                ResourceLocation resLoc = new ResourceLocation(entry);
                if (ForgeRegistries.BLOCKS.containsKey(resLoc)) blockBreakingList.add(ForgeRegistries.BLOCKS.getValue(resLoc));
            }
            for (String entry : InhibitedConfig.blockPlacingList)
            {
                ResourceLocation resLoc = new ResourceLocation(entry);
                if (ForgeRegistries.BLOCKS.containsKey(resLoc)) blockPlacingList.add(ForgeRegistries.BLOCKS.getValue(resLoc));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static boolean isInhibited(EntityPlayer player)
    {
        if (!player.isCreative())
        {
            for (PotionEffect potionEffect : player.getActivePotionEffects())
            {
                if (potionEffect.getPotion() instanceof InhibitedPotion)
                {
                    player.capabilities.allowEdit = false;
                    return true;
                }
            }
            player.capabilities.allowEdit = true;
        }
        return false;
    }

    @SubscribeEvent
    public static void breakBlock(PlayerInteractEvent.LeftClickBlock event)
    {
        if (!(event.getEntityLiving() instanceof EntityPlayer)) return;

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        if (!isInhibited(player)) return;

        boolean isWhitelist = InhibitedConfig.blockBreakingListMode == InhibitedConfig.EnumLists.WHITELIST;

        if (!blockBreakingList.isEmpty())
        {
            World world = event.getWorld();
            BlockPos blockPos = event.getPos();
            Block block = world.getBlockState(blockPos).getBlock();
            if (blockBreakingList.contains(block) == isWhitelist)
            {
                player.capabilities.allowEdit = true;
                return;
            }
        }
        else if (!isWhitelist)
        {
            player.capabilities.allowEdit = true;
            return;
        }

        player.capabilities.allowEdit = false;

        if (InhibitedConfig.shouldDisplayMsg) player.sendStatusMessage(new TextComponentTranslation("message.inhibited.inhibited"), true);

        event.setUseBlock(Event.Result.DENY);
        event.setUseItem(Event.Result.DENY);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void placeBlock(PlayerInteractEvent.RightClickBlock event)
    {
        if (!(event.getEntityLiving() instanceof EntityPlayer)) return;

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        if (!isInhibited(player)) return;

        boolean isWhitelist = InhibitedConfig.blockPlacingListMode == InhibitedConfig.EnumLists.WHITELIST;

        if (!blockPlacingList.isEmpty())
        {
            Item item = player.getHeldItem(event.getHand()).getItem();
            Block block = Block.getBlockFromItem(item);
            if (blockPlacingList.contains(block) == isWhitelist)
            {
                player.capabilities.allowEdit = true;
                return;
            }
        }
        else if (!isWhitelist)
        {
            player.capabilities.allowEdit = true;
            return;
        }

        player.capabilities.allowEdit = false;

        if (InhibitedConfig.shouldDisplayMsg) player.sendStatusMessage(new TextComponentTranslation("message.inhibited.inhibited"), true);
    }
}
