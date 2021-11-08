# ItemProtocoler 

This is a proof-of-concept to accomplish automatic localization of ItemStack names via packet manipulation by ProtocolLib.

## Overview 

This is the overview of the documentation

* [Use-Cases](#use-cases) (What could it be used for?)
* [Evaluation](#evaluation) (Does it make sense to use it?)
* [Usage](#usage) (How is it used?)


## Use-Cases

* Localization of `ItemStack` names
* Control of corrupt `ItemStack`s
* `Player`-based modification of `ItemStack`s

If further use cases are not listed here, they can be added via [pull request](https://github.com/Koboo/item-protocoler/pulls)!

## Evaluation

Using this method yields both pro and con arguments. Some of these are listed below.

#### Pro

* Central localization of `ItemStack` names by `ItemProtocolEvent`

#### Contra

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
