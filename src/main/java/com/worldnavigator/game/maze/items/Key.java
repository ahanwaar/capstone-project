package com.worldnavigator.game.maze.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Key implements Item {
    private final String NAME = getColor() +"Key";
    private final String color;
    private  int price;

    @JsonCreator
    public Key(
            @JsonProperty("color") String color
            ) {
        this.color = Objects.requireNonNull(color);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String accept(ItemVisitor itemVisitor) {
        return itemVisitor.visitKey(this);
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return Objects.equals(color, key.color);
    }

    @Override
    public String toString() {
        return this.color +"Key";
    }
}
