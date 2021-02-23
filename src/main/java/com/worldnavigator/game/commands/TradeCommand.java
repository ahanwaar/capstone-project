package com.worldnavigator.game.commands;

import com.worldnavigator.game.player.Player;

public interface TradeCommand {


  String execute(Player player,String itemName);

  String getDescription();

  String name();

}
