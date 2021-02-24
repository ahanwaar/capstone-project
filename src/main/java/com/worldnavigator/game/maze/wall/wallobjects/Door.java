package com.worldnavigator.game.maze.wall.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.wall.Lock;
import com.worldnavigator.game.maze.wall.Lockable;
import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.maze.wall.WallVisitor;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Door extends Wall implements Lockable {

  private final List<Room> connectedRooms;
  private final Lock lock;
  private boolean open;

  @JsonCreator
  public Door(
      @JsonProperty("connectedRooms") List<Room> connectedRooms, @JsonProperty("lock") Lock lock) {
    this.connectedRooms = connectedRooms;
    this.lock = lock;
    this.open = false;
  }

  public boolean open() {
    if (!lock.isLocked()) {
      open = true;
    }
    return open;
  }

  @JsonProperty(value = "open")
  public boolean isOpen() {
    return open;
  }

  public boolean isMagicalDoor() {
    return this.connectedRooms.size() < 2;
  }

  public Room getNextRoom(Room room) {
    int index = connectedRooms.indexOf(room);
    if (index == 1) {
      return connectedRooms.get(0);
    }
    return connectedRooms.get(1);
  }

  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitDoor(this);
  }

  @Override
  public String toString() {
    return "door";
  }
}
