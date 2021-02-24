package com.worldnavigator.webapp.mappers;

import com.worldnavigator.game.Game;
import com.worldnavigator.game.Player;
import com.worldnavigator.webapp.dto.GameInfo;
import com.worldnavigator.webapp.dto.MazeTemplateInfo;
import com.worldnavigator.webapp.dto.PlayerInfo;
import com.worldnavigator.webapp.entities.MazeTemplate;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public GameInfo toGameInfo(Game game) {
        return new GameInfo(
                game.getUuid(),
                game.getName(),
                game.getOwner(),
                game.getWinner(),
                game.getPlayers().size(),
                game.getTimeout(),
                game.getStartedAt(),
                game.isStarted(),
                game.isFinished()
        );
    }

    public PlayerInfo toPlayerInfo(Player player) {

        return new PlayerInfo(
                player.getMode(),
                player.getUsername(),
                player.getGold(),
                player.getLocation(),
                player.getDirection(),
                player.getItems().keySet()
        );
    }

    public MazeTemplateInfo toMazeTemplateInfo(MazeTemplate mazeTemplate) {
        return new MazeTemplateInfo(
                mazeTemplate.getId(),
                mazeTemplate.getName()
        );
    }
}
