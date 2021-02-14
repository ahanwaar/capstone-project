package com.worldnavigator.game.controls.commands.main.Look;

import com.worldnavigator.game.maze.walls.wallobjects.*;
import com.worldnavigator.game.maze.walls.WallVisitor;

public class LookVisitor implements WallVisitor {

  @Override
  public String visitPlainWall(PlainWall plainWall) {
    return "You are facing a wall!";
  }

  @Override
  public String visitDoor(Door door) {
    return "You are facing a door!";
  }

  @Override
  public String visitChest(Chest chest) {
    return "You are facing a chest!";
  }

  @Override
  public String visitPainting(Painting painting) {
    return "You are facing a painting!";
  }

  @Override
  public String visitMirror(Mirror mirror) {
    return "You see a silhouette of you!";
  }

  @Override
  public String visitSeller(Seller seller) {
    return "You are facing a seller!";
  }
}
