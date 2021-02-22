package com.worldnavigator.game.controls.commands.main.check;

import com.worldnavigator.game.maze.walls.WallVisitor;
import com.worldnavigator.game.maze.walls.wallobjects.Chest;
import com.worldnavigator.game.maze.walls.wallobjects.Door;
import com.worldnavigator.game.maze.walls.wallobjects.Mirror;
import com.worldnavigator.game.maze.walls.wallobjects.Painting;
import com.worldnavigator.game.maze.walls.wallobjects.PlainWall;
import com.worldnavigator.game.maze.walls.wallobjects.Seller;
import com.worldnavigator.game.player.Inventory;
import com.worldnavigator.game.player.Player;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckVisitor implements WallVisitor {
  private final Player player;

  @Override
  public String visitPlainWall(PlainWall plainWall) {
    return "It's a plain wall, nothing here!";
  }

  @Override
  public String visitDoor(Door door) {
    if (door.isLocked()) {
      return "Door is locked, <"
          + door.getKey().getColor().toLowerCase()
          + "> key is needed to unlock!";
    } else {
      if (door.isOpen()) {
        return "The door is open, you can move forward";
      } else return "The door is not open!";
    }
  }

  @Override
  public String visitChest(Chest chest) {
    if (chest.isLocked()) {
      return "Chest is locked, <"
          + chest.getKey().getColor().toLowerCase()
          + "> key is needed to unlock!";
    } else {
      if (chest.isLooted()) {
        return "Someone else looted this chest before you!";
      } else {
        Inventory inventory = chest.loot();
        player.getInventory().addItems(inventory.getItemsAsMap());
        StringBuilder str = new StringBuilder();
        str.append("The items you acquired from this chest are:\n");
        str.append(player.getGold().getAmount() + " gold coins");
        player.getInventory().addItems(inventory.getItemsAsMap());
        chest.setLooted(true);
        return str.toString();
      }
    }
  }

  @Override
  public String visitPainting(Painting painting) {
    if (painting.hasHiddenKey()) {
      player.getInventory().getItemsAsMap().get(painting.getKey());
      painting.setCollected(true);
      return "The " + painting.getKey().toString() + " has looted!";
    } else return "There is nothing behind the painting";
  }

  @Override
  public String visitMirror(Mirror mirror) {
    if (mirror.hasHiddenKey()) {
      player.getInventory().getItemsAsMap().get(mirror.getKey());
      mirror.setCollected(true);
      return "The " + mirror.getKey().toString() + " has looted!";
    } else return "There is nothing behind the mirror!";
  }

  @Override
  public String visitSeller(Seller seller) {
    return "There is a seller";
  }
}
