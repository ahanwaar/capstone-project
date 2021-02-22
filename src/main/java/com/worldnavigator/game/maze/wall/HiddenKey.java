package com.worldnavigator.game.maze.wall;

import com.worldnavigator.game.exceptions.NullItemException;
import com.worldnavigator.game.maze.items.Key;

public interface HiddenKey {

  boolean hasHiddenKey();

  Key grabKey() throws NullItemException;

}
