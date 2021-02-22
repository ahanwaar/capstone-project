package com.worldnavigator.game.maze;


import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.player.Player;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;

public class Room {

  private final int index;
  private final boolean hasLights;
  private final Map<Direction, Wall> walls;
  private final Deque<Player> players;
  private boolean isLit;


  public Room(int index, boolean hasLights, Map<Direction, Wall> walls) {
    this.index = index;
    this.hasLights = hasLights;
    this.isLit = false;
    this.walls = Objects.requireNonNull(walls);
    this.players = new ArrayDeque<>();
  }

  public int getIndex() {
    return index;
  }

  public Map<Direction, Wall> getWalls() {
    return walls;
  }

  public Wall getWall(Direction direction) {
    return this.walls.get(direction);
  }

  public void switchLights() {
    this.isLit = !this.isLit;
  }

  public boolean hasLights() {
    return hasLights;
  }

  public boolean isLit() {
    return this.isLit;
  }

  public Deque<Player> getPlayers() {
    return players;
  }

  public boolean isCrowded() {
    return this.players.size() == 2;
  }

  public boolean isEmpty() {
    return this.players.size() == 0;
  }

  public void addPlayer(Player player) {
    if (!isCrowded()) {
      this.players.addLast(player);
    }
  }

  public void removePlayer(Player player) {
    if(players.getFirst().equals(player)){
      players.removeFirst();
    }else if(players.getLast().equals(player)){
      players.removeLast();
    }
  }

}
