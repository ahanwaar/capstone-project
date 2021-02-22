package com.worldnavigator.game;

public class Gold {

  int amount;

  public Gold(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Gold amount can't be negative");
    }
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public void addGoldAmount(int amount) {
    this.amount += amount;
  }

  public void withdrawGoldAmount(int amount) {
    this.amount -= amount;
  }

  public String getName(){
    return "gold";
  }
}
