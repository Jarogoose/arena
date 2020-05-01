package com.jarogoose.arena;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Arena {

  public static final int SIZE = 9;
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

  public void add(Player player) {
    pool.put(player.alias(), player);
  }

  public Player next() {
    if (queue.isEmpty()) {
      queue.addAll(pool.values());
      round++;
    }
    return queue.poll();
  }
}
