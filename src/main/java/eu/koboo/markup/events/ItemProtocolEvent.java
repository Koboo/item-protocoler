package eu.koboo.markup.events;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ItemProtocolEvent extends Event {

    protected static final HandlerList handlers = new HandlerList();

    private ItemStack itemStack;

    public ItemProtocolEvent(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static HandlerList getHandlerList() {
        return handlers;
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