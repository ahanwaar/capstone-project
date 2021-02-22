package com.worldnavigator.game.conflict;

import com.worldnavigator.game.player.Player;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RockPaperScissorsGame {

  private final Map<Player, Hand> hands;

  public RockPaperScissorsGame(
      Deque<Player> players) {
    hands = new HashMap<>();
    this.hands.put(players.getFirst(),Hand.NULL);
    this.hands.put(players.getLast(),Hand.NULL);
  }

  public boolean play(Player player, Hand hand){
    if(hands.get(player) == Hand.NULL) {
      hands.put(player, hand);
      return true;
    }
    return false;
  }

  public ConflictStatus getStatus(Player player) {
    Player opponent = player.getCurrentRoom().getPlayers().getFirst();

    Hand playerHand = hands.get(player);
    Hand opponentHand = hands.get(opponent);
    if(playerHand == opponentHand)
      return ConflictStatus.TIE;
    if(playerHand.beats(opponentHand))
      return ConflictStatus.LAST_WON;
    else return ConflictStatus.FIRST_WON;
  }

  public void reset() {
    hands.replaceAll((player, hand) -> Hand.NULL);
  }



  public Set<Player> getPlayers() {
    return hands.keySet();
  }
}

