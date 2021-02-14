package com.worldnavigator.game.factories;

import com.worldnavigator.game.Exceptions.NoSuchItemException;
import com.worldnavigator.game.maze.items.Flashlight;
import com.worldnavigator.game.maze.items.Item;
import com.worldnavigator.game.maze.items.Key;
//TODO

/*public class ItemFactory {

    private static final ItemFactory itemFactory = new ItemFactory();

    private ItemFactory(){}

    public Item getItem(String itemType) throws NoSuchItemException {
        itemType=itemType.trim().toLowerCase();

        if(itemType.equals("flashlight")) {
            return new Flashlight();
        } else if(itemType.endsWith("key")) {
            return new Key(itemType.split("\\s+")[0]);
        }
        throw new NoSuchItemException("There is no such item with that name!");
    }

    public static ItemFactory getFactory() {
        return itemFactory;
    }
}
*/