package com.worldnavigator.webapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldnavigator.game.Game;
import com.worldnavigator.game.commands.CommandInvoker;
import com.worldnavigator.game.controls.Command;
import com.worldnavigator.game.controls.PlayerContext;
import com.worldnavigator.game.maze.Direction;
import com.worldnavigator.game.maze.Maze;
import com.worldnavigator.game.maze.Room;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.webapp.dto.ExecutionRequest;
import com.worldnavigator.webapp.dto.NewGameRequest;
import com.worldnavigator.webapp.entities.MazeTemplate;
import com.worldnavigator.webapp.entities.User;
import com.worldnavigator.webapp.repositories.MazeTemplateRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private final CommandInvoker commandInvoker;
  private final Map<UUID, Game> games;
  private final ObjectMapper mapper;
  private final MazeTemplateRepository mazeTemplateRepository;

  @Autowired
  public GameService(
      CommandInvoker commandInvoker,
      ObjectMapper mapper,
      MazeTemplateRepository mazeTemplateRepository) {
    this.mapper = mapper;
    this.commandInvoker = commandInvoker;
    this.games = new HashMap<>();
    this.mazeTemplateRepository = mazeTemplateRepository;
  }

  public Game create(User user, NewGameRequest request) throws IOException {

    MazeTemplate template =
        mazeTemplateRepository
            .findById(request.getMazeId())
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        String.format(
                            "There is no maze template with id (%d).", request.getMazeId())));

    UUID uuid = UUID.randomUUID();
    Maze maze = mapper.readValue(template.getTemplate(), Maze.class);
    LocalDateTime startedAt = LocalDateTime.now().plusMinutes(request.getStartsAfter());

    GameConfig = new GameConfig

    Game game =
        new Game(
            uuid,
            user.getUsername(),
            request.getName(),
            maze,
            request.getGold(),
            request.getTimeout(),
            startedAt);

    games.put(uuid, game);
    return game;
  }

  public Player createPlayer(String uuid, User user) {
    Game game = getGame(uuid);

    if (game.isStarted()) throw new IllegalStateException("The game has started you can't join.");
    else if (game.isFinished())
      throw new IllegalStateException("The game has finished you can't join.");

    Map<String, Player> players = game.getPlayers();

    if (players.containsKey(user.getUsername()))
      throw new IllegalArgumentException("You already join the game.");

    Player player = generatePlayer(game, user.getUsername());
    players.put(user.getUsername(), player);

    return player;
  }

  public Player generatePlayer(Game game, String username) {

    ThreadLocalRandom random = ThreadLocalRandom.current();

    // Pick a random direction for the player
    Direction[] directions = Direction.values();
    Direction direction = directions[random.nextInt(directions.length)];

    // Pick a random room to spawn the player in
    Maze maze = game.getMaze();

    List<Room> emptyRooms =
        maze.getRooms().stream().filter(Room::isEmpty).collect(Collectors.toList());

    if (emptyRooms.isEmpty())
      throw new NoSuchElementException("There is no empty rooms to spawn into.");

    Room randomRoom = emptyRooms.get(random.nextInt(emptyRooms.size()));

    Player player = new Player(username, game.getGold(), randomRoom.getIndex(), direction);

    randomRoom.addPlayer(player);
    return player;
  }

  public String execute(String uuid, User user, ExecutionRequest request) {

    PlayerContext context = getPlayerContext(uuid, user);

    Game game = context.getGame();
    Player player = context.getPlayer();

    if (!game.isStarted()) return "The game has not started yet.";
    else if (game.isFinished()) return "The game has finished.";
    else if (player.getMode() == PlayerMode.LOST) return "You lost good luck next time.";

    return invoker.execute(context, request.getLine());
  }

  public List<Command> getAvailableCommands(String uuid, User user) {
    return invoker.getAvailableCommands(getPlayerContext(uuid, user));
  }

  private PlayerContext getPlayerContext(String uuid, User user) {

    Game game = getGame(uuid);
    Player player = game.getPlayer(user.getUsername());

    return new PlayerContext(game, player);
  }

  public Player getPlayer(String uuid, User user) {
    Game game = getGame(uuid);
    return game.getPlayer(user.getUsername());
  }

  public Game getGame(String uuid) {
    Game game = games.get(UUID.fromString(uuid));

    if (game == null) throw new NoSuchElementException("There is no game with that uuid.");

    return game;
  }

  public Map<UUID, Game> getGames() {
    return games;
  }
}
