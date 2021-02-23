package com.worldnavigator.game.commands.navigation;

import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.player.Player;
import java.util.Objects;

public class TurnRightCommand implements Command {

  @Override
  public String execute(Player player) {
    Objects.requireNonNull(player);
    player.getLocation().setDirection(
        player
            .getLocation()
            .getDirection()
            .turnLeft());
    return "You turned right!";  }

  @Override
  public String getDescription() {
    return  "Use this command to turn right!";
  }

  @Override
  public String name() {
    return "turnRight";
  }
}
