package eu.koboo.itemprotocol.adapter;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import eu.koboo.itemprotocol.ItemProtocol;
import eu.koboo.itemprotocol.wrapper.WrapperPlayServerEntityEquipment;
import org.bukkit.inventory.ItemStack;

public class AdapterServerEntityEquipment extends PacketAdapter {

    private final ItemProtocol itemProtocol;

    public AdapterServerEntityEquipment(ItemProtocol itemProtocol) {
        super(itemProtocol, ListenerPriority.LOWEST, Server.ENTITY_EQUIPMENT);
        this.itemProtocol = itemProtocol;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        WrapperPlayServerEntityEquipment packet = new WrapperPlayServerEntityEquipment(event.getPacket());

        ItemStack itemStack = itemProtocol.onProtocol(packet.getItem(), event.getPlayer());
        if(itemStack == null) {
            event.setCancelled(true);
        } else {
            packet.setItem(itemStack);
        }
    }

}