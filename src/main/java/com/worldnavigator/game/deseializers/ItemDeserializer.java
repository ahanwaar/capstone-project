package com.worldnavigator.game.deseializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.worldnavigator.game.Exceptions.NoSuchItemException;
import com.worldnavigator.game.factories.ItemFactory;
import com.worldnavigator.game.maze.items.Item;
import java.io.IOException;

//TODO DELETE IT
public class ItemDeserializer extends JsonDeserializer<Item> {

  @Override
  public Item deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    try {
      JsonNode node = jsonParser.getCodec().readTree(jsonParser);
      return ItemFactory.getFactory().getItem(node.textValue());

    } catch (NoSuchItemException e) {
      throw new IOException(e);
    }
  }
}
