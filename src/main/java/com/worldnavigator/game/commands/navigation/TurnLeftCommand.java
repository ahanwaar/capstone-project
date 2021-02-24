package com.worldnavigator.game.commands.navigation;

import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerStatus;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class TurnLeftCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Objects.requireNonNull(player);
    player.setDirection(player.getDirection().turnLeft());
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

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING;
  }
}
