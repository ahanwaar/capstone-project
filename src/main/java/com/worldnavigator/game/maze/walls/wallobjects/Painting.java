package com.worldnavigator.game.maze.walls.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.walls.HiddenKey;
import com.worldnavigator.game.maze.walls.Wall;
import com.worldnavigator.game.maze.walls.WallVisitor;
import java.util.Objects;
import java.util.Optional;

public class Painting implements HiddenKey {

  private final Key hiddenKey;
  private boolean hasHiddenKey;

  @JsonCreator
  public Painting(@JsonProperty("key") Key key) {

    this.hiddenKey = Objects.requireNonNull(key);
    this.hasHiddenKey = true;
  }

  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitPainting(this);
  }

  @Override
  public boolean hasHiddenKey() {
    return this.hasHiddenKey;
  }

  @Override
  public void setCollected(boolean hasHiddenKey) {
    this.hasHiddenKey = hasHiddenKey;
  }

  @Override
  public Optional<Key> getKey() {
    return Optional.ofNullable(this.hiddenKey);
  }
}
