package com.jarogoose.arena;

import java.util.Random;

public class Dice {

  private static Random random = new Random();

  public static int roll() {
    return random.nextInt(6) + 1; // 6-edge dice
  }
}
