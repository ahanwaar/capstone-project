package com.worldnavigator.game.maze.items;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.worldnavigator.game.PriceList;

@JsonDeserialize(using = ItemDeserializer.class)
public interface Item {

  String getName();

  String accept(ItemVisitor itemVisitor);

  default int getPrice() {
    return PriceList.getInstance().getItemPrice(this.getName());
  }
}
