package com.worldnavigator.game.maze;

import com.worldnavigator.game.Gold;
import com.worldnavigator.game.PriceList;
import com.worldnavigator.game.maze.items.Item;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Inventory {

  private final PriceList priceList = PriceList.getInstance();
  private Gold gold;
  private final Map<String, Item> items;

  public Inventory() {
    this.gold = new Gold(0);
    this.items = new HashMap<>();
  }

  public Map<String, Item> getItems() {
    return items;
  }

  public void removeItem(Item item) {
    Objects.requireNonNull(item);
    items.remove(item);
  }


  public void addItems(Map<String, Item> items) {
    for (Item item : items.values()) {
      if (containsItem(item.getName())) {
        gold.addGoldAmount(priceList.getItemPrice(item.getName()));
      } else {
        this.items.putIfAbsent(item.getName(), item);
      }
    }
  }

  public void mergeInventories(Inventory inventory) {
    addItems(inventory.getItems());
    this.gold.addGoldAmount(inventory.getGold().getAmount());
  }

  public Gold getGold() {
    return gold;
  }

  public boolean containsItem(String itemName) {
    return items.containsKey(itemName);
  }

  public void addItem(Item item) {
    Objects.requireNonNull(item);
    if(containsItem(item.getName())){
      gold.addGoldAmount(priceList.getItemPrice(item.getName()));
    }else this.items.putIfAbsent(item.getName(),item);
  }

  public Item getItem(String itemName) {
    Objects.requireNonNull(itemName);
    if (!containsItem(itemName)) {
      throw new IllegalArgumentException("The Item isn't existing");
    }
    return items.get(itemName);
  }

  public int convertToGoldAmount() {
    int total = gold.getAmount();
    for(Item item : items.values()){
      total += priceList.getItemPrice(item.getName());
    }
    return total;
  }

  @Override
  public String toString() {
    return "inventory";
  }
}
