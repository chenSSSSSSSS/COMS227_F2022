package hw4;

import api.Actor;
import api.Descriptor;
import api.Direction;
import api.Location;
import api.MazeMap;
import api.Mode;
/**
 * abstract class for pacman and Ghost class that contains the same methods and instance variable
 * @author Chen Sang
 */
abstract class SuperActor implements Actor 
{
	/**
	 * Margin of error for comparing exact position to centerline
	 * of cell.
	 */
	public static final double ERR = .001;

	/**
	 * Maze configuration.
	 */
	private MazeMap maze;

	/**
	 * Initial location on reset().
	 */
	private Location home;

	/**
	 * Initial direction on reset().
	 */
	private Direction homeDirection;

	/**
	 * Current direction of travel.
	 */
	private Direction currentDirection;

	/**
	 * Basic speed increment, used to determine currentIncrement.
	 */
	private double baseIncrement;

	/**
	 * Current speed increment, added in direction of travel each frame.
	 */
	private double currentIncrement;

	/**
	 * Row (y) coordinate, in units of cells.  The row number for the
	 * currently occupied cell is always the int portion of this value.
	 */
	private double rowExact;

	/**
	 * Column (x) coordinate, in units of cells.  The column number for the
	 * currently occupied cell is always the int portion of this value.
	 */
	private double colExact;

	protected SuperActor(MazeMap maze, Location home, double baseSpeed, Direction homeDirection)
	{
		this.maze             = maze;
		this.home             = home;
		baseIncrement         = baseSpeed;
		currentIncrement      = baseSpeed;
		this.homeDirection    = homeDirection;
	}

	public double getBaseIncrement()
	{
		return baseIncrement;
	} 

	public double getCurrentIncrement()
	{
		return currentIncrement;
	}

	public Direction getCurrentDirection()
	{
		return currentDirection;
	}

	public Location getCurrentLocation()
	{
		return new Location((int) rowExact, (int) colExact);
	}

	public Direction getHomeDirection()
	{
		return homeDirection;
	}

	public Location getHomeLocation()
	{
		return home;
	}

	public double getRowExact()
	{
		return rowExact;
	}

	public double getColExact()
	{
		return colExact;
	}

	public Mode getMode()
	{
		return null;
	}

	public void setColExact(double c)
	{
		colExact = c;
	}

	public void setDirection(Direction dir)
	{
		currentDirection = dir;
	}

	public void setRowExact(double r)
	{
		rowExact = r;
	}
    
	
	/**
	 * return the maze 
	 * @return
	 *    maze
	 */
	protected MazeMap getMaze() 
	{
		return this.maze;
	}
	
	/**
	 * setCurrentIncrement when ghost mode changes
	 * no mode change in pacman 
	 * @param d
	 */
	protected void setCurrentIncrement(double d)
	{
		currentIncrement = d;
	}
	
	public void reset()
	{
		Location homeLoc = getHomeLocation();
		setRowExact(homeLoc.row() + 0.5);
		setColExact(homeLoc.col() + 0.5);
		setDirection(getHomeDirection());
		currentIncrement = getBaseIncrement();
	}

	public void setMode(Mode mode, Descriptor desc)
	{
		//does nothing
	}

	public void update(Descriptor d)  
	{
		//does nothing
	}
	
	
	protected double distanceToCenter()
	{
		double colPos = getColExact();
		double rowPos = getRowExact();
		switch (getCurrentDirection())
		{
		case LEFT:
			return colPos - ((int) colPos) - 0.5;
		case RIGHT:
			return 0.5 - (colPos - ((int) colPos));
		case UP:
			return rowPos - ((int) rowPos) - 0.5;
		case DOWN:
			return 0.5 - (rowPos - ((int) rowPos));
		}    
		return 0;
	}
}
