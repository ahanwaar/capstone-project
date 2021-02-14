package com.worldnavigator.game.maze.walls;

import com.worldnavigator.game.maze.items.Key;

public interface Lockable {
    public void unLock(Key key);
    public boolean isLocked();
    public boolean isOpen();
    public void open();
    public Key getKey();
}
