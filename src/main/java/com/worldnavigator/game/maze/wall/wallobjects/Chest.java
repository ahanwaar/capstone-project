package com.worldnavigator.game.maze.wall.wallobjects;

import com.worldnavigator.game.maze.Inventory;
import com.worldnavigator.game.maze.wall.Lock;
import com.worldnavigator.game.maze.wall.Lockable;
import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.maze.wall.WallVisitor;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chest extends Wall implements Lockable {

  private final Inventory inventory;
  private Lock lock;
  private boolean looted;

  public Chest(Lock lock, Inventory inventory) {
    this.lock = Objects.requireNonNull(lock);
    this.inventory = Objects.requireNonNull(inventory);
    this.looted = false;
  }

  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitChest(this);
  }

  @Override
  public String toString() {
    return "chest";
  }
}
