
package test;
import static api.Mode.CHASE;

import api.Direction;
import api.PacmanGame;
import hw4.Clyde;
import hw4.Inky;
import hw4.Pinky;

/**
 * Some ideas for testing the basic target calculation for Pinky, Inky, Clyde
 */
public class SimpleTestTargets
{

  public static final String[] PINKY1 = {
    "#######",
    "#B....#",
    "##....#",
    "#S.P..#",
    "#.....#",
    "#.....#",
    "#######",     
  };
  
  public static final String[] INKY1 = {
    "#######",
    "###I..#",
    "#.....#",
    "#..S..#",
    "#.....#",
    "#B....#",
    "#######",     
  };
  
  public static final String[] CLYDE1 = {
    "##########",
    "#B......S#",
    "#........#",
    "#........#",
    "#........#",
    "#........#",
    "#........#",
    "#C.......#",
    "##########",     
  };
  
  public static void main(String[] args)
  {

  System.out.println("Test targeting for Pinky");
  
  // using a frame rate of 10, the speed increment will be 0.4
  PacmanGame game = new PacmanGame(PINKY1, 10);
  
  // set pacman's direction to RIGHT
  game.getPlayer().setDirection(Direction.RIGHT);
  
  // Pinky should be at index 1, default initial direction is UP
  Pinky pinky = (Pinky) game.getEnemies()[1];

  // Set the mode to CHASE. Target is four cells in front of Pacman
  // which is (3, 5), so closest cell Pinky can go to is (3, 4)
  pinky.setMode(CHASE, SimpleTest.makeDescriptor(game));  
  System.out.println(pinky.getNextCell()); // expected (3, 4)
  System.out.println(pinky.getCurrentDirection()); // still UP
  System.out.println(pinky.getRowExact() + ", " + pinky.getColExact()); // 3.5, 3.5
  System.out.println();

  // Update moves Pinky zero units up to the center,
  // then changes direction to RIGHT
  pinky.update(SimpleTest.makeDescriptor(game));
  System.out.println(pinky.getNextCell()); // expected (3, 4)
  System.out.println(pinky.getCurrentDirection());  // RIGHT
  System.out.println(pinky.getRowExact() + ", " + pinky.getColExact()); // still 3.5, 3.5
  System.out.println();

  // next update should continue to right
  pinky.update(SimpleTest.makeDescriptor(game));
  System.out.println(pinky.getNextCell()); // expected (3, 4)
  System.out.println(pinky.getCurrentDirection()); // expected RIGHT
  System.out.println(pinky.getRowExact() + ", " + pinky.getColExact()); // 3.5, 3.9
  System.out.println();
  
  // now, turn Pacman to face down
  game.getPlayer().setDirection(Direction.DOWN);

  // next update should continue to right into (3, 4),
  // triggering a call to calculateNextCell.  But now
  // next cell should be the one below at (4, 4), since the target 
  // is now at (7, 1)
  pinky.update(SimpleTest.makeDescriptor(game));
  System.out.println(pinky.getNextCell()); // expected (4, 4)
  System.out.println(pinky.getCurrentDirection()); // expected RIGHT
  System.out.println(pinky.getRowExact() + ", " + pinky.getColExact()); // 3.5, 4.3
  System.out.println();

  // next update should continue right to center of (3, 4),
  // then change direction to DOWN
  pinky.update(SimpleTest.makeDescriptor(game));
  System.out.println(pinky.getNextCell()); // expected (4, 4)
  System.out.println(pinky.getCurrentDirection()); // expected DOWN
  System.out.println(pinky.getRowExact() + ", " + pinky.getColExact()); // 3.5, 4.5
  System.out.println();

  
  System.out.println("--------");
  System.out.println();
  
  
  
  
  System.out.println("Test targeting for Inky");
  
  // using a frame rate of 10, the speed increment will be 0.4
  game = new PacmanGame(INKY1, 10);
  
  // set pacman's direction to RIGHT
  game.getPlayer().setDirection(Direction.RIGHT);
  
  // Inky should be at index 1, since there is no Pinky. 
  // default initial direction is DOWN
  Inky inky = (Inky) game.getEnemies()[1];

  // Set the mode to CHASE. Target should be (1, 9)
  // which is (3, 5), so closest cell Inky can go to is (1, 4)
  inky.setMode(CHASE, SimpleTest.makeDescriptor(game));  
  System.out.println(inky.getNextCell()); // expected (1, 4)
  System.out.println(inky.getCurrentDirection()); // still DOWN
  System.out.println(inky.getRowExact() + ", " + inky.getColExact()); // 1.5, 3.5
  System.out.println();

  // Update moves Inky zero units down to the center,
  // then changes direction to RIGHT
  inky.update(SimpleTest.makeDescriptor(game));
  System.out.println(inky.getNextCell()); // expected (1, 4)
  System.out.println(inky.getCurrentDirection());  // RIGHT
  System.out.println(inky.getRowExact() + ", " + inky.getColExact()); // still 1.5, 3.5
  System.out.println();

  // next update should continue to right
  inky.update(SimpleTest.makeDescriptor(game));
  System.out.println(inky.getNextCell()); // expected (1, 4)
  System.out.println(inky.getCurrentDirection()); // expected RIGHT
  System.out.println(inky.getRowExact() + ", " + inky.getColExact()); // 1.5, 3.9
  System.out.println();
  
  // now, turn Pacman to face down
  game.getPlayer().setDirection(Direction.DOWN);

  // next update should continue to right into (1, 4),
  // triggering a call to calculateNextCell.  But now
  // next cell should be the one below at (2, 4), since the target 
  // is now at (5, 5)
  inky.update(SimpleTest.makeDescriptor(game));
  System.out.println(inky.getNextCell()); // expected (2, 4)
  System.out.println(inky.getCurrentDirection()); // expected RIGHT
  System.out.println(inky.getRowExact() + ", " + inky.getColExact()); // 1.5, 4.3
  System.out.println();

  // next update should continue right to center of (1, 4),
  // then change direction to DOWN
  inky.update(SimpleTest.makeDescriptor(game));
  System.out.println(inky.getNextCell()); // expected (2, 4)
  System.out.println(inky.getCurrentDirection()); // expected DOWN
  System.out.println(inky.getRowExact() + ", " + inky.getColExact()); // 1.5, 4.5
  System.out.println();

  
  System.out.println("--------");
  System.out.println();
  
  
  
  
  System.out.println("Test targeting for Clyde");
  
  // using a frame rate of 10, the speed increment will be 0.4
  game = new PacmanGame(CLYDE1, 10);
  
  // Clyde should be at index 1, since there is no Pinky or Inky
  // default initial direction is UP
  Clyde clyde = (Clyde) game.getEnemies()[1];

  // Ok. Pacman is at (1, 8) and Clyde is at (7, 1)
  // Distances squared from Pacman to some neighboring cells are:
  //   Pacman to (7, 1): 85, so distance greater than 8 
  //   Pacman to (6, 1): 74, so distance greater than 8
  //   Pacman to (6, 2): 61, so distance less than 8
  //   Pacman to (7, 2): 72, so distance greater than 8
  
  // Set the mode to CHASE. We are more than 8 units away, so target is Pacman.
  // Clyde can go to (6, 1) or (7, 2), and (7, 2) is closer, which is to the right.
  clyde.setMode(CHASE, SimpleTest.makeDescriptor(game));  
  System.out.println(clyde.getNextCell()); // expected (7, 2)
  System.out.println(clyde.getCurrentDirection()); // still UP
  System.out.println(clyde.getRowExact() + ", " + clyde.getColExact()); // 7.5, 1.5
  System.out.println();

  // Update moves Clyde zero units up to the center,
  // then changes direction to RIGHT
  clyde.update(SimpleTest.makeDescriptor(game));
  System.out.println(clyde.getNextCell()); // expected (7, 2)
  System.out.println(clyde.getCurrentDirection());  // RIGHT
  System.out.println(clyde.getRowExact() + ", " + clyde.getColExact()); // still 7.5, 1.5
  System.out.println();

  // next update should continue to right
  clyde.update(SimpleTest.makeDescriptor(game));
  System.out.println(clyde.getNextCell()); // expected (7, 2)
  System.out.println(clyde.getCurrentDirection()); // expected RIGHT
  System.out.println(clyde.getRowExact() + ", " + clyde.getColExact()); // 7.5, 1.9
  System.out.println();

  // next update should continue to right into (7, 2),
  // triggering a call to calculateNextCell.  We are still more than 8 units from
  // Pacman so target is still Pacman. Closest next cell is above at (6, 2)
  clyde.update(SimpleTest.makeDescriptor(game));
  System.out.println(clyde.getNextCell()); // expected (6, 2)
  System.out.println(clyde.getCurrentDirection()); // expected RIGHT
  System.out.println(clyde.getRowExact() + ", " + clyde.getColExact()); // 7.5, 2.3
  System.out.println();

  // next update should continue right to center of (7, 2),
  // then change direction to UP
  clyde.update(SimpleTest.makeDescriptor(game));
  System.out.println(clyde.getNextCell()); // expected (6, 2)
  System.out.println(clyde.getCurrentDirection()); // expected UP
  System.out.println(clyde.getRowExact() + ", " + clyde.getColExact()); // 7.5, 2.5
  System.out.println();

  // next update should go up 
  clyde.update(SimpleTest.makeDescriptor(game));
  System.out.println(clyde.getNextCell()); // expected (6, 2)
  System.out.println(clyde.getCurrentDirection()); // expected UP
  System.out.println(clyde.getRowExact() + ", " + clyde.getColExact()); // 7.1, 2.5
  System.out.println();

  // next update should go up. We crossed into next cell (6, 2) which
  // is less than distance 8 from Pacman, so now the target is Clyde's
  // scatter target which is in the lower left corner at (9, 1)
  // Clyde can't go down, so cell (6, 1) at left is closest to target
  clyde.update(SimpleTest.makeDescriptor(game));
  System.out.println(clyde.getNextCell()); // expected (6, 1)
  System.out.println(clyde.getCurrentDirection()); // expected UP
  System.out.println(clyde.getRowExact() + ", " + clyde.getColExact()); // 6.7, 2.5
  System.out.println();

  // next update should go up to center of row 6 and then change direction to left
  clyde.update(SimpleTest.makeDescriptor(game));
  System.out.println(clyde.getNextCell()); // expected (6, 1)
  System.out.println(clyde.getCurrentDirection()); // expected LEFT
  System.out.println(clyde.getRowExact() + ", " + clyde.getColExact()); // 6.5, 2.5
  System.out.println();
  
  // next update should go left
  clyde.update(SimpleTest.makeDescriptor(game));
  System.out.println(clyde.getNextCell()); // expected (6, 1)
  System.out.println(clyde.getCurrentDirection()); // expected LEFT
  System.out.println(clyde.getRowExact() + ", " + clyde.getColExact()); // 6.5, 2.1
  System.out.println();
  
  // next update should go left.  Crossing into next cell triggers call to
  // calculateNextCell.  Since (6, 1) is more than 8 units from Pacman, 
  // the target is Pacman again, so closest possible cell is ablve at (5, 1)
  clyde.update(SimpleTest.makeDescriptor(game));
  System.out.println(clyde.getNextCell()); // expected (5, 1)
  System.out.println(clyde.getCurrentDirection()); // expected LEFT
  System.out.println(clyde.getRowExact() + ", " + clyde.getColExact()); // 6.5, 1.7
  System.out.println();
  
  
  System.out.println("--------");
  System.out.println();

  
  
  }
  
  
}
