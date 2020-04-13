package com.jarogoose.arena;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Defines the combat flow
 */
public class Combat {

  public static void move(Player player, String direction) {
    if (Keyword.UP.contains(direction)) {
      final String alias = Display.cell(player.x(), player.y() + 1);
      if (!alias.trim().isEmpty()) {
        Player defender = Arena.fromPool(alias);
        final int wounds = attack(player, defender);
        defender.take(wounds);
      } else {
        player.up();
      }
    }

    if (Keyword.DOWN.contains(direction)) {
      final String alias = Display.cell(player.x(), player.y() - 1);
      if (!alias.trim().isEmpty()) {
        Player defender = Arena.fromPool(alias);
        final int wounds = attack(player, defender);
        defender.take(wounds);
      } else {
        player.down();
      }
    }

    if (Keyword.LEFT.contains(direction)) {
      final String alias = Display.cell(player.x() - 1, player.y());
      if (!alias.trim().isEmpty()) {
        Player defender = Arena.fromPool(alias);
        final int wounds = attack(player, defender);
        defender.take(wounds);
      } else {
        player.left();
      }
    }

    if (Keyword.RIGHT.contains(direction)) {
      final String alias = Display.cell(player.x() + 1, player.y());
      if (!alias.trim().isEmpty()) {
        Player defender = Arena.fromPool(alias);
        final int wounds = attack(player, defender);
        defender.take(wounds);
      } else {
        player.right();
      }
    }
  }

  /**
   * returns number of wounds.
   */
  public static int attack(Player attacker, Player defender) {
    int wounds = 0;
    int[] atkPool = attacker.hit();
    int[] defPool = defender.block();

    List<Integer> hits = Arrays.stream(atkPool)
        .boxed()
        .sorted()
        .collect(Collectors.toCollection(LinkedList::new));

    List<Integer> blocks = Arrays.stream(defPool)
        .boxed()
        .sorted()
        .collect(Collectors.toCollection(LinkedList::new));

    Display.rolls(attacker, hits);
    Display.rolls(defender, blocks);

    while (!hits.isEmpty()) {
      final int hit = hits.remove(hits.size() - 1);
      int block = 1; // default value in case no more def dice
      if (!blocks.isEmpty()) {
        block = blocks.remove(blocks.size() - 1);
      }

      boolean wounded = hit > 2 && hit > block;
      if (wounded) {
        wounds++;
      }
    }

    System.out.println(attacker.name() + " inflicts " + wounds);
    return wounds;
  }
}
