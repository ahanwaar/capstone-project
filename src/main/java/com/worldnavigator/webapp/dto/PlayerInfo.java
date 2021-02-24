package com.worldnavigator.webapp.dto;

import com.worldnavigator.game.maze.Direction;
import com.worldnavigator.game.player.PlayerStatus;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerInfo {

    private final PlayerStatus status;

    private final String username;

    private final int gold;

    private final int location;

    private final Direction direction;

    private final Set<String> items;
}
