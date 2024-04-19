package me.helium.mods;

import me.helium.Helium9;
import net.minecraft.client.MinecraftClient;

public class AutoZZZZ {

    private static MinecraftClient mc = Helium9.mc;

    public static boolean autoZZZZ = false;

    private static int timer = 100;

    public static void onTick() {
        if(mc.player == null || !autoZZZZ) return;

        if(mc.player.isSleeping()){
            timer++;
            if(timer >= 100){

                String message;

                message = "rrrrrzzzzzzzzz";
                message = randomizeCapitals(message);

                mc.player.networkHandler.sendChatMessage(message);
                timer = 0;
            }
        } else {
            timer = 100;
        }

    }

    private static String randomizeCapitals(String text) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < sb.length(); i++) {
            if (Math.random() < 0.5) {
                sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
            }
        }
        return sb.toString();
    }

}
