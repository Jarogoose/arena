package com.jarogoose.arena;

import java.util.Random;

public class Dice {

  private static Random random = new Random();

  public static int roll() {
    return random.nextInt(6) + 1; // 6-edge dice
  }

  public static int[] roll(int quantity) {
    int[] dices = new int[quantity];
    for (int i = 0; i < quantity; i++) {
      dices[i] = roll();
    }
    return dices;
  }
}
