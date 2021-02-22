package com.worldnavigator.game.maze.walls.wallobjects;

import com.fasterxml.jackson.annotation.*;
import com.worldnavigator.game.maze.*;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.walls.*;

import java.util.*;

public class Door extends Wall implements Lockable {

  private final List<Room> connectedRooms;
  private final Key key;
  private boolean isLocked;
  private boolean isOpen;
  //TODO adding method isMagical door;

  @JsonCreator
  public Door(
      @JsonProperty("connectedRooms") List<Room> connectedRooms,
      @JsonProperty("key") Key key
  ) {
    this.connectedRooms = connectedRooms;
    this.key = key;
    this.isLocked = true;
    this.isOpen = false;
  }


  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitDoor(this);
  }

  @Override
  public boolean unLock(Key key) {
    if (Objects.equals(this.key, key)) {
      isLocked = !isLocked;
      return true;
    }else return false;
  }

  @Override
  public boolean isLocked() {
    return isLocked;
  }

  public void open() {
    if (!isLocked) {
      isOpen = true;
    }
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

  public boolean isMagicalDoor(){
    if (this.connectedRooms.size() < 1) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return "door";
  }
}
