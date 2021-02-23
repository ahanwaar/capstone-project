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
    opponents.getFirst().setPlayerStatus(PlayerStatus.FIGHTING);
    opponents.getLast().setPlayerStatus(PlayerStatus.FIGHTING);
  }

  public ConflictStatus getPrimaryFightStatus(){
    Player p1 = opponents.getFirst();
    Player p2 = opponents.getLast();

    if(p1.getTotalGoldValue() > p2.getTotalGoldValue()){
      setResults(p1, p2);
      conflictState = ConflictStatus.LOST;
    }else if (p1.getTotalGoldValue() < p2.getTotalGoldValue()){
     setResults(p2,p1);
      conflictState = ConflictStatus.WON;
    }else
    conflictState = ConflictStatus.TIE;

    return conflictState;
  }

  public ConflictStatus getSecondaryFightStatus(Player player, Hand hand){
    RockPaperScissorsFight game = new RockPaperScissorsFight(opponents);
    if(conflictState != ConflictStatus.TIE){
      return conflictState;
    }
    if(game.start(player,hand)){
      return game.getStatus(player);
    }else game.reset();

    return getSecondaryFightStatus(player,hand);
  }

  public void setResults(Player winner, Player loser){
    winner.setPlayerStatus(PlayerStatus.WALKING);
    loser.setPlayerStatus(PlayerStatus.LOST);
    winner.getCurrentRoom().removePlayer(loser);
    winner.getGame().removePlayer(loser);
    winner.getGame().distributeGold(loser.getTotalGoldValue());
  }

}
