# ItemProtocoler 

This is a proof-of-concept of server-side changes to ItemStacks being played out client-side. Further information can be found below.

Changes, improvements, contributions and additions are welcome via [pull request](https://github.com/Koboo/item-protocoler/pulls)! 

#### I am thankful to every contributor!

## Overview 

This is the overview of the documentation

* [Use-Cases](#use-cases) (What could it be used for?)
* [Evaluation](#evaluation) (Does it make sense to use it?)
* [Usage](#usage) (How is it used?)
* [Packets](#packets) (Which packets are intercepted?)
* [Dependencies](#dependencies) (Which external libraries are used?)


## Use-Cases

Due to the immense number of changes that take place in the individual packets that affect item or inventory changes, developers should explicitly consider in advance whether and to what scope the use of this procedure makes sense.

* Localization of `ItemStack` names
* Control of corrupt `ItemStack`s
* `Player`-based modification of `ItemStack`s

If further use cases are not listed here, they can be added via [pull request](https://github.com/Koboo/item-protocoler/pulls)!

## Evaluation

Using this method yields both pro and con arguments. Some of these are listed below.

#### Pro

* Central query point of all `ItemStack`s that are sent to clients.
* Easy modification of the created `ItemStack`s.

#### Contra

* Overhead creation through newly cloned `ItemStack` objects.

## Usage

The `ItemProtocolEvent` follows the regular `Bukkit` event-api and can also be intercepted via it. An example `Listener` might look like the following:

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

From an `ItemProtocolEvent` both the `Player` and the original `ItemStack` can be fetched. If the original `ItemStack` is changed, it should be noted that it may also be changed on the server-side. To make only client-side changes, the cloned `ItemStack` is provided.

It should also be noted that a new clone is created when the cloned `ItemStack` method is called, and not when the event is initialized.

## Packets

Currently the following packets are intercepted and can be retrieved as `ItemProtocolEvent`.

* `WrapperPlayServerEntityEquipment`
* `WrapperPlayServerSetSlot`
* `WrapperPlayServerWindowItems`

These are the only packets that are sent from the server-to-client. In the scenario it is currently assumed that the client does not create any `ItemStack`s via `GameMode.CREATIVE`. This would require that further client-to-server packets would have to be intercepted via `PacketAdapter`.

## Dependencies

This proof-of-concept uses the `ProtocolLib` library and additionally some classes of the official `PacketWrapper` library. For more information, please refer to the documentation of the respective library.

* [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/)
* [PacketWrapper](https://github.com/dmulloy2/PacketWrapper)

