package com.worldnavigator.game.maze.wall.wallobjects;


import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.maze.wall.WallVisitor;
import java.util.Map;

public class Seller extends Wall {

  private final Map<String, Integer> pricesList;


  public Seller( Map<String, Integer> pricesList) {
    this.pricesList = pricesList;
  }

  public Map<String, Integer> getPricesList() {
    return pricesList;
  }

  public Integer getItemPrice(String itemName) {
    return pricesList.get(itemName);
  }

  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitSeller(this);
  }
}
