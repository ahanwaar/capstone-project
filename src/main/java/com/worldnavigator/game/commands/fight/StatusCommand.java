package com.worldnavigator.game.commands.fight;

import com.worldnavigator.game.Game;
import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.conflict.ConflictHandler;
import com.worldnavigator.game.conflict.RockPaperScissorsFight;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class StatusCommand implements Command {

    private final ConflictHandler handler;

    @Autowired
    public StatusCommand(ConflictHandler handler) {
        this.handler = handler;
    }

    @Override
    public String execute(Player player, String... args) {

        Game game = player.getGame();
        RockPaperScissorsFight fight = game.getFightByPlayer(player);

        return handler.status(player, fight);
    }

    @Override
    public boolean checkAvailability(Player player) {
        return player.getPlayerStatus() == PlayerStatus.FIGHTING;
    }

    @Override
    public String name() {
        return "fight:status";
    }

    @Override
    public String getDescription() {
        return "Gives fight status.";
    }
}
