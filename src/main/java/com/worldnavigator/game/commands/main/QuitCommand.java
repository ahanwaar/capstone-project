package com.worldnavigator.game.commands.main;

import com.worldnavigator.game.Game;
import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.exceptions.NoSuchItemException;
import com.worldnavigator.game.exceptions.NullItemException;
import com.worldnavigator.game.player.Player;

public class QuitCommand implements Command {

  @Override
  public String execute(Player player) throws NullItemException, NoSuchItemException {
    Game game = player.getGame();
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
}
