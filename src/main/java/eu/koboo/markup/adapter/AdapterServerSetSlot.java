package eu.koboo.markup.adapter;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import eu.koboo.markup.ItemProtocoler;
import eu.koboo.markup.util.EventUtil;
import eu.koboo.markup.wrapper.WrapperPlayServerSetSlot;
import org.bukkit.inventory.ItemStack;

public class AdapterServerSetSlot extends PacketAdapter {

    private final ItemProtocoler itemProtocoler;

    public AdapterServerSetSlot(ItemProtocoler itemProtocoler) {
        super(itemProtocoler, ListenerPriority.LOWEST, Server.SET_SLOT);
        this.itemProtocoler = itemProtocoler;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        WrapperPlayServerSetSlot packet = new WrapperPlayServerSetSlot(event.getPacket());

        ItemStack itemStack = EventUtil.isItemStackValid(packet.getSlotData());
        if(itemStack == null) {
            event.setCancelled(true);
        } else {
            packet.setSlotData(itemStack);
        }
    }

}