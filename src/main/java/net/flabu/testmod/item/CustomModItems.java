package net.flabu.testmod.item;

import net.flabu.testmod.util.InventoryUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class CustomModItems extends SwordItem {

    private static final int maxAffutage = 100;
    private static final int minAffutage = 0;

    public CustomModItems(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()){
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();

            BlockState state = context.getWorld().getBlockState(positionClicked);

            if(isValidBlock(state)){

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

    public void checkNbt(PlayerEntity player) {
        ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.CUSTOM_TEST));

        if(stack.getNbt().isEmpty()){
            NbtCompound nbtData = new NbtCompound();
            nbtData.putInt("testmod.affutage", 50);
            stack.setNbt(nbtData);
        }
        else {
            NbtCompound nbtUpdate = new NbtCompound();
            nbtUpdate.putInt("testmod.affutage", (stack.getNbt().getInt("testmod.affutage") + 5));
            stack.setNbt(nbtUpdate);
        }
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        NbtCompound nbtData = new NbtCompound();
        nbtData.putInt("testmod.affutage", 50);
        stack.setNbt(nbtData);
        super.onCraft(stack, world, player);
    }

    private boolean isValidBlock(BlockState state) {
        return state.isOf(Blocks.COAL_BLOCK);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(!stack.getNbt().isEmpty()) {
            tooltip.add(Text.literal("Affutage : " + stack.getNbt().getInt("testmod.affutage")));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt();
    }
}
