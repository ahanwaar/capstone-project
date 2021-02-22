package com.worldnavigator.game.maze.wall;

import com.worldnavigator.game.exceptions.NullLockException;
import com.worldnavigator.game.maze.items.Key;
import java.util.Objects;

public class Lock  {

  private final Key lockingKey;
  private boolean locked;

  public Lock(Key lockingKey) {
    this.lockingKey = Objects.requireNonNull(lockingKey);
    locked = !lockingKey.equals(Key.NULL);
  }

  public boolean unLock(Key key) {
    if(lockingKey.equals(Key.NULL)){
      throw new NullLockException();
    }else if (this.lockingKey.equals(key)) {
      locked = !locked;
      return true;
    }else {
      return false;
    }
  }

  public boolean isLocked() {
    return this.locked;
  }

  public Key getKey() {
    return this.lockingKey;
  }
}
