package com.worldnavigator.game.maze.walls.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.walls.Lockable;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.player.Inventory;
import com.worldnavigator.game.maze.walls.WallVisitor;

import java.util.Objects;

public class Chest extends Wall implements Lockable {

    private final Key key;
    private Inventory inventory;
    private boolean isLooted;
    private boolean isLocked;
    private boolean isOpen;


    @JsonCreator
    public Chest(
            @JsonProperty("key") Key key,
            @JsonProperty("inventory") Inventory inventory
    ) {
        this.key = key;
        this.inventory = inventory;
        this.isLocked =true;
        this.isOpen = false;
        this.isLooted =false;
    }

    @Override
    public void unLock(Key key) {
        if (Objects.equals(this.key,key)){
            this.isLocked = false;
        }
    }

    @JsonProperty(value="isLocked")
    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @JsonProperty(value="isOpen")
    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void open() {
        if(!isLocked){
            isOpen = true;
        }
    }

    @Override
    public Key getKey() {
        return this.key;
    }

    @Override
    public String accept(WallVisitor visitor) {
        return visitor.visitChest(this);
    }

    public boolean isLooted() {
        return this.isLooted;
    }

    public void setLooted(boolean looted) {
        this.isLooted=looted;
    }

    public Inventory loot() {
        return this.inventory;
    }

    @Override
    public String toString() {
        return "chest";
    }
}
