package eu.koboo.markup;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import eu.koboo.markup.adapter.AdapterServerEntityEquipment;
import eu.koboo.markup.adapter.AdapterServerSetSlot;
import eu.koboo.markup.adapter.AdapterServerWindowItems;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemProtocoler extends JavaPlugin {

    private static ItemProtocoler API_INSTANCE;

    @Override
    public void onEnable() {
        API_INSTANCE = this;

        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new AdapterServerWindowItems(this));
        protocolManager.addPacketListener(new AdapterServerEntityEquipment(this));
        protocolManager.addPacketListener(new AdapterServerSetSlot(this));
    }

    @Override
    public void onDisable() {

    }

    public static ItemProtocoler getAPI() {
        return API_INSTANCE;
    }

}
