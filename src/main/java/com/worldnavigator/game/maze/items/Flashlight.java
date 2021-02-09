package com.worldnavigator.game.maze.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.worldnavigator.game.visitors.ItemVisitor;

public class Flashlight implements Item {

    private boolean isOn;

    @JsonCreator
    public Flashlight(){
        this.isOn = false;
    }


    private void switchFlashlight(){
        this.isOn = !isOn;
    }

    @Override
    public String accept(ItemVisitor itemVisitor) {
        return itemVisitor.visitFlashlight(this);
    }

    @Override
    public String toString() {
        return "Flashlight{" +
                "isOn=" + isOn +
                '}';
    }
}
