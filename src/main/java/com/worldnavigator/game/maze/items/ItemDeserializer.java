package com.worldnavigator.game.maze.items;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.worldnavigator.game.exceptions.NoSuchItemException;
import java.io.IOException;

public class ItemDeserializer extends JsonDeserializer<Item> {

  @Override
  public Item deserialize(JsonParser p, DeserializationContext c) throws IOException {
    try {
      JsonNode node = p.getCodec().readTree(p);
      return ItemFactory.getItem(node.textValue());

    } catch (NoSuchItemException e) {
      throw new IOException(e);
    }
  }
}
