package com.worldnavigator.game.commands.navigation;

import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.maze.Direction;
import com.worldnavigator.game.player.Player;
import java.util.Objects;

public class TurnLeftCommand implements Command {

  @Override
  public String execute(Player player) {
    Objects.requireNonNull(player);
    player.getLocation().setDirection(
        player
            .getLocation()
            .getDirection()
            .turnLeft());
    return "You turned left!";
  }

  @Override
  public String getDescription() {
    return "Use this command to turn left!";
  }

  @Override
  public String name() {
    return "turnLeft";
  }
}
