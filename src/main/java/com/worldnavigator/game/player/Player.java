package com.worldnavigator.game.player;

public class Player {
  private final String userName;
  private PlayerLocation location;
  private PlayerInventory inventory;
  private PlayerStatus playerStatus;

  public Player(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public PlayerLocation getLocation() {
    return location;
  }

  public PlayerInventory getInventory() {
    return this.inventory;
  }

  public PlayerStatus getPlayerStatus() {
    return playerStatus;
  }

  public void setLocation(PlayerLocation location) {
    this.location = location;
  }

  public void setInventory(PlayerInventory inventory) {
    this.inventory = inventory;
  }

  public void setPlayerStatus(PlayerStatus playerStatus) {
    this.playerStatus = playerStatus;
  }
}
