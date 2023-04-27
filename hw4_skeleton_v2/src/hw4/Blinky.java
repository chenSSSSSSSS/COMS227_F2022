package hw4;
import java.util.Random;



import api.Descriptor;
import api.Direction;
import api.Location;
import api.MazeMap;
import api.Mode;

/**
 * Ghost for Blinky
 * @author Chen Sang
 */
public class Blinky extends Ghost
{

	public Blinky(MazeMap maze, Location home, double baseSpeed, Direction homeDirection, Location scatterTarget, Random rand)
	{
		super(maze, home, baseSpeed, homeDirection, scatterTarget, rand);
	}

	protected Location chaseTarget(Descriptor desc)
	{
		return desc.getPlayerLocation();
	}

}
