package com.jarogoose.arena;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class PlayerTest {

  @Test
  @Timeout(value = 50, unit = MILLISECONDS)
  void hitPointsEqualFive_whenTakeFourWounds() {
    Player defender = new Player("Meat", 0, 1, 3, 3, 3); // 9 hp

    defender.take(4);

    assertEquals(5, defender.hp());
  }

  @Test
  @Timeout(value = 50, unit = MILLISECONDS)
  void attackStaysOne_ifAttackWasOneBeforeTakingWound() {
    Player defender = new Player("Meat", 0, 1, 1, 2, 2); // 5 hp

    defender.take(4);

    assertEquals(1, defender.atk());
  }

  @Test
  @Timeout(value = 50, unit = MILLISECONDS)
  void defenceStaysOne_ifDefenceWasOneBeforeTakingWound() {
    Player defender = new Player("Meat", 0, 1, 2, 1, 2); // 5 hp

    defender.take(2);

    assertEquals(1, defender.def());
  }

  @Test
  @Timeout(value = 50, unit = MILLISECONDS)
  void speedStaysOne_ifspeedWasOneBeforeTakingWound() {
    Player defender = new Player("Meat", 0, 1, 2, 2, 1); // 5 hp

    defender.take(2);

    assertEquals(1, defender.spd());
  }
}