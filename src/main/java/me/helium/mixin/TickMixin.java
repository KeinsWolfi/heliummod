package me.helium.mixin;

import me.helium.mods.CreeperQuit;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MinecraftClient.class, priority = 1001)
public class TickMixin {
	@Inject(at = @At("HEAD"), method = "tick")
	private void init(CallbackInfo info) {
		CreeperQuit.onTick();
	}
}