package com.worldnavigator.game.player;

import com.worldnavigator.game.maze.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PlayerLocation {

  private int roomIndex;
  private Direction direction;
}
