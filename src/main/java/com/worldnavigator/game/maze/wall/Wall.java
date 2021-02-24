package com.worldnavigator.game.maze.wall;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.worldnavigator.game.maze.wall.wallobjects.Chest;
import com.worldnavigator.game.maze.wall.wallobjects.Door;
import com.worldnavigator.game.maze.wall.wallobjects.Mirror;
import com.worldnavigator.game.maze.wall.wallobjects.Painting;
import com.worldnavigator.game.maze.wall.wallobjects.PlainWall;
import com.worldnavigator.game.maze.wall.wallobjects.Seller;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PlainWall.class, name = "plainWall"),
    @JsonSubTypes.Type(value = Door.class, name = "door"),
    @JsonSubTypes.Type(value = Mirror.class, name = "mirror"),
    @JsonSubTypes.Type(value = Painting.class, name = "painting"),
    @JsonSubTypes.Type(value = Seller.class, name = "seller"),
    @JsonSubTypes.Type(value = Chest.class, name = "chest")
})
public abstract class Wall {

  public abstract String accept(WallVisitor visitor);
}
