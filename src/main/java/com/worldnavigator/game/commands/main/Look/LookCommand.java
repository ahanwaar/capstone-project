package com.worldnavigator.game.commands.main.Look;


import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

@Component
public class LookCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Room room = player.getCurrentRoom();
    Wall wall = player.getCurrentWall();

    if (room.isLit()) {
      return wall.accept(new LookVisitor());
    } else {
      return "The room is dark!";
    }
  }

  @Override
  public String name() {
    return "look";
  }


  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING;
  }

  @Override
  public String getDescription() {
    return "Use this command to see what is in front of you.";
  }
}
