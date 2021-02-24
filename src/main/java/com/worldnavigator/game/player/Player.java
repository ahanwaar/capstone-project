package com.worldnavigator.game.player;

import com.worldnavigator.game.Game;
import com.worldnavigator.game.maze.Direction;
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

  @EqualsAndHashCode.Include private final String userName;
  private Inventory inventory;
  private PlayerStatus playerStatus;
  private Game game;
  private int roomIndex;
  private Direction direction;

  public Player(
      String userName, int goldAmount, int roomIndex,  Direction direction) {
    this.userName = userName;
    this.inventory.getGold().addGoldAmount(goldAmount);
    this.roomIndex = roomIndex;
    this.direction = direction;
  }

  public Room getCurrentRoom() {
    return this.game.getGameConfig().getMaze().getRoom(roomIndex);
  }

  public Wall getCurrentWall() {
    return getCurrentRoom().getWall(direction);
  }

  public int getTotalGoldValue() {
    return getInventory().convertToGoldAmount();
  }
}
