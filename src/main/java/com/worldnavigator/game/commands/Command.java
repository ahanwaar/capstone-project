package com.worldnavigator.game.commands;

import com.worldnavigator.game.exceptions.NoSuchItemException;
import com.worldnavigator.game.exceptions.NullItemException;
import com.worldnavigator.game.player.Player;

public interface Command {
  //TODO check validity

  String execute(Player player) throws NullItemException, NoSuchItemException;

  String getDescription();

  String name();


}
