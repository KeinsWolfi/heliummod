package me.helium;

import me.helium.mods.CreeperQuit;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.KeyEvent;

public class Helium9 implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("helium9");
	public static final MinecraftClient mc = MinecraftClient.getInstance();

	public static final String MOD_ID = "helium9";
	public static ModMetadata MOD_META;
	public static String MOD_NAME;
	public static String MOD_VERSION;

    private static KeyBinding creeperQuitKey;

	@Override
	public void onInitialize() {
		MOD_META = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow().getMetadata();
		MOD_NAME = MOD_META.getName();
		MOD_VERSION = String.valueOf(MOD_META.getVersion());

		LOGGER.info("MoinMoin meine aktiven freunde (" + MOD_NAME + " v" + MOD_VERSION + ")");

		creeperQuitKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"CreeperQuit", // The translation key of the keybinding's name
				KeyEvent.VK_G, // The keycode of the key
				"Helium" // The translation key of the keybinding's category.
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (creeperQuitKey.wasPressed()) {
				CreeperQuit.creeperQuit = !CreeperQuit.creeperQuit;
				LOGGER.info("CreeperQuit toggled. (" + !CreeperQuit.creeperQuit + " to " + CreeperQuit.creeperQuit + ")");
			}
		});

	}
}