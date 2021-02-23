package com.worldnavigator.game.commands.navigation;

import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.maze.Direction;
import com.worldnavigator.game.player.Player;

public class MoveBackwardCommand implements Command {

  @Override
  public String execute(Player player){
    Direction oppositeDir = player.getLocation().getDirection().getOppositeDir();
    player.getLocation().setDirection(oppositeDir);
    MoveForwardCommand forwardCommand = new MoveForwardCommand();
    return forwardCommand.execute(player);
  }

  @Override
  public String getDescription() {
    return "use this command to move backward through open doors.";
  }

  @Override
  public String name() {
    return "moveBackward";
  }
}
