package com.worldnavigator.game.controls;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.player.Player;

public interface Command {
  //TODO check validity

  String execute(Player player);

  @JsonProperty("description")
  String getDescription();

  @JsonProperty("name")
  String name();


}
