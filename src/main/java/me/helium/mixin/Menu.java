package me.helium.mixin;

import me.helium.GUI.HeliumScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(GameMenuScreen.class)
public class Menu extends Screen {
    public Menu(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "initWidgets")
    public void initWidgets(CallbackInfo info) {
        this.addDrawableChild(this.createButton(Text.of("Helium"), () -> new HeliumScreen(this, this.client.options),
                this.width / 2 - 50, this.height - 24, 100, 20));
    }

    private ButtonWidget createButton(Text text, Supplier<Screen> screenSupplier, int x, int y, int width, int height) {
        return ButtonWidget.builder(text, (button) -> {
            this.client.setScreen((Screen)screenSupplier.get());
        }).dimensions(x, y, width, height).build();
    }

}
