package eu.koboo.itemprotocol.adapter;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import eu.koboo.itemprotocol.ItemProtocol;
import eu.koboo.itemprotocol.wrapper.WrapperPlayServerSetSlot;
import org.bukkit.inventory.ItemStack;

public class AdapterServerSetSlot extends PacketAdapter {

    private final ItemProtocol itemProtocol;

    public AdapterServerSetSlot(ItemProtocol itemProtocol) {
        super(itemProtocol, ListenerPriority.LOWEST, Server.SET_SLOT);
        this.itemProtocol = itemProtocol;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        WrapperPlayServerSetSlot packet = new WrapperPlayServerSetSlot(event.getPacket());

        ItemStack itemStack = itemProtocol.onProtocol(packet.getSlotData(), event.getPlayer());
        if(itemStack == null) {
            event.setCancelled(true);
        } else {
            packet.setSlotData(itemStack);
        }
    }

}