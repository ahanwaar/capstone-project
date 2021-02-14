package com.worldnavigator.game.maze;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.maze.walls.Wall;

import java.util.*;

public class Room {

    private final int index;
    private final boolean hasLights;
    private boolean isLit;
    private final Map<Direction, Wall> walls;
    private final Deque<Player> players;

    @JsonCreator
    public Room(
            @JsonProperty("index") int index,
            @JsonProperty("hasLights") boolean hasLights,
            @JsonProperty("isLit") boolean isLit,
            @JsonProperty("walls") Map<Direction, Wall> walls
    ) {
        this.index = index;
        this.hasLights = hasLights;
        this.isLit = isLit;
        this.walls = Objects.requireNonNull(walls);
        this.players = new ArrayDeque<>();
    }

    public int getIndex() {
        return index;
    }

    public Map<Direction, Wall> getWalls() {
        return walls;
    }

    public Wall getWall(Direction direction){
        return this.walls.get(direction);
    }

    public boolean switchLights() {
        if(hasLights){
            this.isLit = !this.isLit;
        }
        return this.isLit;
    }

    public boolean isLit(){
        return this.isLit;
    }

    public Deque<Player> getPlayers() {
        return players;
    }

    public boolean isCrowded(){
        return this.players.size() == 2;
    }

    public boolean isEmpty(){
        return this.players.isEmpty();
    }

    public void addPlayer(Player player){
        if(!isCrowded()){
            this.players.addLast(player);
        }
    }

    public void kickPlayer(Player player){
        this.players.remove(player);
    }
}
