package com.worldnavigator.game.maze.items;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.worldnavigator.game.deseializers.ItemDeserializer;
import com.worldnavigator.game.visitors.ItemVisitor;

@JsonDeserialize(using = ItemDeserializer.class)
public interface Item {

    public String accept(ItemVisitor itemVisitor);

    @Override
    public String toString();
}
