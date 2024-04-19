package me.helium.mixin;

import it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import me.helium.mods.AutoReconnect;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConnectScreen.class)
public class ConnectMixin {
    @Inject(method = "connect(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/network/ServerAddress;Lnet/minecraft/client/network/ServerInfo;)V", at = @At("HEAD"))
    private void tryConnectEvent(MinecraftClient client, ServerAddress address, ServerInfo info, CallbackInfo ci) {
        AutoReconnect.lastConnectedServer = new ObjectObjectImmutablePair<>(address, info);
    }
}
