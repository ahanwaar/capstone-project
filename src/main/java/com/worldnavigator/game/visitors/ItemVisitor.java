package com.worldnavigator.game.visitors;

import com.worldnavigator.game.maze.items.Flashlight;
import com.worldnavigator.game.maze.items.Key;

public interface ItemVisitor {

    String visitFlashlight(Flashlight flashlight);

    String visitKey(Key key);
}
