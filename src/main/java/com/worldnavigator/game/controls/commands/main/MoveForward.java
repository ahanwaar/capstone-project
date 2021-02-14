package com.worldnavigator.game.controls.commands.main;

import com.worldnavigator.game.controls.Command;
import com.worldnavigator.game.maze.Direction;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.items.Item;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.maze.walls.wallobjects.Door;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerLocation;

import java.util.Map;
import java.util.Optional;

public class MoveForward implements Command {

    @Override
    public String name() {
        return "move";
    }

    @Override
    public String execute(Player player) {
        Direction direction = player.getLocation().getDirection();

        Wall wall = player.getCurrentRoom().getWall(direction);

        if(wall instanceof Door) {
            Door door = (Door) wall;
            if(!door.isLocked()){
                if(door.isOpen()){
                    if(door.getConnectedRooms().size() < 1) {
                        player.getGame().setWinner(player);
                        return "You won! you entered a magic door.";
                    }

                    Room currentRoom = player.getCurrentRoom();
                    Room nextRoom = door.getConnectedRooms().get(1);

                    if(nextRoom.isCrowded())
                        return "You can't go to the room there is a fight.";

                    boolean empty = nextRoom.isEmpty();

                    currentRoom.kickPlayer(player);
                    nextRoom.addPlayer(player);
                    player.setLocation(new PlayerLocation(nextRoom.getIndex(),direction));

                    if(empty) {
                        return "You are in the next room.";
                    }

                    Player opponent = nextRoom.getPlayers().getFirst();
                    return fight(context.getGame(), player, opponent);

                }
            }

            if(optional.isPresent()) {
                Lock lock = optional.get();

                if(lock.isOpen())
                    return move(context, door);
                else
                    return "The door is not open you can't go though.";

            } else {
                return move(context, door);
            }

        } else {
            return "You can't move " + args[0] + " there is no door.";
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
