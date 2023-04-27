package hw4;
import java.util.Random;

import api.Descriptor;
import api.Direction;
import api.Location;
import api.MazeMap;

/**
 * Ghost for Pinky
 * @author Chen Sang
 */
public class Pinky extends Ghost
{

	public Pinky(MazeMap maze, Location home, double baseSpeed, Direction homeDirection, Location scatterTarget, Random rand)
	{
		super(maze, home, baseSpeed, homeDirection, scatterTarget, rand);
	}

	
	
	/**
	 * Pinky targetCell in chase mode
	 * is 4 tails ahead of currentDirecton based on pacman
	 * @param pacmanLocation
	 * @param playerDirection
	 * @return
	 *    targetCell
	 */    
	protected Location chaseTarget(Descriptor desc)
	{
		
		int rowPac = desc.getPlayerLocation().row();
		int colPac = desc.getPlayerLocation().col();
		
		if( desc.getPlayerDirection() == Direction.UP)
		{
			rowPac -= 4;
		}
		else if( desc.getPlayerDirection() == Direction.LEFT)
		{
			colPac -= 4;
		}
		else if( desc.getPlayerDirection() == Direction.DOWN)
		{
			rowPac += 4;
		}
		else if( desc.getPlayerDirection() == Direction.RIGHT)
		{
			colPac += 4;
		}
		
		Location loc = new Location(rowPac,colPac);
		return loc;
	}
	


	
}
