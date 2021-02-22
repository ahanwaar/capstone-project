package com.worldnavigator;


import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.worldnavigator.game.*;
import com.worldnavigator.game.exceptions.NoSuchItemException;
import com.worldnavigator.game.maze.Direction;
import com.worldnavigator.game.maze.Inventory;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.maze.items.Key;
import com.worldnavigator.game.maze.wall.Lock;
import com.worldnavigator.game.maze.wall.Wall;
import com.worldnavigator.game.maze.wall.wallobjects.Chest;
import com.worldnavigator.game.maze.wall.wallobjects.Door;
import com.worldnavigator.game.maze.wall.wallobjects.Mirror;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class test {
  public static void main(String[] args) throws NoSuchItemException {
    Map<Direction, Wall> wallSides = new HashMap<>();
    wallSides.putIfAbsent(Direction.EAST, new Mirror(Key.GOLD));

    Room room = new Room(1, true, wallSides);
    Door door = new Door(new ArrayList<Room>(), new Lock(Key.NULL));

    door.getConnectedRooms().add(room);

    PriceList priceList = PriceList.getInstance();
    System.out.println(Key.COPPER == Key.SILVER);
  }
}
