package eu.koboo.markup.util;

import eu.koboo.markup.events.ItemProtocolEvent;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class EventUtil {

  public static ItemStack isItemStackValid(ItemStack itemStack) {
    ItemProtocolEvent protocolEvent = new ItemProtocolEvent(itemStack);
    Bukkit.getPluginManager().callEvent(protocolEvent);
    return protocolEvent.getItemStack();
  }

}
