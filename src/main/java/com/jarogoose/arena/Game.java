package com.jarogoose.arena;

import static com.jarogoose.arena.Arena.SIZE;

import com.jarogoose.arena.Player.Status;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean inGame = true;

    // setup
    Arena arena = new Arena();
    Player spartacus = new Player("Spartacus", SIZE / 2, 1, 4, 5, 3);
    Player gannicus = new Player("Gannicus", SIZE / 2, SIZE - 1, 5, 4, 3);
     Player agron = new Player("Agrom", SIZE/2+1, SIZE-1, 3, 3, 4);
    // TODO validate if key already exist and use another alias
    arena.add(spartacus);
    arena.add(gannicus);
    arena.add(agron);

    Display.addPlayers(Arrays.asList(spartacus, gannicus, agron));
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

}
