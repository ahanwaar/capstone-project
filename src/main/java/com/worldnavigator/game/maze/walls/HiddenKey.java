package com.worldnavigator.game.maze.walls;

import com.worldnavigator.game.maze.items.Key;

import java.util.Optional;

public interface HiddenKey {

  public boolean hasHiddenKey();

  public void setCollected(boolean hasHiddenKey);

  public Optional<Key> getKey();
}
