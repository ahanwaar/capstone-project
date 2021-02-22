package com.worldnavigator.game.maze.wall;

import com.worldnavigator.game.exceptions.NullItemException;

public abstract class Wall {

  public abstract String accept(WallVisitor visitor);
}
