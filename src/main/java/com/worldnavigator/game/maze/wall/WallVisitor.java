package com.worldnavigator.game.maze.wall;


import com.worldnavigator.game.exceptions.NoSuchItemException;
import com.worldnavigator.game.exceptions.NullItemException;
import com.worldnavigator.game.maze.wall.wallobjects.*;

public interface WallVisitor {

  String visitPlainWall(PlainWall plainWall);

  String visitDoor(Door door);

  String visitChest(Chest chest);

  String visitPainting(Painting painting);

  String visitMirror(Mirror mirror);

  String visitSeller(Seller seller);

}
