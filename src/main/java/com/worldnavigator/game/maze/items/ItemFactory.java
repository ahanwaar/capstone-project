package com.worldnavigator.game.maze.items;

import com.worldnavigator.game.exceptions.NoSuchItemException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ItemFactory {
  private static final Map<String, Item> factoryMap;

  static {
    Map<String, Item> realMap = new HashMap<>();
    factoryMap = Collections.unmodifiableMap(realMap);

    realMap.put("flashlight", new Flashlight());
    realMap.put("copperKey", Key.COPPER);
    realMap.put("silverKey", Key.SILVER);
    realMap.put("goldKey", Key.GOLD);
  }

  public static Item getItem(String itemName) throws NoSuchItemException {
    if (factoryMap.containsKey(itemName)) {
      return factoryMap.get(itemName);
    } else throw new NoSuchItemException("Unexpected value: " + itemName);
  }
}
