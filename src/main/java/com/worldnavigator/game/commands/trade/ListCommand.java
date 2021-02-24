package com.worldnavigator.game.commands.trade;

import com.worldnavigator.game.commands.Command;
import com.worldnavigator.game.maze.wall.wallobjects.Seller;
import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerStatus;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ListCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    player.setPlayerStatus(PlayerStatus.TRADE_MODE);
    Seller seller = (Seller) player.getCurrentWall();
    Map<String, Integer> priceList = seller.getPricesList().getPrices();
    StringBuilder sb = new StringBuilder();

    sb.append("Available Seller items:\n");
    priceList.forEach(
        (item, price) -> sb.append("\t").append(item).append(" : ").append(price).append("\n"));

    return sb.toString();
  }

  @Override
  public String getDescription() {
    return "use to list seller item";
  }

  @Override
  public String name() {
    return "trade:list";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getCurrentWall() instanceof Seller;
  }
}
