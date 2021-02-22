package com.worldnavigator.game.exceptions;

public class NullLockException extends NullPointerException{

  public NullLockException() {
    super("This object doesn't have a lock!");
  }
}
