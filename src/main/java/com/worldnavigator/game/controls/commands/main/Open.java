package com.worldnavigator.game.controls.commands.main;

import com.worldnavigator.game.controls.Command;
import com.worldnavigator.game.maze.walls.Lockable;
import com.worldnavigator.game.player.Player;

import java.util.Optional;

public class Open implements Command {
    @Override
    public String name() {
        return "open";
    }

    @Override
    public String execute(Player player) {
        Lockable lockable = (Lockable) player.getCurrentRoom();

        if (lockable.isLocked()) {

            if (lockable.isLocked()) {
                return String.format("The %s is locked, you need a %s to unlock it!", lockable, lockable.getKey());

            } else {

                if (lockable.isOpen()) {
                    return String.format("The %s is already open!", lockable);
                } else {
                    lockable.open();
                    return String.format("The %s is now open!", lockable);
                }
            }

        } else {
            return String.format("The %s doesn't have a lock!", lockable);
        }
    }

    @Override
    public String getDescription() {
        return "use this command to open unlocked doors or chests";
    }
}
