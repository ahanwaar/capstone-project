package com.worldnavigator.game.maze.walls;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.worldnavigator.game.maze.walls.wallobjects.Chest;
import com.worldnavigator.game.maze.walls.wallobjects.Door;
import com.worldnavigator.game.maze.walls.wallobjects.Mirror;
import com.worldnavigator.game.maze.walls.wallobjects.Painting;
import com.worldnavigator.game.maze.walls.wallobjects.PlainWall;
import com.worldnavigator.game.maze.walls.wallobjects.Seller;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PlainWall.class, name = "plain"),
    @JsonSubTypes.Type(value = Door.class, name = "door"),
    @JsonSubTypes.Type(value = Mirror.class, name = "mirror"),
    @JsonSubTypes.Type(value = Painting.class, name = "painting"),
    @JsonSubTypes.Type(value = Seller.class, name = "seller"),
    @JsonSubTypes.Type(value = Chest.class, name = "chest")
})
public abstract class Wall {

  public abstract String accept(WallVisitor visitor);
}
