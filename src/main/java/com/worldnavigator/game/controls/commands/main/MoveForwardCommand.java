package com.worldnavigator.game.controls.commands.main;

import com.worldnavigator.game.controls.Command;
import com.worldnavigator.game.maze.Direction;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.maze.walls.wallobjects.Door;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerLocation;
import org.springframework.stereotype.Component;

@Component
public class MoveForwardCommand implements Command {

  @Override
  public String execute(Player player) {
    Direction direction = player.getLocation().getDirection();
    Wall wall = player.getCurrentRoom().getWall(direction);
    if (wall instanceof Door) {
      Door door = (Door) wall;
      if (!door.isLocked()) {
        if (door.isOpen()) {
          if (door.isMagicalDoor()) {
            player.getGame().setWinner(player);
            return "You won! you entered a magical door.";
          }else {
            Room currentRoom = player.getCurrentRoom();
            Room nextRoom = door.getConnectedRooms().get(1);
            if (nextRoom.isCrowded()) {
              return "You can't enter the room, there is a fight.";
            }else if (nextRoom.isEmpty()) {
              currentRoom.removePlayer(player);
              nextRoom.addPlayer(player);
              player.setLocation(new PlayerLocation(nextRoom.getIndex(), direction));
              return "You are in the next room.";

              //if(nextRoom.getPlayers().size() > 1);

            }
          }
        }
      }
    }
    return null;
  }

  @Override
  public String name() {
    return "moveForward";
  }

  @Override
  public String getDescription() {
    return null;
  }
}
