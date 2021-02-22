package com.worldnavigator.game.maze.wall.wallobjects;

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
  private boolean isOpen;

  public Door(List<Room> connectedRooms, Lock lock) {
    this.connectedRooms = connectedRooms;
    this.lock = lock;
    this.isOpen = false;
  }

  public boolean open(){
    if(!lock.isLocked()){
      isOpen = true;
    }
    return isOpen;
  }

  public boolean isOpen() {
    return isOpen;
  }

  public boolean isMagicalDoor(){
    return this.connectedRooms.size() < 2;
  }

  public Room getNextRoom(Room room){
    int index = connectedRooms.indexOf(room);
    if(index == 1){
      return connectedRooms.get(0);
    }return connectedRooms.get(1);
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
