package com.worldnavigator.game.controls.commands.main.check;

import com.worldnavigator.game.controls.Command;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.maze.walls.Wall;
import org.springframework.stereotype.Component;

@Component
public class Check implements Command {

  @Override
  public String name() {
    return "check";
  }

  @Override
  public String execute(
          Player player
  ) {
      Wall wall = player.getCurrentWall();
    return wall.accept(
            new CheckVisitor(
                    player));
  }

  @Override
  public String getDescription() {
    return null;
  }
}
