package com.worldnavigator.game.maze.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.worldnavigator.game.visitors.ItemVisitor;

import java.util.Objects;

public class Key implements Item{

    private final String color;

    @JsonCreator
    public Key(String color) {
        this.color = Objects.requireNonNull(color);
    }


    @Override
    public String accept(ItemVisitor itemVisitor) {
        return itemVisitor.visitKey(this);
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
        return "Key{" +
                "color='" + color + '\'' +
                '}';
    }
}
