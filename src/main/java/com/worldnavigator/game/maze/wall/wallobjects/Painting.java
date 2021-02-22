package com.worldnavigator.game.maze.wall.wallobjects;

import com.worldnavigator.game.exceptions.NoSuchItemException;
import com.worldnavigator.game.exceptions.NullItemException;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.wall.HiddenKey;

import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.maze.wall.WallVisitor;
import java.util.Objects;

public class Painting extends Wall implements HiddenKey {

  private Key hiddenKey;

  public Painting(Key key) {
    this.hiddenKey = Objects.requireNonNull(key);
  }

  @Override
  public String accept(WallVisitor visitor){
    return visitor.visitPainting(this);
  }


  @Override
  public boolean hasHiddenKey() {
    return !hiddenKey.equals(Key.NULL);
  }

  @Override
  public Key grabKey(){
    Key key= Key.NULL;
    try{
      if(hasHiddenKey()){
        key = hiddenKey;
        this.hiddenKey = Key.NULL;
      }else throw new NullItemException("There is no hidden key");
    } catch (NullItemException e) {
      e.printStackTrace();
    }
    return key;
  }
}
