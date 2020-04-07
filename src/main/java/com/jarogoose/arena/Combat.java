package com.jarogoose.arena;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Defines the combat flow
 */
public class Combat {

  public static void move(Player player, String direction) {
    if (Keyword.UP.contains(direction)) {
//      if () {
//
//      }
      player.up();
    }

    if (Keyword.DOWN.contains(direction)) {
      player.down();
    }

    if (Keyword.LEFT.contains(direction)) {
      player.left();
    }

    if (Keyword.RIGHT.contains(direction)) {
      player.right();
    }
  }

  /**
   * returns number of wounds.
   */
  public static int attack(Player attacker, Player defender) {
    int wounds = 0;
    int[] atkPool = attacker.hit();
    int[] defPool = defender.block();

    Queue<Integer> hits = Arrays.stream(atkPool)
        .boxed()
        .sorted()
        .collect(Collectors.toCollection(LinkedList::new));

    Queue<Integer> blocks = Arrays.stream(defPool)
        .boxed()
        .sorted()
        .collect(Collectors.toCollection(LinkedList::new));

    while (!hits.isEmpty()) {
      final int hit = hits.poll();
      final int block = blocks.poll();
      boolean wounded = hit > 2 && hit > block;
      if (wounded) {
        wounds++;
      }
    }

    return wounds;
  }

  private static boolean isOffenciveMove(Player player, String cmd) {
    for (Player enemy: Arena.pool()) {
      player.x() ;
      player.y();


    }
    return false;
  }
}
