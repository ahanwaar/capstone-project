package com.worldnavigator.game.commands.main.check;


import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.exceptions.NullItemException;
import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.player.Player;
import org.springframework.stereotype.Component;

@Component
public class CheckCommand implements Command {

  @Override
  public String name() {
    return "check";
  }

  @Override
  public String execute(Player player) {
    Wall wall = player.getCurrentWall();
    return wall.accept(new CheckVisitor(player));
  }


  @Override
  public String getDescription() {
    return "Use this command to check the in front wall object.";
  }
}
