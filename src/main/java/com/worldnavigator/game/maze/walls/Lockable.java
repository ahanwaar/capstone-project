package com.worldnavigator.game.maze.walls;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.maze.items.Key;

public interface Lockable {

  public boolean unLock(Key key);

  @JsonProperty(value = "isLocked")
  public boolean isLocked();

  @JsonProperty(value = "isOpen")
  public boolean isOpen();

  public void open();

  public Key getKey();
}
