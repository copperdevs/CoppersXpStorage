package com.copperdevs.xpstorage.coppersxpstorage.mixin;

import com.copperdevs.xpstorage.coppersxpstorage.CoppersXpStorage;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
    @Inject(at = @At("HEAD"), method = "loadWorld")
    private void injected(CallbackInfo info) {
        CoppersXpStorage.LOGGER.info("world loaded");
    }
}