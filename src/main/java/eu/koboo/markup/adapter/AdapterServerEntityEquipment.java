package eu.koboo.markup.adapter;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import eu.koboo.markup.ItemProtocoler;
import eu.koboo.markup.util.EventUtil;
import eu.koboo.markup.wrapper.WrapperPlayServerEntityEquipment;
import org.bukkit.inventory.ItemStack;

public class AdapterServerEntityEquipment extends PacketAdapter {

    private final ItemProtocoler itemProtocoler;

    public AdapterServerEntityEquipment(ItemProtocoler itemProtocoler) {
        super(itemProtocoler, ListenerPriority.LOWEST, Server.ENTITY_EQUIPMENT);
        this.itemProtocoler = itemProtocoler;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        WrapperPlayServerEntityEquipment packet = new WrapperPlayServerEntityEquipment(event.getPacket());

        ItemStack itemStack = EventUtil.fireItemProtocolEvent(packet.getItem());
        if(itemStack == null) {
            event.setCancelled(true);
        } else {
            packet.setItem(itemStack);
        }
    }

}