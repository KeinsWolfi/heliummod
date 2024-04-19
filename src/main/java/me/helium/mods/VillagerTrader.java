package me.helium.mods;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.ObjectIntImmutablePair;
import me.helium.Helium9;
import me.helium.util.gui.ChatFormat;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.SetTradeOffersS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;

import java.util.ArrayList;
import java.util.List;

public class VillagerTrader {

    public static boolean villagerTrader = false;

    public static void sendChatMessage(Packet<?> packet) {
        if(Helium9.mc.player == null || !villagerTrader) return;
        if(packet instanceof SetTradeOffersS2CPacket) {
            SetTradeOffersS2CPacket tradePacket = (SetTradeOffersS2CPacket) packet;
            List<TradeOffer> offers = tradePacket.getOffers();
            if(!offers.isEmpty()){
                List<TradeOffer> enchantedBookOffers = new ArrayList<>();
                for(TradeOffer offer : offers){
                    if(offer.getSellItem().getItem().equals(Items.ENCHANTED_BOOK)){
                        enchantedBookOffers.add(offer);
                        ItemStack stack = offer.getSellItem();
                        List<Pair<Identifier, Integer>> enchantments = getEnchantments(stack);
                        for(Pair<Identifier, Integer> enchantment : enchantments){
                            String[] enchants = enchantment.left().toString().split(":");
                            Helium9.mc.player.sendMessage(isRareEnchant(enchants[1], enchantment.right()) ? Text.of(ChatFormat.GREEN + enchants[1] + " lvl: " + enchantment.right()) : Text.of(ChatFormat.RED + enchants[1] + " lvl: " + enchantment.right()));
                        }
                    }
                }
                if(enchantedBookOffers.isEmpty()){
                    Helium9.mc.player.sendMessage(Text.of(ChatFormat.RED + "-"));
                }
            }
        }
    }

    public static List<Pair<Identifier, Integer>> getEnchantments(ItemStack stack) {
        List<Pair<Identifier, Integer>> ret = new ArrayList<>();
        NbtList list = stack.getNbt().getList("StoredEnchantments", NbtElement.COMPOUND_TYPE);
        list.addAll(stack.getNbt().getList("Enchantments", NbtElement.COMPOUND_TYPE));
        for (int i = 0; i < list.size(); ++i) {
            NbtCompound c = list.getCompound(i);
            Identifier id = EnchantmentHelper.getIdFromNbt(c);
            if (id == null) continue;
            ret.add(new ObjectIntImmutablePair<>(id, EnchantmentHelper.getLevelFromNbt(c)));
        }
        return ret;
    }

    public static boolean isRareEnchant(String s, int level){
        return s.equals("mending") || (s.equals("sharpness") && level >= 5) || (s.equals("efficiency") && level >= 5) || (s.equals("unbreaking") && level >= 3) || (s.equals("protection") && level >= 4) || (s.equals("power") && level >= 5) || (s.equals("fortune") && level >= 3) || (s.equals("looting") && level >= 3);
    }

}
