package com.jarogoose.arena;

import java.util.Arrays;

public class Player {

  private final String name;

  private int x;
  private int y;

  private int def;
  private int atk;
  private int spd;

  Player(String name, int x, int y, int atk, int def, int spd) {
    this.name = name;
    this.x = x;
    this.y = y;
    this.atk = atk;
    this.def = def;
    this.spd = spd;
  }


  public void up() {
    this.y++;
  }

  public void down() {
    this.y--;
  }

  public void left() {
    this.x--;
  }

  public void right() {
    this.x++;
  }

  public int y() {
    return this.y;
  }

  public int x() {
    return this.x;
  }

  public String name() {
    return this.name;
  }

  public int atk() {
    return atk;
  }

  public int def() {
    return def;
  }

  public int spd() {
    return spd;
  }

  public String alias() {
    // return first letter
    return this.name.substring(0, 1);
  }

  public int[] hit() {
    int[] rolls = new int[atk()];
    for (int i = 0; i < atk(); i++) {
      rolls[i] = Dice.roll();
    }
    return rolls;
  }

  public int[] block() {
    int[] rolls = new int[def()];
    for (int i = 0; i < def(); i++) {
      rolls[i] = Dice.roll();
    }
    return rolls;
  }

  public int[] initiative() {
    int[] rolls = new int[spd()];
    for (int i = 0; i < spd(); i++) {
      rolls[i] = Dice.roll();
    }
    return rolls;
  }

  public String info() {
    final String space = " ";
    return Display.GREEN + name() + space + Display.RESET
        + Display.RED + atk() + space + Display.RESET
        + Display.PURPLE + def() + space + Display.RESET
        + Display.BLUE + spd() + space + Display.RESET;
  }
}
