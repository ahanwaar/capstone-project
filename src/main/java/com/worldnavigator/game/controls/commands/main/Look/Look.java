package com.worldnavigator.game.controls.commands.main.Look;

import com.worldnavigator.game.controls.Command;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.walls.Wall;

public class Look implements Command {
  @Override
  public String name() {
    return "look";
  }

  @Override
  public String execute(Player player) {
    Room room = player.getCurrentRoom();
    Wall wall = player.getCurrentWall();

    if (room.isLit()) {
      return wall.accept(new LookVisitor());
    } else return "The room is dark!";
  }

  @Override
  public String getDescription() {
    return "Use this command to see what is in front of you.";
  }
}
