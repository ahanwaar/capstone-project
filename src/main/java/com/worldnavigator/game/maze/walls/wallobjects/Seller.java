package com.worldnavigator.game.maze.walls.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.visitors.WallVisitor;

import java.util.Map;

public class Seller extends Wall {
    private final Map<String,Integer> pricesList;

    @JsonCreator
    public Seller(
            @JsonProperty("prices") Map<String, Integer> pricesList) {
        this.pricesList = pricesList;
    }

    public Map<String, Integer> getPricesList() {
        return pricesList;
    }

    public Integer getItemPrice(String itemName){
        return pricesList.get(itemName);
    }

    @Override
    public String accept(WallVisitor visitor) {
        return visitor.visitSeller(this);
    }
}
