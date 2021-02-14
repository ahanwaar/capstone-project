package com.worldnavigator.game.player;

import com.worldnavigator.game.Game;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.player.Player;

public class PlayerContext {
    private final Game game;
    private final Player player;

    public PlayerContext(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom(){
    //TODO
    }

    public Wall getCurrentWall(){
        //TODO
    }

}
