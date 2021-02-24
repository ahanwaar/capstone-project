package com.worldnavigator.game.commands.main;

import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

@Component
public class UseFlashlightCommand implements Command {

  @Override
  public String name() {
    return "useFlashlight";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING;
  }

  @Override
  public String execute(Player player, String... args) {
    Room room = player.getCurrentRoom();
    if (player.getInventory().containsItem("flashlight")) {
      if (room.isLit()) {
        return "The room is already lit! save your batteries for harder times!";
      } else {
        room.switchLights();
        return "The room is lit rn!";
      }
    } else {
      return "You don't have a flashlight!";
    }
  }

  @Override
  public String getDescription() {
    return "use this command to use the flashlight if you have it.";
  }
}
