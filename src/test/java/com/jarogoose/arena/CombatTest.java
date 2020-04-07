package com.jarogoose.arena;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

class CombatTest {

  @Spy Player attacker = new Player("Spartacus", 0, 1, 4, 5, 3);
  @Spy Player defender = new Player("Gannicus", 0, 2, 5, 4, 3);

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void oneWound_whenAtkDice_isGreaterThanDefDice() {
    int[] atkPool = {6};
    Mockito.doReturn(atkPool).when(attacker).hit();
    int[] defPool = {3};
    Mockito.doReturn(defPool).when(defender).block();

    final int wounds = Combat.attack(attacker, defender);

    assertEquals(1, wounds);
  }

  @Test
  void twoWounds_whenTwoDice_isGreaterThanDefDice() {
    int[] atkPool = {6, 5};
    Mockito.doReturn(atkPool).when(attacker).hit();
    int[] defPool = {3, 4};
    Mockito.doReturn(defPool).when(defender).block();

    final int wounds = Combat.attack(attacker, defender);

    assertEquals(2, wounds);
  }

  @Test
  void noWounds_whenAtkDice_isLesserThanDefDice() {
    int[] atkPool = {5};
    Mockito.doReturn(atkPool).when(attacker).hit();
    int[] defPool = {6};
    Mockito.doReturn(defPool).when(defender).block();

    final int wounds = Combat.attack(attacker, defender);

    assertEquals(0, wounds);
  }

  @Test
  void noWounds_whenAtkDice_isEqualToDefDice() {
    int[] atkPool = {3};
    Mockito.doReturn(atkPool).when(attacker).hit();
    int[] defPool = {3};
    Mockito.doReturn(defPool).when(defender).block();

    final int wounds = Combat.attack(attacker, defender);

    assertEquals(0, wounds);
  }

  @Test
  void noWounds_whenAtkDiceValue_isLesserThanThree() {
    int[] atkPool = {2};
    Mockito.doReturn(atkPool).when(attacker).hit();
    int[] defPool = {1};
    Mockito.doReturn(defPool).when(defender).block();

    final int wounds = Combat.attack(attacker, defender);

    assertEquals(0, wounds);
  }
}
