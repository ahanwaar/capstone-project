package com.worldnavigator.game.controls;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.player.Player;

public interface Command {
    @JsonProperty("name")
    public String name();

    public String execute(Player player);

    @JsonProperty("description")
    public String getDescription();
}
