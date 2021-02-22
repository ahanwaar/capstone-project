package com.worldnavigator.game.conflict;

import com.worldnavigator.game.player.Player;
import com.worldnavigator.game.player.PlayerStatus;
import java.util.Deque;
import java.util.Objects;

public class ConflictHandler {
  private Deque<Player> opponents;
  private ConflictStatus conflictState;

  public ConflictHandler(Deque<Player> opponents){
    this.opponents = Objects.requireNonNull(opponents);
  }

  public ConflictStatus getPrimaryFightStatus(){
    Player p1 = opponents.getFirst();
    Player p2 = opponents.getLast();

    if(p1.getTotalGoldValue() > p2.getTotalGoldValue()){
      setResults(p1, p2);
      conflictState = ConflictStatus.FIRST_WON;
    }else if (p1.getTotalGoldValue() < p2.getTotalGoldValue()){
     setResults(p2,p1);
      conflictState = ConflictStatus.LAST_WON;
    }else
      p1.setPlayerStatus(PlayerStatus.FIGHTING);
      p2.setPlayerStatus(PlayerStatus.FIGHTING);
    conflictState = ConflictStatus.TIE;

    return conflictState;
  }

  public ConflictStatus getSecondaryFightStatus(Player player, Hand hand){
    RockPaperScissorsGame game = new RockPaperScissorsGame(opponents);
    if(conflictState != ConflictStatus.TIE){
      return conflictState;
    }
    if(game.play(player,hand)){
      return game.getStatus(player);
    }else game.reset();

    return getSecondaryFightStatus(player,hand);
  }

  public void setResults(Player winner, Player loser){
    winner.setPlayerStatus(PlayerStatus.WON);
    loser.setPlayerStatus(PlayerStatus.LOST);
    winner.getCurrentRoom().removePlayer(loser);
    winner.getGame().removePlayer(loser);
    winner.getGame().distributeGold(loser.getTotalGoldValue());
  }

  public void setLastPlayerWon(){
    opponents.removeFirst();
    opponents.getFirst().setPlayerStatus(PlayerStatus.LOST);
    opponents.getLast().setPlayerStatus(PlayerStatus.WON);
  }


}
