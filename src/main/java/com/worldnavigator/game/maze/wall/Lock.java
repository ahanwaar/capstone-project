package com.worldnavigator.game.maze.wall;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.exceptions.NullLockException;
import com.worldnavigator.game.maze.items.Key;
import java.util.Objects;

public class Lock {

  private final Key lockingKey;
  private boolean locked;

  @JsonCreator
  public Lock(
      @JsonProperty("key") Key lockingKey
  ) {
    this.lockingKey = Objects.requireNonNull(lockingKey);
    locked = !lockingKey.equals(Key.NULL);
  }

  public boolean unLock(Key key) {
    if (lockingKey.equals(Key.NULL)) {
      throw new NullLockException();
    } else if (this.lockingKey.equals(key)) {
      locked = !locked;
      return true;
    } else {
      return false;
    }
  }

  @JsonProperty(value = "locked")
  public boolean isLocked() {
    return this.locked;
  }

  public Key getKey() {
    return this.lockingKey;
  }
}
