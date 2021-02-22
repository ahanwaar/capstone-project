package com.worldnavigator.game.controls.commands.main.check;

import com.worldnavigator.game.controls.Command;
import com.worldnavigator.game.maze.walls.Wall;
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
