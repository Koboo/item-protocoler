package eu.koboo.itemprotocol.events;

import com.comphenix.protocol.PacketType.Play;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ItemProtocolEvent extends Event {

    protected static final HandlerList handlers = new HandlerList();

    private final Player player;
    private ItemStack itemStack;

    public ItemProtocolEvent(Player player, ItemStack itemStack) {
        this.player = player;
        this.itemStack = itemStack;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getClonedItemStack() {
        if(this.itemStack == null) {
            return new ItemStack(Material.AIR, 1);
        }
        return new ItemStack(this.itemStack);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}