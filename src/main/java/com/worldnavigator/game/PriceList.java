package com.worldnavigator.game;

import com.worldnavigator.game.exceptions.NoSuchItemException;
import com.worldnavigator.game.maze.items.Item;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PriceList {
  private static final PriceList priceList = new PriceList();
  private Map<String,Integer> prices;

  private PriceList() {
    this.prices = new HashMap<>();
    this.prices.putIfAbsent("flashlight",20);
    this.prices.putIfAbsent("copperKey",20);
    this.prices.putIfAbsent("silverKey",30);
    this.prices.putIfAbsent("goldKey",40);
  }

  public static PriceList getInstance() {
    return priceList;
  }

  public int getItemPrice(Item item){
    Objects.requireNonNull(item);
    int price =0;
    try{
      if(prices.containsKey(item.getName()))
        price = prices.get(item.getName());
      else throw new NoSuchItemException("There no such item in the price list");
    } catch (NoSuchItemException e) {
      e.printStackTrace();
    }
    return price;
  }
}
