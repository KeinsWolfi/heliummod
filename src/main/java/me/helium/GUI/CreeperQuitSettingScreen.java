package me.helium.GUI;

import me.helium.mods.CreeperQuit;
import me.helium.util.gui.ChatFormat;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class CreeperQuitSettingScreen extends Screen {

    private final Screen parent;
    private final GameOptions options;

    protected CreeperQuitSettingScreen(Screen parent, GameOptions options) {
        super(Text.of("Creeper Quit Settings"));
        this.parent = parent;
        this.options = options;
    }

    Text creeperQuitSettingNoAutoReconnectText() {
        return CreeperQuit.noAutoReconnect ? Text.of("No Auto Reconnect: " + ChatFormat.GREEN + "On") : Text.of("No Auto Reconnect: " + ChatFormat.RED + "Off");
    }

    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(creeperQuitSettingNoAutoReconnectText(), (button) -> {
            CreeperQuit.noAutoReconnect= !CreeperQuit.noAutoReconnect;
            button.setMessage(creeperQuitSettingNoAutoReconnectText());
        }).dimensions(this.width / 2 - 75, this.height / 2 - 10, 150, 20).build());

        this.addDrawableChild(this.createButtonBack(Text.of("Back"), () -> this.parent, 4, 4, 100, 20));
    }

    private ButtonWidget createButtonBack(Text text, Supplier<Screen> screenSupplier, int x, int y, int width, int height) {
        return ButtonWidget.builder(text, (button) -> {
            this.client.setScreen((Screen)screenSupplier.get());
        }).dimensions(x, y, width, height).build();
    }

}
