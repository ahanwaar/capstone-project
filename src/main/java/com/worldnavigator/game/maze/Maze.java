package com.worldnavigator.game.maze;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Maze {

  private final List<Room> rooms;

  @JsonCreator
  public Maze(@JsonProperty("rooms") List<Room> rooms) {
    this.rooms = rooms;
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public Room getRoom(int index) {
    return rooms.get(index);
  }

  public int getNumOfRooms() {
    return this.rooms.size();
  }
}
