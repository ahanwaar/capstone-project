package com.worldnavigator.game;

import com.worldnavigator.game.maze.Maze;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GameConfig {

  private int initialGoldAmount;
  private int timeOut;
  private final Maze maze;


}
