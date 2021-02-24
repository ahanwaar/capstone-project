package com.worldnavigator.game.commands.main;

import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.wall.Lockable;
import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

@Component
public class UseKeyCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Room room = player.getCurrentRoom();
    Wall wall = player.getCurrentWall();

    Lockable lockable = (Lockable) wall;
    Key key = lockable.getLock().getKey();
    if (player.getInventory().containsItem(key.getName())) {
      if (lockable.getLock().isLocked()) {
        lockable.getLock().unLock(key);
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

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING;
  }

  @Override
  public String name() {
    return "useKey";
  }
}
