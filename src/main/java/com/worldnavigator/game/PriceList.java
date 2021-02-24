package com.worldnavigator.game;

import com.worldnavigator.game.exceptions.NoSuchItemException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;

@Getter
public class PriceList {

  private static final PriceList priceList = new PriceList();

  private Map<String,Integer> prices;

  private PriceList() {
    this.prices = new HashMap<>();
    this.prices.putIfAbsent("flashlight", 20);
    this.prices.putIfAbsent("copperKey", 20);
    this.prices.putIfAbsent("silverKey", 30);
    this.prices.putIfAbsent("goldKey", 40);
  }

  public static PriceList getInstance() {
    return priceList;
  }


  public int getItemPrice(String itemName) {
    Objects.requireNonNull(itemName);
    int price = 0;
    try {
      if (prices.containsKey(itemName)) {
        price = prices.get(itemName);
      } else {
        throw new NoSuchItemException("There no such item in the price list");
      }
    } catch (NoSuchItemException e) {
      e.printStackTrace();
    }
    return price;
  }
}
