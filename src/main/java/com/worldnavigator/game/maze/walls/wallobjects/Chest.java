package com.worldnavigator.game.maze.walls.wallobjects;

import com.fasterxml.jackson.annotation.*;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.walls.*;
import com.worldnavigator.game.player.Inventory;
import java.util.Objects;

public class Chest extends Wall implements Lockable {

  private final Key lockingKey;
  private final Inventory inventory;
  private boolean isLooted;
  private boolean isLocked;
  private boolean isOpen;

  @JsonCreator
  private Chest(
      @JsonProperty("key") Key key,
      @JsonProperty("inventory") Inventory inventory
  ) {
    this.lockingKey = key;
    this.inventory = inventory;
    this.isLocked = true;
    this.isOpen = false;
    this.isLooted = false;
  }

  //TODO this is edited, added static factory method

  public static Chest createChest(Key key, Inventory inventory) {
    return new Chest(key,inventory);
  }

  @Override
  public boolean unLock(
      Key key
  ) {
    if (Objects.equals(this.lockingKey, key)) {
      isLocked = !isLocked;
      return true;
    }else return false;
  }

  @Override
  public boolean isLocked() {
    return this.isLocked;
  }

  @Override
  public boolean isOpen() {
    return isOpen;
  }

  @Override
  public void open() {
    if (!isLocked) {
      isOpen = true;
    }
  }

  @Override
  public Key getKey() {
    return this.lockingKey;
  }

  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitChest(this);
  }

  public boolean isLooted() {
    return this.isLooted;
  }

  public void setLooted(boolean looted) {
    this.isLooted = looted;
  }

  public Inventory loot() {
    return this.inventory;
  }

  @Override
  public String toString() {
    return "chest";
  }
}
