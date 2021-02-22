package com.worldnavigator.game.maze.wall.wallobjects;

import com.worldnavigator.game.exceptions.NullItemException;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.wall.HiddenKey;
import com.worldnavigator.game.maze.wall.Wall;

import com.worldnavigator.game.maze.wall.WallVisitor;
import java.util.Objects;

public class Mirror extends Wall implements HiddenKey {

    private Key hiddenKey;

    public Mirror(Key key) {
        this.hiddenKey = Objects.requireNonNull(key);
    }

    public void acquireKey(Key hiddenKey) {
        this.hiddenKey = hiddenKey;
    }

    @Override
    public String accept(WallVisitor visitor) {
        return visitor.visitMirror(this);
    }

    @Override
    public boolean hasHiddenKey() {
        return !hiddenKey.equals(Key.NULL);
    }

    @Override
    public Key grabKey(){
        Key key = Key.NULL;
        try{
            if(hasHiddenKey()){
                this.hiddenKey = Key.NULL;
                return hiddenKey;
            }else throw new NullItemException("there is no hidden key");
        } catch (NullItemException e) {
            e.printStackTrace();
        }
        return key;
    }
}
