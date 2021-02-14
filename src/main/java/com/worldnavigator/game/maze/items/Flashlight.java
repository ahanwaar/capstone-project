package com.worldnavigator.game.maze.items;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Flashlight implements Item {
    private int price;
    private boolean isOn;

    @JsonCreator
    public Flashlight(){

        this.isOn = false;
    }


    private void switchFlashlight(){
        this.isOn = !isOn;
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
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "flashlight";
    }
}
