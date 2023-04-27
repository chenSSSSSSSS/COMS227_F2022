package hw4;
import java.util.Random;

import api.Descriptor;
import api.Direction;
import api.Location;
import api.MazeMap;


/**
 * Ghost for Inky
 * @author Chen Sang
 */
public class Inky extends Ghost
{
	public Inky(MazeMap maze, Location home, double baseSpeed, Direction homeDirection, Location scatterTarget, Random rand)
	{
		super(maze, home, baseSpeed, homeDirection, scatterTarget, rand);
	}
	
	/**
	 * Inky target cell in chase mode
	 * If pacman is up, go up 2 tails then go left 2 tails 
	 * otherwise, Find the cell that is two tail ahead based on pacman's location
	 * find it's vector in horizontal and vertical direction. 
	 * newCell would be blinky's location + vector * 2
	 * @param blinkyLocation
	 * @param playerLocation
	 * @param playerDirection
	 * @return
	 *    target cell
	 */
	protected Location chaseTarget(Descriptor desc)
	{
		// pacman row and col
		int rowPac = desc.getPlayerLocation().row();
		int colPac = desc.getPlayerLocation().col();
		
		//blinky row and col
		int rowBlinky = desc.getBlinkyLocation().row();
		int colBlinky = desc.getBlinkyLocation().col();
		
		// new cell that 2 tail ahead depends on left, down, right direction
		int newRow;
		int newCol;
		
		// vector for vertical(row) and horizontal(col) Direction
		int rowVector;  
		int colVector;
		
		// final taiget cell after vector * 2
		int row = 0;
		int col = 0;
		
		if(desc.getPlayerDirection() == Direction.UP)    
		{
			return new Location(rowPac - 2, colPac -2);  // go up 2 tails then go left 2 tails if diriction is up
		}
		else if(desc.getPlayerDirection() == Direction.LEFT)
		{   
			newRow = rowPac;
			newCol = colPac - 2;             // find cell that is 2 cell left, blinkyRow - newRow, blinkyCol - newCol
			
			rowVector = newRow - rowBlinky;  // calculate the vector
			colVector = newCol - colBlinky;
			
			row = rowBlinky + 2 * rowVector; // target cell location
			col = colBlinky + 2 * colVector;		
		}
		else if(desc.getPlayerDirection() == Direction.DOWN)
		{
			newRow = rowPac + 2;
			newCol = colPac;
			
			rowVector = newRow - rowBlinky;
			colVector = newCol - colBlinky;
			
			row = rowBlinky + 2 * rowVector;
			col = colBlinky + 2 * colVector;
		}
		else if(desc.getPlayerDirection() == Direction.RIGHT)
		{
			newRow = rowPac;
			newCol = colPac + 2;
			
			rowVector = newRow - rowBlinky;
			colVector = newCol - colBlinky;
			
			row = rowBlinky + 2 * rowVector;
			col = colBlinky + 2 * colVector;
		}
		return new Location (row, col);
	}
}