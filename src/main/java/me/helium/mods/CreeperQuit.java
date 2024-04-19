package me.helium.mods;

import me.helium.GUI.HeliumScreen;
import me.helium.Helium9;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.text.Text;

public class CreeperQuit {

    public static boolean creeperQuit = false;

    private static int timer = 0;

    public static void onTick() {
        if(creeperQuit) {
            if(Helium9.mc.world == null) return;
            for(Entity entity : Helium9.mc.world.getEntities()){
                if(entity.getType().equals(EntityType.CREEPER)){
                    CreeperEntity creeperEntity = (CreeperEntity) entity;
                    if(creeperEntity.getFuseSpeed() >= 1){
                        timer++;
                    }
                }
            }
            if(timer >= 10) {
                timer = 0;
                Helium9.mc.getNetworkHandler().onDisconnected(Text.of("Creeper exploding!"));
            }
        }
    }

}
