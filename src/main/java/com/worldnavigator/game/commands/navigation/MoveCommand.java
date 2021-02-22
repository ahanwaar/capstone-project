package com.worldnavigator.game.commands.navigation;

import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.conflict.ConflictHandler;
import com.worldnavigator.game.conflict.ConflictStatus;
import com.worldnavigator.game.maze.Direction;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.maze.wall.wallobjects.Door;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerLocation;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class MoveCommand implements Command {

  Player player;

  @Override
  public String execute(Player player) {
    Objects.requireNonNull(player);
    this.player = player;

    Direction direction = player.getLocation().getDirection();
    Wall wall = player.getCurrentRoom().getWall(direction);

    if (wall instanceof Door) {
      Door door = (Door) wall;
      if (!door.getLock().isLocked()) {
        if (door.isOpen()) {
          if(door.isMagicalDoor()){
            player.getGame().setWinner(player);
            return "You won! you entered a magical door.";
          }
          moveThroughDoor(door,player);
        }
      }return "The door is locked, you need to unlock it first";
    }
    return "You can't move, you are not in front a door";
  }

  public String moveThroughDoor(Door door, Player player){
    Room currentRoom = player.getCurrentRoom();
    Room nextRoom = door.getNextRoom(currentRoom);
    synchronized (nextRoom){
      if (nextRoom.isCrowded()) {
        return "You can't enter the room, there is a fight.";
      }
      currentRoom.removePlayer(player);
      return enterUnCrowdedRoom(player,nextRoom);
    }
  }

  public String enterUnCrowdedRoom(Player player, Room nextRoom) {
    nextRoom.addPlayer(player);
    player.setLocation(new PlayerLocation(nextRoom.getIndex(), player.getLocation().getDirection()));
    if (nextRoom.isEmpty()) {
      return "You are in the next room.";
    }
    return startConflict(new ConflictHandler(nextRoom.getPlayers()));
  }

  public String startConflict(ConflictHandler conflictHandler){
    if (conflictHandler.getPrimaryFightStatus() == ConflictStatus.TIE) {
      return "You got a tie, you have to play \"rock paper scissor\" game.";
    }
    else if(conflictHandler.getPrimaryFightStatus() == ConflictStatus.FIRST_WON){
        return "You lost; your opponent inventory level is higher than yours.";
    }else player.setPlayerStatus();

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
