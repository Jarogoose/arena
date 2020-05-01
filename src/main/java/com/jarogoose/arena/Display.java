package com.jarogoose.arena;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Display {

  public static final String CLEAR_CONSOLE = "\033[H\033[2J";

  public static final String RESET = "\u001B[0m";
  public static final String BLACK = "\u001B[30m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";
  public static final String WHITE = "\u001B[37m";

  public static final String BG_BLACK = "\u001B[40m";
  public static final String BG_RED = "\u001B[41m";
  public static final String BG_GREEN = "\u001B[42m";
  public static final String BG_YELLOW = "\u001B[43m";
  public static final String BG_BLUE = "\u001B[44m";
  public static final String BG_PURPLE = "\u001B[45m";
  public static final String BG_CYAN = "\u001B[46m";
  public static final String BG_WHITE = "\u001B[47m";

  private static final String[][] grid = new String[Arena.SIZE][Arena.SIZE];
  private static final List<Player> PLAYERS = new ArrayList<>();

  public static void init() {
    grid[0][0] = "0";

    System.out.print(WHITE);
    for (int x = 1; x < Arena.SIZE; x++) {
      grid[0][x] = String.valueOf(x);
    }

    for (int y = 1; y < Arena.SIZE; y++) {
      grid[y][0] = String.valueOf(y);
    }
    System.out.print(RESET);

    drawField();
  }

  public static void clear() {
    System.out.print(CLEAR_CONSOLE);
  }

  public static void addPlayers(Collection<Player> players) {
    PLAYERS.addAll(players);
  }

  public static void render() {
//    clear();
    drawField();
    int size = Arena.SIZE;
    for (int y = size - 1; y >= 0; y--) {
      for (int x = 0; x < size; x++) {
        if (x == 0 || y == 0) {
          System.out.print(BG_BLACK + WHITE + grid[y][x] + " " + RESET); // draw coord
        } else {
          System.out.print(BG_YELLOW + BLACK + grid[y][x] + " " + RESET); // set a player
        }
      }
      System.out.println();
    }
    System.out.print(RESET);
  }

  public static String cell(final int x, final int y) {
    return grid[y][x];
  }

  private static void drawField() {
    for (int x = 1; x < Arena.SIZE; x++) {
      for (int y = 1; y < Arena.SIZE; y++) {
        grid[y][x] = " ";
      }
    }

    for (Player player : PLAYERS) {
      grid[player.y()][player.x()] = player.alias();
    }
  }

  public static void round() {
    System.out.println("Round -> " + Arena.round());
  }

  public static void playerInfo(Player player) {
    System.out.println(player.info());
  }

  public static void rolls(Player player, List<Integer> rolls) {
    StringBuilder sb = new StringBuilder();
    sb.append(player.alias()).append(" ");

    for (int i = rolls.size() - 1; i >= 0; i--) {
      sb.append(" |").append(rolls.get(i)).append("|");

    }

    System.out.println(sb.toString());
  }
}
