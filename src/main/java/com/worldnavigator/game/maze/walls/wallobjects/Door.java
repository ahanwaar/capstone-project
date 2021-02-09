package com.worldnavigator.game.maze.walls.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.walls.Lockable;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.visitors.WallVisitor;

import java.util.Objects;

public class Door extends Wall implements Lockable {
    private final int Room;//edit between 2 rooms
    private final Key key;
    private boolean isLocked;
    private boolean isOpen;

    @JsonCreator
    private Door(
            @JsonProperty("room") int room,
            @JsonProperty("lock") Key key,
            @JsonProperty(value="isOpen") boolean isOpen,//we can edit it later removing value
            @JsonProperty(value="isLocked") boolean isLocked

    ) {
        Room = room;
        this.key = key;
        if(isOpen && isLocked)
            throw new IllegalArgumentException("The lock can't be open and locked at the same time.");

        this.isOpen = isOpen;
        this.isLocked = isLocked;
    }

    @Override
    public String accept(WallVisitor visitor) {
        return visitor.visitDoor(this);
    }


    @Override
    public boolean unLock(Key key) {
        if(Objects.equals(this.key, key)){
            this.isLocked=false;
        }
        return isLocked;
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    public void open() {
        if(!isLocked)
            isOpen = true;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    @Override
    public Key getKey() {
        return this.key;
    }
}
