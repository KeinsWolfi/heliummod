package me.helium.GUI;

import me.helium.mods.AutoReconnect;
import me.helium.mods.CreeperQuit;
import me.helium.util.gui.ChatFormat;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class AutoReconnectSettingScreen extends Screen {

    private final Screen parent;
    private final GameOptions options;

    protected AutoReconnectSettingScreen(Screen parent, GameOptions options) {
        super(Text.of("Auto Reconnect Settings"));
        this.parent = parent;
        this.options = options;
    }

    Text autoReconnectSettingAutoReconnectText() {
        return AutoReconnect.autoEnable ? Text.of("Auto Enable: " + ChatFormat.GREEN + "On") : Text.of("Auto Enable: " + ChatFormat.RED + "Off");
    }

    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(autoReconnectSettingAutoReconnectText(), (button) -> {
            AutoReconnect.autoEnable = !AutoReconnect.autoEnable;
            button.setMessage(autoReconnectSettingAutoReconnectText());
        }).dimensions(this.width / 2 - 75, this.height / 2 - 10, 150, 20).build());

        this.addDrawableChild(this.createButtonBack(Text.of("Back"), () -> this.parent, 4, 4, 100, 20));
    }

    private ButtonWidget createButtonBack(Text text, Supplier<Screen> screenSupplier, int x, int y, int width, int height) {
        return ButtonWidget.builder(text, (button) -> {
            this.client.setScreen((Screen)screenSupplier.get());
        }).dimensions(x, y, width, height).build();
    }

}
