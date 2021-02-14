package com.worldnavigator.game.maze.walls;

import com.worldnavigator.game.maze.walls.wallobjects.*;

public interface WallVisitor {
    public String visitPlainWall(PlainWall plainWall);
    public String visitDoor(Door door);
    public String visitChest(Chest chest);
    public String visitPainting(Painting painting);
    public String visitMirror(Mirror mirror);
    public String visitSeller(Seller seller);
}
