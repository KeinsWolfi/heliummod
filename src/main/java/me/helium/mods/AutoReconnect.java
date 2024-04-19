package me.helium.mods;

import it.unimi.dsi.fastutil.Pair;
import me.helium.Helium9;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;

public class AutoReconnect {

    public static boolean autoReconnect = false;

    public static Pair<ServerAddress, ServerInfo> lastConnectedServer;

    public static void reconnect() {
        if(autoReconnect) {
            ConnectScreen.connect(new TitleScreen(), Helium9.mc, lastConnectedServer.left(), lastConnectedServer.right(), false);
        }
    }

}
