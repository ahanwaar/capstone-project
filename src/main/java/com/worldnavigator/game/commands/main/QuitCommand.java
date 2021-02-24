package com.worldnavigator.game.commands.main;

import com.worldnavigator.game.Game;
import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerStatus;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class QuitCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Objects.requireNonNull(player);
    Game game = player.getGame();
    player.setPlayerStatus(PlayerStatus.LOST);
    game.distributeGold(player.getInventory().convertToGoldAmount());
    player.getCurrentRoom().removePlayer(player);
    game.getPlayers().remove(player);
    return "You quit the game";
  }

  @Override
  public String getDescription() {
    return "use this command to quit the game";
  }

  @Override
  public String name() {
    return "quit";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING
        || player.getPlayerStatus() == PlayerStatus.FIGHTING;
  }
}
