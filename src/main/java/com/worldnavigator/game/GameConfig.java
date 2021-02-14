package com.worldnavigator.game;

import com.worldnavigator.game.player.PlayerInventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class GameConfig {
    private final int initialGoldAmount;
    private final int timeOut;
    private final Map<String,Integer> prices;
}
