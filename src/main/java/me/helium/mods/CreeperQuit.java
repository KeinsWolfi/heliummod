package me.helium.mods;

import me.helium.Helium9;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CreeperQuit {

    public static boolean creeperQuit = false;
    public static boolean noAutoReconnect = true;

    public static boolean creeperNear = false;

    private static int timer = 0;

    public static void onTick() {
        if(creeperQuit) {
            List<CreeperEntity> entities = new ArrayList<>();
            if(Helium9.mc.world == null) return;
            for(Entity entity : Helium9.mc.world.getEntities()){
                if(entity.getType().equals(EntityType.CREEPER)){
                    if(entity.squaredDistanceTo(Helium9.mc.player) <= 100){
                        CreeperEntity creeperEntity = (CreeperEntity) entity;
                        entities.add(creeperEntity);
                        if(creeperEntity.getFuseSpeed() >= 1){
                            timer++;
                        }
                    }
                }
            }
            if(!entities.isEmpty()) creeperNear = true;
            else creeperNear = false;
            if(timer >= 5) {
                timer = 0;
                if(noAutoReconnect){
                    AutoReconnect.autoReconnect = false;
                }

                Helium9.mc.getNetworkHandler().onDisconnect(new DisconnectS2CPacket(Text.of("Creeper Exploding!!")));
            }
        }
    }

}
