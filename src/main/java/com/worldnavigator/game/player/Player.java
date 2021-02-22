package com.worldnavigator.game.player;

import com.worldnavigator.game.Game;
import com.worldnavigator.game.Gold;
import com.worldnavigator.game.maze.Inventory;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.wall.Wall;
import java.util.Objects;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

  public int getTotalGoldValue(){
    return getInventory().convertToGoldAmount();
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Player player = (Player) o;
    return userName.equals(player.userName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName);
  }
}
