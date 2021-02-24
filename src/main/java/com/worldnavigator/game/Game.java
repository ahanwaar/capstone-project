package com.worldnavigator.game;

import static java.util.stream.Collectors.toList;

import com.worldnavigator.game.conflict.RockPaperScissorsFight;
import com.worldnavigator.game.maze.Maze;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerStatus;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
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
  private Map<String,Player> players;
  private String winner;
  private LocalTime startingTime;
  private GameStatus gameStatus;
  private Map<Player, RockPaperScissorsFight> fightMap;

  public Game(int id, Maze maze, GameConfig gameConfig) {
    this.id = id;
    this.name = "Game" + id;
    this.maze = maze;
    this.gameConfig = gameConfig;
    this.gameStatus = GameStatus.LOADING;
    this.fightMap = new HashMap<>();
    this.players = new HashMap<>();
    this.startingTime = LocalTime.now();
  }

  public void start(Map<String,Player> players) {
    this.players.putAll(players);
    equipPlayers();
    this.startingTime = LocalTime.now(ZoneId.systemDefault());
    this.gameStatus = GameStatus.STARTED;
  }

  public void equipPlayers() {
    distributeGold(gameConfig.getInitialGoldAmount());
  }

  public void distributeGold(int amount){
    List<Player> currentPlayers = this.players.values().stream()
        .filter(p -> p.getPlayerStatus() != PlayerStatus.LOST)
        .collect(toList());

    if(currentPlayers.isEmpty())
      return;

    int goldForEach = amount / currentPlayers.size();
    currentPlayers.forEach(p -> p.getInventory().getGold().addGoldAmount(goldForEach));
  }

  public void setWinner(Player winner) {
    this.players.forEach((s, p) -> p.setPlayerStatus(PlayerStatus.LOST));
    winner.setPlayerStatus(PlayerStatus.WON);

    this.winner = winner.getUserName();
    gameStatus = GameStatus.FINISHED;
  }

  public Player getPlayer(String username) {
    Player player = players.get(username);
    return Objects.requireNonNull(player);
  }

  public void removePlayer(Player player) {
    Objects.requireNonNull(player);
    players.remove(player);
  }

  public void addFight(RockPaperScissorsFight fight) {
    Objects.requireNonNull(fight);
    fight.getPlayers().forEach(player -> fightMap.put(player, fight));
  }

  public RockPaperScissorsFight getFightByPlayer(Player player) {
    return fightMap.get(player);
  }
}
