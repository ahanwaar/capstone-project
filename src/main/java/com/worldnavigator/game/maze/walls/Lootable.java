package com.worldnavigator.game.maze.walls;

import com.worldnavigator.game.maze.items.Item;

import java.util.List;
import java.util.Optional;

public interface Lootable {
    boolean isLooted();
    void setLooted();
    List<Item> loot();
}
