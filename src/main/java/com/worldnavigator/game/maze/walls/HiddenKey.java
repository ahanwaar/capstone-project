package com.worldnavigator.game.maze.walls;

import com.worldnavigator.game.maze.items.Key;
import java.util.Optional;

public interface HiddenKey {

  boolean hasHiddenKey();

  void setCollected(boolean hasHiddenKey);

  Optional<Key> getKey();
}
