package com.copperdevs.xpstorage.mixin;

import com.copperdevs.xpstorage.CoppersXpStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlassBottleItem.class)
public class BottleUsed {
    @Inject(at = @At("HEAD"), method = "use")
    private void injected(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (!CoppersXpStorage.getEnabledConfig() || world.isClient || user.isCreative()) return;
        if (!CoppersXpStorage.getSneakingRequiredConfig() && user.isSneaking()) return;
        if (CoppersXpStorage.getSneakingRequiredConfig() && !user.isSneaking()) return;

        if ((user.experienceLevel + user.experienceProgress) >= CoppersXpStorage.getBottleConsumption()) {

            var serverUser = (ServerPlayerEntity) user;

            var xpAdded = (CoppersXpStorage.getBottleConsumption() * -10);
            serverUser.addExperience((int) xpAdded);

            serverUser.getMainHandStack().decrement(1);

            var inventory = serverUser.getInventory();

            ItemStack bottle = new ItemStack(Items.EXPERIENCE_BOTTLE);

            var wasAdded = inventory.insertStack(bottle);

            if (!wasAdded) {
                serverUser.dropItem(bottle, false);
            }

            float f = serverUser.experienceLevel > 30 ? 1.0f : (float) serverUser.experienceLevel / 30.0f;
            serverUser.playSoundToPlayer(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, f * 0.25f, 0.625f);
        }
    }
}
