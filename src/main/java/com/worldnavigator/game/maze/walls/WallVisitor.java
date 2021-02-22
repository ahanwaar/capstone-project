package com.worldnavigator.game.maze.walls;

import com.worldnavigator.game.maze.walls.wallobjects.Chest;
import com.worldnavigator.game.maze.walls.wallobjects.Door;
import com.worldnavigator.game.maze.walls.wallobjects.Mirror;
import com.worldnavigator.game.maze.walls.wallobjects.Painting;
import com.worldnavigator.game.maze.walls.wallobjects.PlainWall;
import com.worldnavigator.game.maze.walls.wallobjects.Seller;

public interface WallVisitor {

  String visitPlainWall(PlainWall plainWall);

  String visitDoor(Door door);

  String visitChest(Chest chest);

  String visitPainting(Painting painting);

  String visitMirror(Mirror mirror);

  String visitSeller(Seller seller);
}
