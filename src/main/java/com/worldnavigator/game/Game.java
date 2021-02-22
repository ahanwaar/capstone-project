package com.worldnavigator.game;

import com.worldnavigator.game.conflict.RockPaperScissorsGame;
import com.worldnavigator.game.maze.Maze;
import com.worldnavigator.game.player.Player;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Game {

  private final int id;
  private final String name;
  private final Maze maze;
  private final GameConfig gameConfig;
  private List<Player> players;
  private String winner;
  private LocalTime startingTime;
  private GameState gameState;

  public Game(int id, Maze maze, GameConfig gameConfig) {
    this.id = id;
    this.name = "Game" + id;
    this.maze = maze;
    this.gameConfig = gameConfig;
    this.gameState = GameState.LOADING;

  }

  public void start(List<Player> players) {
    this.players.addAll(players);
    equipPlayers();
    this.startingTime = LocalTime.now(ZoneId.systemDefault());
    this.gameState = GameState.STARTED;
  }

  public void equipPlayers() {
    distributeGold(gameConfig.getInitialGoldAmount());
  }

  public void distributeGold(int amount){
    players.forEach(player ->
        player.getInventory().getGold().addGoldAmount(amount));
  }

  public void setWinner(Player winner) {
    this.winner = winner.getUserName();
    this.gameState = GameState.FINISHED;
  }
  public void removePlayer(Player player){
    Objects.requireNonNull(player);
    players.remove(player);
  }


}
