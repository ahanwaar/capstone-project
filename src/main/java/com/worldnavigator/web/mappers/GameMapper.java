package com.worldnavigator.web.mappers;

import com.worldnavigator.game.Game;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.web.dto.GameInfo;
import com.worldnavigator.web.dto.MazeTemplateInfo;
import com.worldnavigator.web.dto.PlayerInfo;
import com.worldnavigator.web.entities.MazeTemplate;
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
                game.getGameConfig().getTimeOut(),
                game.getStartedAt(),
                game.isStarted(),
                game.isFinished()
        );
    }

    public PlayerInfo toPlayerInfo(Player player) {

        return new PlayerInfo(
                player.getPlayerStatus(),
                player.getUserName(),
                player.getInventory().getGold().getAmount(),
                player.getRoomIndex(),
                player.getDirection(),
                player.getInventory().getItems().keySet()
        );
    }

    public MazeTemplateInfo toMazeTemplateInfo(MazeTemplate mazeTemplate) {
        return new MazeTemplateInfo(
                mazeTemplate.getId(),
                mazeTemplate.getName()
        );
    }
}
