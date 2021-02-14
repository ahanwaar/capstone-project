package com.worldnavigator.game.maze.walls.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.walls.Lockable;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.visitors.WallVisitor;

import java.util.List;
import java.util.Objects;

public class Door extends Wall implements Lockable {
    private final List<Room> connectedRooms;
    private final Key key;
    private boolean isLocked;
    private boolean isOpen;
    //private final boolean isMagical door?; //TODO

    @JsonCreator
    private Door(
            @JsonProperty("connectedRooms") List<Room> connectedRooms,
            @JsonProperty("key") Key key,
            @JsonProperty(value="isLocked") boolean isLocked
    ) {
        this.connectedRooms = connectedRooms;
        this.key = key;
        this.isOpen = false;
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

    public List<Room> getConnectedRooms() {
        return connectedRooms;
    }
}
