package com.worldnavigator.game.player;

import com.worldnavigator.game.Game;
import com.worldnavigator.game.maze.Inventory;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.wall.Wall;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Player {

  @EqualsAndHashCode.Include
  private final String userName;
  private PlayerLocation location;
  private Inventory inventory;
  private PlayerStatus playerStatus;
  private Game game;

  public Player(String userName) {
    this.userName = userName;
  }

  public Room getCurrentRoom() {
    return this.game.getMaze().getRoom(getLocation().getRoomIndex());
  }

  public Wall getCurrentWall() {
    return getCurrentRoom().getWall(getLocation().getDirection());
  }

  public int getTotalGoldValue() {
    return getInventory().convertToGoldAmount();
  }
}
