package com.worldnavigator.game.controls.commands.main;

import com.worldnavigator.game.controls.Command;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.walls.Lockable;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.player.Player;

public class UseKeyCommand implements Command {

  @Override
  public String name() {
    return "useKey";
  }

  @Override
  public String execute(Player player) {
    Room room = player.getCurrentRoom();
    Wall wall = player.getCurrentWall();

    Lockable lockable = (Lockable) wall;
    Key key = lockable.getKey();
    if (player.getInventory().containsItem(key.getName())) {
      if (lockable.isLocked()) {
        lockable.unLock(key);
        return String.format("The %s is now unlocked!", lockable);

      } else {
        return String.format("The %s is already unlocked!", lockable);
      }
    } else {
      return String.format("You don't have the required key to unlock the %s", lockable);
    }
  }

  @Override
  public String getDescription() {
    return "use this command to unlock doors and chests.";
  }
}
