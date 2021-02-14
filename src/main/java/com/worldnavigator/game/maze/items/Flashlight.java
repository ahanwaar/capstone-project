package com.worldnavigator.game.maze.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldnavigator.game.Gold;
import com.worldnavigator.game.visitors.ItemVisitor;

public class Flashlight implements Item {

    private final Gold price;
    private boolean isOn;

    @JsonCreator
    public Flashlight(
            @JsonProperty("price") int price
    ){
        this.price = new Gold(price);
        this.isOn = false;
    }


    private void switchFlashlight(){
        this.isOn = !isOn;
    }

    @Override
    public Gold getPrice() {
        return price;
    }

    @Override
    public String accept(ItemVisitor itemVisitor) {
        return itemVisitor.visitFlashlight(this);
    }

    @Override
    public String toString() {
        return "Flashlight";
    }
}
