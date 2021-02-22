package com.worldnavigator.game.maze.items;


import com.worldnavigator.game.PriceList;
import com.worldnavigator.game.exceptions.NoSuchItemException;

public interface Item {

  String getName();

  String accept(ItemVisitor itemVisitor);

  default int getPrice() throws NoSuchItemException {
    return PriceList.getInstance().getItemPrice(this);
  }
}
