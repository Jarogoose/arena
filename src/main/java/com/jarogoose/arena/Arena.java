package com.jarogoose.arena;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Arena {

  public static final int SIZE = 8;
  private static int round = 0;
  private static List<Player> pool = new ArrayList<>();
  private Queue<Player> queue = new LinkedList<>();

  public static List<Player> pool() {
    return pool;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Arena arena = new Arena();
    boolean inGame = true;

    // setup
    Player spartacus = new Player("Spartacus", SIZE / 2, 1, 4, 5, 3);
    Player gannicus = new Player("Gannicus", SIZE / 2 - 1, SIZE - 1, 5, 4, 3);
    // Player agron = new Player("Agrom", SIZE/2+1, SIZE-1, 3, 3, 4);
    arena.pool.add(spartacus);
    arena.pool.add(gannicus);

    Display display = new Display(SIZE);
    display.addPlayers(arena.pool);
    display.render();

    while (inGame) {

      Player player = arena.next();
      System.out.println("Round -> " + round);
      System.out.println(player.info());

      String[] commands = scanner.nextLine().split(" ");

      for (String cmd : commands) {
        Combat.move(player, cmd);
      }

      display.render();

      if (commands[0].equals("q")) {
        inGame = false;
      }
    }
  }

  private Player next() {
    if (queue.isEmpty()) {
      queue.addAll(pool);
      round++;
    }
    return queue.poll();
  }
}
