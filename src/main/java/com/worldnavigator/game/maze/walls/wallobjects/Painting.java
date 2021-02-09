package com.worldnavigator.game.maze.walls.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.maze.items.Item;
import com.worldnavigator.game.maze.walls.Lootable;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.visitors.WallVisitor;

import java.util.ArrayList;
import java.util.List;

public class Painting extends Wall implements Lootable {

    private final Item item;
    private boolean isLooted;

    @JsonCreator
    public Painting(
            @JsonProperty("item") Item item
    ) {
        this.item = item;
        this.isLooted = false;
    }

    @Override
    public boolean isLooted() {
        return this.isLooted;
    }

    @Override
    public void setLooted() {
        this.isLooted = true;
    }

    @Override
    public List<Item> loot() {
        List<Item> items = new ArrayList<>(1);
        items.add(item);
        return items ;
    }

    @Override
    public String accept(WallVisitor visitor) {
        return visitor.visitPainting(this);
    }
}
