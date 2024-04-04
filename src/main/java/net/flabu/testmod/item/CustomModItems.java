package net.flabu.testmod.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class CustomModItems extends SwordItem {

    private static final int maxAffutage = 100;
    private static final int minAffutage = 0;

    public CustomModItems(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings){
        super(toolMaterial, attackDamage, attackSpeed, settings);

    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int rand = (int)(Math.random() * 6);
        int nbt = stack.getNbt().getInt("testmod.affutage");

        if(rand == 5 && nbt > minAffutage){
            NbtCompound nbtData = new NbtCompound();
            nbtData.putInt("testmod.affutage", (nbt - 1));
            stack.setNbt(nbtData);
        }
        setMultiplier(stack);
        return super.postHit(stack, target, attacker);
    }

    public void setMultiplier(ItemStack stack) {
        float multiplicateur = 1;
        if(stack.getSubNbt("testmod.multiplier") != null) {
            stack.getSubNbt("testmod.multiplier").getFloat("testmod.multiplier");
        }
        int affutage = stack.getNbt().getInt("testmod.affutage");

        if(affutage == 50){
            multiplicateur = 1;
        }
        else if(affutage > 50){
            multiplicateur = /*1.10f*/ 5;
        }
        else{
            multiplicateur = /*0.90f*/ 0.10f;
        }

        NbtCompound nbtData = new NbtCompound();
        nbtData.putFloat("testmod.multiplier", multiplicateur);
        stack.setSubNbt("testmod.multiplier", nbtData);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()){
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();

            BlockState state = context.getWorld().getBlockState(positionClicked);

            if(isValidBlock(state)){
                checkNbt(player);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    public void checkNbt(PlayerEntity player) {
        ItemStack stack = player.getMainHandStack();

        NbtCompound nbtUpdate = new NbtCompound();
        int nbt = stack.getNbt().getInt("testmod.affutage");

        if(nbt > 95) {
            nbtUpdate.putInt("testmod.affutage", (nbt + (maxAffutage - nbt)));
        }
        else if(nbt < maxAffutage) {
            nbtUpdate.putInt("testmod.affutage", (nbt + 5));
        }
        else {
            nbtUpdate.putInt("testmod.affutage", nbt);
        }

        stack.setNbt(nbtUpdate);
        setMultiplier(stack);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        NbtCompound nbtData1 = new NbtCompound();
        NbtCompound nbtData2 = new NbtCompound();
        nbtData1.putInt("testmod.affutage", 50);
        stack.setNbt(nbtData1);
        nbtData2.putFloat("testmod.multiplier", 1);
        stack.setSubNbt("testmod.multiplier", nbtData2);
        super.onCraft(stack, world, player);
    }

    private boolean isValidBlock(BlockState state) {
        return state.isOf(Blocks.COAL_BLOCK);
    }

    public void generateItemModel(ItemModelGenerator modelGenerator) {
        modelGenerator.register(ModItems.CUSTOM_TEST, Models.HANDHELD);
        modelGenerator.register(ModItems.CUSTOM_TEST2, Models.HANDHELD);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Affutage : " + stack.getNbt().getInt("testmod.affutage")).formatted(Formatting.GREEN));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
