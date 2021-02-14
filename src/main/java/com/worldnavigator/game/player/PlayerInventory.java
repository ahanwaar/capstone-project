package com.worldnavigator.game.player;

import com.worldnavigator.game.Gold;
import com.worldnavigator.game.maze.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerInventory {
    private Map<String , Item> items;
    private Gold gold;

    public PlayerInventory() {
        this.gold = new Gold(0);
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void throwItems(){
        items.clear();
    }

    public void addItem(Item item){
        items.put(item.toString(),item);
    }

    public Gold getGold() {
        return gold;
    }

    public int getTotalValue(){
        List<Item> items =  new ArrayList<Item>(this.items.values());
        int total=0;
        for(Item item : items){
            total+= item.getPrice().getAmount();
        }
    return total + gold.getAmount();
    }
}
