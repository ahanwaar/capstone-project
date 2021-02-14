package com.worldnavigator.game.player;

import com.worldnavigator.game.maze.items.Item;
import com.worldnavigator.game.utils.Pair;


import java.util.*;

public class Inventory {
    private final static String NAME = "container";
    private Map<String, Pair<Item, Integer>> items;

    public Inventory() {
        this.items = Collections.synchronizedMap(new HashMap<>());
    }

    public List<Pair<Item, Integer>>  getInventory() {
        List<Pair<Item, Integer>> tmpItems = new ArrayList<>();
        for (Map.Entry<String, Pair<Item, Integer>> item : items.entrySet()) {
            tmpItems.add(new Pair<>(item.getValue().getKey(), item.getValue().getValue()));
        }
        return tmpItems;
    }

    public void throwItems(){
        items.clear();
    }

    public void addItems(Map<String, Pair<Item, Integer>> items) {
        this.items.putAll(items);
    }

    public int getItemAmount(String itemName) {
        if (!items.containsKey(itemName)) return 0;
        return items.get(itemName).getValue();
    }

    public boolean containsItem(String itemName) {
        return items.containsKey(itemName);
    }

    public void addItem(Item item, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Amount can not be less than zero when adding to inventory in class Container");
        }
        if(amount>0){
            items.put(item.getName(), new Pair<>(item, amount));
        }
    }

    public Item getItem(String itemName) {
        if (!containsItem(itemName)) {
            throw new IllegalArgumentException("You can not getItem before adding it in class Container");
        }
        return items.get(itemName).getKey();
    }

    public void addOrReplaceItem(Item item, int amount) {
        if (containsItem(item.getName())) {
            replaceItem(item.getName(), amount);
        } else {
            addItem(item, amount);
        }
    }

    public void replaceItem(String itemName, int amount) {
        if (!containsItem(itemName)) {
            throw new IllegalArgumentException(
                    "Can not replace an item that is not added in class Container");
        }
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Item amount (" + amount + ") should not be negative in class Container");
        }
        if (getItemAmount(itemName) == 0) {
            items.remove(itemName);
        } else {
            items.replace(itemName, new Pair<>(getItem(itemName), amount));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder();
        String prefix = "";
        for (Map.Entry<String, Pair<Item, Integer>> item : items.entrySet()) {
            stringRepresentation.append(prefix);
            prefix = " | ";
            stringRepresentation
                    .append("Name: ")
                    .append(item.getKey())
                    .append(", Cost: ")
                    .append(item.getValue().getKey().getPrice())
                    .append(", Amount: ")
                    .append(item.getValue().getValue());
        }
        if (stringRepresentation.length() == 0) {
            return "No items to show!";
        }
        return stringRepresentation.toString();
    }

}
