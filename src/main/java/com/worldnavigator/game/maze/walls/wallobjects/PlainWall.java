package com.worldnavigator.game.maze.walls.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.visitors.WallVisitor;

public class PlainWall extends Wall {

    @Override
    public String accept(WallVisitor visitor) {
        return visitor.visitPlainWall(this);
    }
}
