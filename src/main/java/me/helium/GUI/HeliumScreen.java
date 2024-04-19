package me.helium.GUI;

import me.helium.mods.AutoReconnect;
import me.helium.mods.CreeperQuit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class HeliumScreen extends Screen {

    private final Screen parent;
    private final GameOptions options;

    public HeliumScreen(Screen parent, GameOptions options) {
        super(Text.of("Helium"));
        this.parent = parent;
        this.options = options;
    }

    Text creeperQuitText() {
            return CreeperQuit.creeperQuit ? Text.of("Creeper Quit: §aOn") : Text.of("Creeper Quit: §cOff");
    }

    Text autoReconnectText() {
        return AutoReconnect.autoReconnect ? Text.of("Auto Reconnect: §aOn") : Text.of("Auto Reconnect: §cOff");
    }

    protected void init() {

        this.addDrawableChild(ButtonWidget.builder(creeperQuitText(), (button) -> {
            CreeperQuit.creeperQuit = !CreeperQuit.creeperQuit;
            button.setMessage(creeperQuitText());
        }).dimensions(this.width / 2 - 75, this.height / 2 - 12, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(autoReconnectText(), (button) -> {
            AutoReconnect.autoReconnect = !AutoReconnect.autoReconnect;
            button.setMessage(autoReconnectText());
        }).dimensions(this.width / 2 - 75, this.height / 2 + 12, 150, 20).build());

        //Back
        this.addDrawableChild(this.createButtonBack(Text.of("Back"), () -> this.parent, 4, 4, 100, 20));
    }

    private ButtonWidget createButtonBack(Text text, Supplier<Screen> screenSupplier, int x, int y, int width, int height) {
        return ButtonWidget.builder(text, (button) -> {
            this.client.setScreen((Screen)screenSupplier.get());
        }).dimensions(x, y, width, height).build();
    }
}
