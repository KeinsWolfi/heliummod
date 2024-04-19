package me.helium.mixin;

import me.helium.Helium9;
import me.helium.mods.CreeperQuit;
import me.helium.util.gui.ChatFormat;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Shadow private int scaledWidth;

    @Inject(at = @At("TAIL"), method = "renderHotbar")
    private void renderHotbarMixin(float tickDelta, DrawContext context, CallbackInfo ci) {
        if(CreeperQuit.creeperNear){
            TextRenderer tr = Helium9.mc.textRenderer;
            context.drawTextWithShadow(tr, ChatFormat.RED + "Creeper near!", scaledWidth / 2 - tr.getWidth("Creeper near!") / 2, 6, -1);
        }
    }

}
