package com.worldnavigator.game.commands.navigation;

import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerStatus;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class TurnRightCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Objects.requireNonNull(player);
    player.getLocation().setDirection(
        player
            .getLocation()
            .getDirection()
            .turnLeft());
    return "You turned right!";
  }

  @Override
  public String getDescription() {
    return "Use this command to turn right!";
  }

  @Override
  public String name() {
    return "turnRight";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING;
  }
}
