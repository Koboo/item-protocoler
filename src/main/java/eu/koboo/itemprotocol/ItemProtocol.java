package eu.koboo.itemprotocol;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import eu.koboo.itemprotocol.adapter.AdapterServerEntityEquipment;
import eu.koboo.itemprotocol.adapter.AdapterServerSetSlot;
import eu.koboo.itemprotocol.adapter.AdapterServerWindowItems;
import eu.koboo.itemprotocol.events.ItemProtocolEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemProtocol extends JavaPlugin{

    private static ItemProtocol API;

    @Override
    public void onEnable() {
        API = this;

        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new AdapterServerWindowItems(this));
        protocolManager.addPacketListener(new AdapterServerEntityEquipment(this));
        protocolManager.addPacketListener(new AdapterServerSetSlot(this));
    }

    @Override
    public void onDisable() {

    }

    public ItemStack onProtocol(ItemStack itemStack, Player toPlayer) {
        ItemProtocolEvent protocolEvent = new ItemProtocolEvent(toPlayer, itemStack);
        Bukkit.getPluginManager().callEvent(protocolEvent);
        return protocolEvent.getItemStack();
    }

    public static ItemProtocol getAPI() {
        return API;
    }

}
