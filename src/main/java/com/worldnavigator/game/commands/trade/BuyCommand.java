package com.worldnavigator.game.commands.trade;

import com.worldnavigator.game.commands.TradeCommand;
import com.worldnavigator.game.exceptions.NoSuchItemException;
import com.worldnavigator.game.maze.items.Item;
import com.worldnavigator.game.maze.items.ItemFactory;
import com.worldnavigator.game.maze.wall.wallobjects.Seller;
import com.worldnavigator.game.player.Player;

public class BuyCommand implements TradeCommand {

  @Override
  public String execute(Player player, String itemName) {
    Seller seller = (Seller) player.getCurrentWall();
    if (seller == null) {
      throw new IllegalArgumentException("You are not facing a seller!");
    }

    if (seller.getPricesList().containsKey(itemName)) {
      int itemPrice = seller.getItemPrice(itemName);

      if (player.getInventory().containsItem(itemName))
        return "You don't need this item," + " It's already in your inventory!";

      if (player.getInventory().getGold().getAmount() >= itemPrice) {
        player.getInventory().getGold().withdrawGoldAmount(itemPrice);
        try {
          addPurchasedItem(player, itemName);
        } catch (NoSuchItemException e) {
          e.printStackTrace();
        }
      } else return String.format("Your gold amount isn't enough to buy <%s> !", itemName);
    }
    return "The seller doesn't have the required item!";
  }

  private String addPurchasedItem(Player player, String itemName) throws NoSuchItemException {
    Item item = ItemFactory.getItem(itemName);
    player.getInventory().addItem(item);
    return String.format("The %s has been added to your inventory", itemName);
  }

  @Override
  public String getDescription() {
    return "Use this command to buy an item from the facing seller!";
  }

  @Override
  public String name() {
    return "buy";
  }
}
