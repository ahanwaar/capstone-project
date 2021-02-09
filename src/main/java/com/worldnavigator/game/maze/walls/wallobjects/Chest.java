package com.worldnavigator.game.maze.walls.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.maze.items.Item;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.walls.Lockable;
import com.worldnavigator.game.maze.walls.Lootable;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.visitors.WallVisitor;

import java.util.List;
import java.util.Objects;

public class Chest extends Wall implements Lockable, Lootable {
    private final Key key;
    private final List<Item> items;
    private final int gold;
    private boolean isLooted;
    private boolean isLocked;


    @JsonCreator
    public Chest(
            @JsonProperty("key") Key key,
            @JsonProperty("items") List<Item> items,
            @JsonProperty("gold") int gold
    ) {
        this.key = key;
        this.items = items;
        this.gold = gold;
        this.isLocked =true;
        this.isLooted =false;
    }

    @Override
    public boolean unLock(Key key) {
        if (Objects.equals(this.key,key)){
            this.isLocked = false;
        }
        return isLocked;
    }

    @JsonProperty(value="isLocked")
    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public Key getKey() {
        return this.key;
    }

    @Override
    public String accept(WallVisitor visitor) {
        return visitor.visitChest(this);
    }

    @Override
    public boolean isLooted() {
        return this.isLooted;
    }

    @Override
    public void setLooted() {
        this.isLooted=true;
    }

    @Override
    public List<Item> loot() {
        return this.items;
    }
}
