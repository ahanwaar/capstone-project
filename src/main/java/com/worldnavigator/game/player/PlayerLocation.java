package com.worldnavigator.game.player;

import com.worldnavigator.game.maze.Direction;

public class PlayerLocation {

  private int roomIndex;
  private Direction direction;

  public PlayerLocation(int roomIndex, Direction direction) {
    this.roomIndex = roomIndex;
    this.direction = direction;
  }

  public int getRoomIndex() {
    return roomIndex;
  }

  public void setRoomIndex(int roomIndex) {
    this.roomIndex = roomIndex;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }
}
