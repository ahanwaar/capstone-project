package com.worldnavigator.game;

import com.worldnavigator.game.fight.Fight;
import com.worldnavigator.game.maze.Maze;
import com.worldnavigator.game.player.Player;
import lombok.Getter;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Getter
public class Game {
    private final int id;
    private final String name;
    private final Maze maze;
    private final GameConfig gameConfig;
    private List<Player> players;
    private Map<Player, Fight> fights;
    private String winner;
    private LocalTime startingTime;

    public Game(int id, Maze maze,GameConfig gameConfig) {
        this.id = id;
        this.name = "Game" + id;
        this.maze = maze;
        this.gameConfig = gameConfig;
    }

    public void start(List<Player> players){
        setPlayers(players);
        this.startingTime =  LocalTime.now(ZoneId.systemDefault());
    }

    public void setPlayers(List<Player> players){
        players.forEach(
                player -> player.getGold().addGoldAmount(
                        gameConfig.getInitialGoldAmount()
                )
        );
       //TODO DISTRIBUTE PLAYERS

    }


    public void setWinner(Player winner) {
        this.winner = winner.getUserName();
    }
}
