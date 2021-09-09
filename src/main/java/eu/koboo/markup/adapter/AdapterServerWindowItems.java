package eu.koboo.markup.adapter;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import eu.koboo.markup.ItemProtocoler;
import eu.koboo.markup.util.EventUtil;
import eu.koboo.markup.wrapper.WrapperPlayServerWindowItems;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.inventory.ItemStack;

public class AdapterServerWindowItems extends PacketAdapter {

    private final ItemProtocoler itemProtocoler;

    public AdapterServerWindowItems(ItemProtocoler itemProtocoler) {
        super(itemProtocoler, ListenerPriority.LOWEST, Server.WINDOW_ITEMS);
        this.itemProtocoler = itemProtocoler;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        WrapperPlayServerWindowItems packet = new WrapperPlayServerWindowItems(event.getPacket());

        List<ItemStack> slotDataList = new ArrayList<>();
        for(ItemStack itemStack : packet.getSlotData()) {
            ItemStack retStack = EventUtil.isItemStackValid(itemStack);
            if(retStack != null) {
                slotDataList.add(retStack);
            }
        }
        packet.setSlotData(slotDataList);
    }

}