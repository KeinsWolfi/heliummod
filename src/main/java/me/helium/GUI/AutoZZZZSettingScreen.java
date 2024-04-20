package me.helium.GUI;

import me.helium.mods.AutoZZZZ;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class AutoZZZZSettingScreen extends Screen {
    private final Screen parent;
    private final GameOptions options;

    protected AutoZZZZSettingScreen(Screen parent, GameOptions options) {
        super(Text.of("AutoZZZZ Settings"));
        this.parent = parent;
        this.options = options;
    }

    Text autoZZZZSettingRAmmountText(){
        return Text.of("RRRR Ammount: " + AutoZZZZ.rrrAmmount);
    }

    Text autoZZZZSettingZAmmountText(){
        return Text.of("ZZZZ Ammount: " + AutoZZZZ.zzzAmmount);
    }

    protected void init(){

        ButtonWidget buttonRammount = this.addDrawableChild(ButtonWidget.builder(autoZZZZSettingRAmmountText(), (button) -> {
        }).dimensions(this.width / 2 - 75, this.height / 2 - 12, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.of("-"), (button) -> {
            AutoZZZZ.rrrAmmount--;
            if(AutoZZZZ.rrrAmmount < 0) AutoZZZZ.rrrAmmount = 0;
            buttonRammount.setMessage(autoZZZZSettingRAmmountText());
        }).dimensions(this.width / 2 - 95, this.height / 2 - 12, 20, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.of("+"), (button) -> {
            AutoZZZZ.rrrAmmount++;
            buttonRammount.setMessage(autoZZZZSettingRAmmountText());
        }).dimensions(this.width / 2 + 75, this.height / 2 - 12, 20, 20).build());


        ButtonWidget buttonZammount = this.addDrawableChild(ButtonWidget.builder(autoZZZZSettingZAmmountText(), (button) -> {
        }).dimensions(this.width / 2 - 75, this.height / 2 + 12, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.of("-"), (button) -> {
            AutoZZZZ.zzzAmmount--;
            if(AutoZZZZ.zzzAmmount < 0) AutoZZZZ.zzzAmmount = 0;
            buttonZammount.setMessage(autoZZZZSettingZAmmountText());
        }).dimensions(this.width / 2 - 95, this.height / 2 + 12, 20, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.of("+"), (button) -> {
            AutoZZZZ.zzzAmmount++;
            buttonZammount.setMessage(autoZZZZSettingZAmmountText());
        }).dimensions(this.width / 2 + 75, this.height / 2 +12, 20, 20).build());

        this.addDrawableChild(this.createButtonBack(Text.of("Back"), () -> this.parent, 4, 4, 100, 20));
    }

    private ButtonWidget createButtonBack(Text text, Supplier<Screen> screenSupplier, int x, int y, int width, int height) {
        return ButtonWidget.builder(text, (button) -> {
            this.client.setScreen((Screen)screenSupplier.get());
        }).dimensions(x, y, width, height).build();
    }

}
