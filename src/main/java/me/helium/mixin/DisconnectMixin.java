package me.helium.mixin;

import me.helium.mods.AutoReconnect;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DisconnectedScreen.class)
public class DisconnectMixin extends Screen {
    protected DisconnectMixin(Text title) {
        super(title);
    }

    @Override
    public void tick() {
        AutoReconnect.reconnect();
    }
}
