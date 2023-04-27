package hw4;
import java.util.Random;

import api.Descriptor;
import api.Direction;
import api.Location;
import api.MazeMap;
import api.Mode;

/**
 * Ghost for Clyde
 * @author Chen Sang 
 */
public class Clyde extends Ghost{

	public Clyde (MazeMap maze, Location home, double baseSpeed, Direction homeDirection, Location scatterTarget, Random rand)
	{
		super(maze, home, baseSpeed, homeDirection, scatterTarget, rand);
	}
	
	
	/**
	 * Clyde target cell in chase mode
	 * find target cell in the case mode 
	 * if distance > 8 tails, target cell would be pacman
	 *  otherwise, return trarget as scatter target
	 * @param pacmanLocation
	 * @param clydeLoc
	 * @return
	 *    targetCell
	 */
	 protected Location chaseTarget(Descriptor desc)
	 {
		 // find distance between pacman and clyde
		 int verticalDiff   = getCurrentLocation().row() - desc.getPlayerLocation().row();  //y
		 int horizontalDiff = getCurrentLocation().col() - desc.getPlayerLocation().col();  //x
		 
		 double dis =  Math.sqrt(Math.pow(verticalDiff, 2) + Math.pow(horizontalDiff,2));  // [ (x2 -x1)^2 + (y2 - y1)^2 ]^(1/2) 
		 
		 if (dis > 8)                // if distance > 8 tials, target cell is pacman, otherwise return scatter target as trargetCell 
		 {
			 return desc.getPlayerLocation();
		 }
		 return getScatterTarget();
	 }
	
}
