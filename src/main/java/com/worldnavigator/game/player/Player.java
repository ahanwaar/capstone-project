package com.worldnavigator.game.player;

import com.worldnavigator.game.Game;
import com.worldnavigator.game.Gold;
import com.worldnavigator.game.maze.items.Item;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.utils.Pair;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Player {

  @EqualsAndHashCode.Include
  private final String userName;
  private PlayerLocation location;
  private Inventory inventory;
  private PlayerStatus playerStatus;
  private  Game game;
  private Gold gold;



  public Player(String userName) {
    this.userName = userName;
  }


  public Room getCurrentRoom() {
    return this.game.getMaze().getRoom(getLocation().getRoomIndex());
  }

  public Wall getCurrentWall() {
    return getCurrentRoom().getWall(getLocation().getDirection());
  }

  public void addToInventory(List<Pair<Item, Integer>> items) {

    items.forEach(item -> inventory.addOrReplaceItem(item.getKey(), item.getValue()));
  }

  public int convertInventoryToGoldAmount() {
    int totalGold = 0;
    List<Pair<Item, Integer>> items = inventory.getInventory();
    for (Pair<Item, Integer> item : items) {
      totalGold += item.getKey().getPrice() * item.getValue();
    }
    return totalGold;
  }


}
