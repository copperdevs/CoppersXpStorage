package com.copperdevs.xpstorage.coppersxpstorage.mixin;

import com.copperdevs.xpstorage.coppersxpstorage.CoppersXpStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
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
        if (!user.isSneaking() || world.isClient)
            return;

        if ((user.experienceLevel + user.experienceProgress) >= CoppersXpStorage.BottleXpConsumption) {

            var serverUser = (ServerPlayerEntity) user;

            serverUser.addExperience((int)(CoppersXpStorage.BottleXpConsumption * -10));

            serverUser.getMainHandStack().decrement(1);

            var inventory = serverUser.getInventory();

            ItemStack bottle = new ItemStack(Items.EXPERIENCE_BOTTLE);

            var wasAdded = inventory.insertStack(bottle);

            if (!wasAdded) {
                serverUser.dropItem(bottle, false);
            }
        }
    }
}
