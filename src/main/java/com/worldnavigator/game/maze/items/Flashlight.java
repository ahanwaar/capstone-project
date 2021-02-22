package com.worldnavigator.game.maze.items;

import com.worldnavigator.game.PriceList;
import com.worldnavigator.game.exceptions.NoSuchItemException;
import java.util.Objects;


public class Flashlight implements Item {

  private boolean on;

  public Flashlight() {
    this.on = false;
  }

  public boolean isOn() {
    return on;
  }

  private void switchFlashlight() {
    on = !on;
  }

  @Override
  public String getName() {
    return "flashlight";
  }

  @Override
  public String accept(ItemVisitor itemVisitor) {
    return itemVisitor.visitFlashlight(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Flashlight that = (Flashlight) o;
    return getName().equals(((Flashlight) o).getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  @Override
  public String toString() {
    return "flashlight";
  }
}
