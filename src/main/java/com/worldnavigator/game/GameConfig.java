package com.worldnavigator.game;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GameConfig {

  private int initialGoldAmount;
  private int timeOut;
  private PriceList priceList;

}
