package com.worldnavigator.game.maze.items;

public interface ItemVisitor {

  String visitFlashlight(Flashlight flashlight);

  String visitKey(Key key);
}
