package com.worldnavigator.game;

import static java.util.stream.Collectors.toList;

import com.worldnavigator.game.conflict.RockPaperScissorsFight;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerStatus;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Game {

  private final UUID uuid;
  private final String name;
  private final String owner;
  private final GameConfig gameConfig;
  private final LocalDateTime startedAt;
  private Map<String, Player> players;
  private String winner;
  private Map<Player, RockPaperScissorsFight> fightMap;

  public Game(
      UUID uuid, String name, String owner, GameConfig gameConfig, LocalDateTime startedAt) {
    this.name = name;
    this.owner = owner;
    this.uuid = UUID.fromString(getName());
    this.gameConfig = gameConfig;
    this.fightMap = new HashMap<>();
    this.players = new HashMap<>();
    this.startedAt = startedAt;
  }

  public void distributeGold(int amount) {
    List<Player> currentPlayers =
        this.players.values().stream()
            .filter(p -> p.getPlayerStatus() != PlayerStatus.LOST)
            .collect(toList());

    if (currentPlayers.isEmpty()) return;

    int goldForEach = amount / currentPlayers.size();
    currentPlayers.forEach(p -> p.getInventory().getGold().addGoldAmount(goldForEach));
  }

  public void setWinner(Player winner) {
    this.players.forEach((s, p) -> p.setPlayerStatus(PlayerStatus.LOST));
    winner.setPlayerStatus(PlayerStatus.WON);

    this.winner = winner.getUserName();
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

  public boolean isStarted() {
    return LocalDateTime.now().isAfter(startedAt);
  }

  public boolean isFinished() {
    return winner != null || isTimeout();
  }

  public boolean isTimeout() {
    return startedAt.plusMinutes(gameConfig.getTimeOut()).isBefore(LocalDateTime.now());
  }
}
