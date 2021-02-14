package com.worldnavigator.game.maze.walls.wallobjects;

import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.maze.walls.WallVisitor;

public class PlainWall extends Wall {

    @Override
    public String accept(WallVisitor visitor) {
        return visitor.visitPlainWall(this);
    }
}
