package com.worldnavigator.game;

import java.time.LocalTime;

public class Timer {
  private LocalTime initialTime;
  private int timerDuration;

  public Timer(int timerDuration) {

    this.timerDuration = timerDuration;
  }
}
