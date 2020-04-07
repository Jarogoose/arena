package com.jarogoose.arena;

import java.util.ArrayList;
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

  private final String[][] grid;
  private final List<Player> players = new ArrayList<>();

  public Display(final int size) {
    grid = new String[size][size];

    grid[0][0] = "0";

    System.out.print(WHITE);
    for (int x = 1; x < size; x++) {
      grid[0][x] = String.valueOf(x);
    }

    for (int y = 1; y < size; y++) {
      grid[y][0] = String.valueOf(y);
    }
    System.out.print(RESET);

    drawField();
  }

  public void clear() {
    System.out.print(CLEAR_CONSOLE);
  }

  public void addPlayers(List<Player> players) {
    this.players.addAll(players);
  }

  public void render() {
    clear();
    drawField();
    int size = grid.length;
    for (int y = size - 1; y >= 0; y--) {
      for (int x = 0; x < size; x++) {
        if (x == 0 || y == 0) {
          System.out.print(BG_BLACK + WHITE + grid[y][x] + " ");
        } else {
          System.out.print(BG_YELLOW + BLACK + grid[y][x] + " ");
        }
      }
      System.out.println();
    }
    System.out.print(RESET);
  }

  public String cell(final int x, final int y) {
    return grid[y][x];
  }

  private void drawField() {
    for (int x = 1; x < grid.length; x++) {
      for (int y = 1; y < grid.length; y++) {
        grid[y][x] = " ";
      }
    }

    for (Player player : players) {
      grid[player.y()][player.x()] = player.alias();
    }
  }
}
