package com.worldnavigator.game.maze.wall.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.PriceList;
import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.maze.wall.WallVisitor;

public class Seller extends Wall {

  private final PriceList pricesList;

  @JsonCreator
  public Seller(@JsonProperty("prices") PriceList pricesList) {
    this.pricesList = pricesList;
  }

  public PriceList getPricesList() {
    return pricesList;
  }

  public Integer getItemPrice(String itemName) {
    return pricesList.getItemPrice(itemName);
  }

  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitSeller(this);
  }
}
