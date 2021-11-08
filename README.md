# ItemProtocoler 

This is a proof-of-concept to accomplish automatic localization of ItemStack names via packet manipulation by ProtocolLib.

## Pro

* Central localization of `ItemStack` names by `ItemProtocolEvent`

## Contra

* Overhead creation through newly created `ItemStack` objects


## Usage

```java
public class ItemProtocolListener implements Listener {

  @EventHandler
  public void onItemProtocol(ItemProtocolEvent event) {
    // The original ItemStack object
    ItemStack itemStack = event.getItemStack();
    
    // A clone of the original ItemStack object
    ItemStack clone = event.getClonedItemStack();
    
    // Set a modified ItemStack
    event.setItemStack(clone);
  }

}
```
