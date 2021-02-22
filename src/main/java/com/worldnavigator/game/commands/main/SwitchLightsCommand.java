package com.worldnavigator.game.commands.main;

import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.exceptions.NoSuchItemException;
import com.worldnavigator.game.exceptions.NullItemException;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.player.Player;

public class SwitchLightsCommand implements Command {


  @Override
  public String execute(Player player) throws NullItemException, NoSuchItemException {
    Room room = player.getCurrentRoom();
    if(room.hasLights()){
      room.switchLights();
      if(room.isLit()){
        return "The room is lit now!";
      }else return "The room is dark now!";
    }
    return "The room doesn't have lights!";
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public String name() {
    return null;
  }
}
