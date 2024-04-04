package net.flabu.testmod.event;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.flabu.testmod.item.CustomModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AttackEntityHandler implements AttackEntityCallback {

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {

        ItemStack stack = player.getStackInHand(hand);

        if(stack.getItem() instanceof CustomModItems && entity instanceof LivingEntity && !world.isClient()) {
            float multiplier = stack.getSubNbt("testmod.multiplier").getFloat("testmod.multiplier");
            float newDamage = (((CustomModItems) stack.getItem()).getAttackDamage() * multiplier);
            ((LivingEntity) entity).damage(entity.getDamageSources().playerAttack(player), newDamage);

            stack.getItem().postHit(stack, (LivingEntity) entity, player);

            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}

