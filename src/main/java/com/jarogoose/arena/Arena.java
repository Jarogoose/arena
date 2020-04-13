package com.jarogoose.arena;

import com.jarogoose.arena.Player.Status;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Arena {

  public static final int SIZE = 8;
  private static int round = 0;
  private static Map<String, Player> pool = new HashMap<>();
  private Queue<Player> queue = new LinkedList<>();

  /**
   * return the player from the pool by it's unique alias
   */
  public static Player fromPool(String key) {
    return pool.get(key);
  }

  public static int round() {
    return round;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Arena arena = new Arena();
    boolean inGame = true;

    // setup
    Player spartacus = new Player("Spartacus", SIZE / 2, 1, 4, 5, 3);
    Player gannicus = new Player("Gannicus", SIZE / 2, SIZE - 1, 5, 4, 3);
    // Player agron = new Player("Agrom", SIZE/2+1, SIZE-1, 3, 3, 4);
    // TODO validate if key already exist and use another alias
    pool.put(spartacus.alias(), spartacus);
    pool.put(gannicus.alias(), gannicus);

    Display.addPlayers(pool.values());
    Display.init();
    Display.render();

    while (inGame) {
      Player player = arena.next();
      Display.round();
      Display.playerInfo(player);

      String[] commands = scanner.nextLine().split("");
      if (player.status() == Status.ACTIVE) {
        for (String cmd : commands) {
          Combat.move(player, cmd);
        }
      } else {
        System.out.println(
            Display.RED + player.name() + " was " + player.status().toString().toLowerCase());
        inGame = false;
      }

      Display.render();

      if (commands[0].equals("q")) {
        inGame = false;
      }
    }
  }

  private Player next() {
    if (queue.isEmpty()) {
      queue.addAll(pool.values());
      round++;
    }
    return queue.poll();
  }
}
