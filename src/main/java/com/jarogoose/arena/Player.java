package com.jarogoose.arena;

import java.util.Random;

public class Player {

  public enum Status {
    ACTIVE,
    YIELD,
    INJURED,
    DECAPITATED;
  }

  private Status status = Status.ACTIVE;

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

  public Status status() {
    return this.status;
  }

  public int[] hit() {
    return Dice.roll(atk());
  }

  public int[] block() {
    return Dice.roll(def());
  }

  public int[] initiative() {
    return Dice.roll(spd());
  }

  public int hp() {
    return atk() + def() + spd();
  }

  public String info() {
    final String space = " ";
    return Display.GREEN + name() + space + Display.RESET
        + Display.RED + atk() + space + Display.RESET
        + Display.PURPLE + def() + space + Display.RESET
        + Display.BLUE + spd() + space + Display.RESET;
  }

  public void take(int wounds) {
    final int expectedHp = hp() - wounds;

    final int atkIndex = 0;
    final int defIndex = 1;
    final int spdIndex = 2;

    if (hp() - wounds == 2) {
      status = Status.YIELD;
      return;
    }
    if (hp() - wounds == 1) {
      status = Status.INJURED;
      return;
    }
    if (hp() - wounds <= 0) {
      status = Status.DECAPITATED;
      return;
    }

    Random random = new Random();
    while (hp() > 3 && hp() > expectedHp) {
      final int index = random.nextInt(3); // randomly determine which parameter to decrease.
      if (index == atkIndex && atk() > 1) {
        atk--;
      }
      if (index == defIndex && def() > 1) {
        def--;
      }
      if (index == spdIndex && spd() > 1) {
        spd--;
      }
    }
  }
}
