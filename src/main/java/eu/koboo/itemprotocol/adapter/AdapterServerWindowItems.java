package eu.koboo.itemprotocol.adapter;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import eu.koboo.itemprotocol.ItemProtocol;
import eu.koboo.itemprotocol.wrapper.WrapperPlayServerWindowItems;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.inventory.ItemStack;

public class AdapterServerWindowItems extends PacketAdapter {

    private final ItemProtocol itemProtocol;

    public AdapterServerWindowItems(ItemProtocol itemProtocol) {
        super(itemProtocol, ListenerPriority.LOWEST, Server.WINDOW_ITEMS);
        this.itemProtocol = itemProtocol;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        WrapperPlayServerWindowItems packet = new WrapperPlayServerWindowItems(event.getPacket());

        List<ItemStack> slotDataList = new ArrayList<>();
        for(ItemStack itemStack : packet.getSlotData()) {
            ItemStack retStack = itemProtocol.onProtocol(itemStack, event.getPlayer());
            if(retStack != null) {
                slotDataList.add(retStack);
            }
        }
        packet.setSlotData(slotDataList);
    }

}