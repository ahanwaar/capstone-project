package com.worldnavigator.game.commands.navigation;

import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.maze.Direction;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.maze.wall.wallobjects.Door;
import com.worldnavigator.game.player.Player;
import org.springframework.stereotype.Component;

@Component
public class MoveBackwardCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Direction oppositeDir = player.getDirection().getOppositeDir();
    player.setDirection(oppositeDir);
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

  @Override
  public boolean checkAvailability(Player player) {
    Room room = player.getCurrentRoom();
    Wall oppositeWall = room.getWall(player.getDirection().getOppositeDir());
    return oppositeWall instanceof Door;
  }
}
