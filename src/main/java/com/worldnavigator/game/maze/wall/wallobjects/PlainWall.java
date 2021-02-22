package com.worldnavigator.game.maze.wall.wallobjects;


import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.maze.wall.WallVisitor;

public class PlainWall extends Wall {

  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitPlainWall(this);
  }
}
