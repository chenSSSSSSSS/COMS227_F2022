package hw4;

import java.util.ArrayList;
import java.util.Random;


import api.Direction;
import api.Descriptor;
import api.Location;
import api.MazeMap;
import api.Mode;

/**
 * Ghost abstract class that contains the common methods and instance variable
 * @author Chen Sang
 *
 */
abstract class Ghost extends SuperActor
{
	/**
	 * Mode for ghost
	 */
	private Mode m;
	
	/**
	 * fixed target for use during scatter mode
	 */
	private Location scatterTarget;
	
	/**
	 * generate random number for direction in Frightened mode
	 */
	private Random rand;
	
	/**
	 *  Next location after current location
	 */
	private Location nextLoc;
	
	/**
	 * next direction after current direction
	 */
	private Direction nextDir;
	
	
	protected Ghost(MazeMap maze, Location home, double baseSpeed, Direction homeDirection, Location scatterTarget, Random rand) 
	{
		super(maze, home, baseSpeed, homeDirection);
		this.scatterTarget = scatterTarget;
		this.rand          = rand;
	}

	/**
	 * return next location 
	 * @return 
	 *    nextLoc
	 */
	public Location getNextCell()   
	{
		return nextLoc;            
	}
	
	/**
	 * return the scatter target for the ghost
	 * @return
	 *   scatterTarget
	 */
	protected Location getScatterTarget()
	{
		return scatterTarget;
	}
	
	@Override
	public Mode getMode()
	{
		return m;
	}
	
	@Override
	public void reset()
	{
		super.reset();
		m = Mode.INACTIVE;
	}
	
	@Override
	public void setMode(Mode mode, Descriptor desc)
	{

		if(Mode.CHASE == mode)
		{
			m = Mode.CHASE;
			setCurrentIncrement(getBaseIncrement());
		}

		else if(Mode.SCATTER == mode)
		{
			m = Mode.SCATTER;
			setCurrentIncrement(getBaseIncrement());
		}

		else if(Mode.FRIGHTENED == mode)
		{
			m = Mode.FRIGHTENED;
			setCurrentIncrement(getBaseIncrement() * (2 / 3.0)) ;
		}

		else if(Mode.DEAD == mode)
		{
			m = Mode.DEAD;
			setCurrentIncrement(getBaseIncrement() * 2);
		}
		
		// pass the center should not triger calcNextcell
		if(distanceToCenter() < getCurrentIncrement() && distanceToCenter() > -ERR ) 
		{
		   calculateNextCell(desc);
		}
	}
	
	@Override
	public void update(Descriptor desc) 
	{
		if (Mode.INACTIVE == getMode())
		{
			return;
		}
        
		if (getCurrentDirection() == null)
		{
			return;
		}
		
		double increment         = getCurrentIncrement();
		double curRowExact       = getRowExact();   
		double curColExact       = getColExact();   
        double diff              = distanceToCenter();
		Location currentLocation = getCurrentLocation();		
		
		if(getCurrentDirection() == Direction.UP)     
		{
			if(nextDir != Direction.UP && diff < increment && diff > -ERR)  // if nextDir is not the same as currentDir, increemnt cannot pass the center
			{
				increment = diff;
				
				setRowExact(curRowExact - increment);
				setDirection(nextDir);
			}
			else 
			{
			curRowExact -= increment;
			setRowExact(curRowExact);
			}
		}
		else if(getCurrentDirection() == Direction.LEFT)
		{
			if(nextDir != Direction.LEFT && diff < increment && diff > -ERR)
			{
				increment = diff;
				
				setColExact(curColExact - increment);
				setDirection(nextDir);
			}
			else 
			{
				curColExact -= increment;
				
				//tunnel
				if(curColExact < 1) 
				{
					nextLoc     = new Location(getCurrentLocation().row(), getMaze().getNumColumns()-1);
					curColExact = getMaze().getNumColumns() + curColExact;
				}
				setColExact(curColExact);
			}
		}
		else if(getCurrentDirection() == Direction.DOWN)
		{
			if(nextDir != Direction.DOWN && diff < increment && diff > -ERR)
			{
				increment = diff;
				setRowExact(curRowExact + diff);
				setDirection(nextDir);
			}
			else
			{
				curRowExact += increment;
				setRowExact(curRowExact);
			}
		}
		else if(getCurrentDirection() == Direction.RIGHT)
		{
			if(nextDir != Direction.RIGHT && diff < increment && diff > -ERR)
			{
				increment = diff;
				setColExact(curColExact + increment);
				setDirection(nextDir);
			}
			else 
			{
				curColExact += increment;
				
				// tunnel
				if(curColExact > getMaze().getNumColumns())
				{
					nextLoc = new Location(getCurrentLocation().row(), 2);
					curColExact = curColExact - getMaze().getNumColumns();
				}
				setColExact(curColExact);
			}
		}
		
		// if crossed into a new cell
		if(! currentLocation.equals(getCurrentLocation()))
		{
			calculateNextCell(desc);
		}	
	}
	

	
	public void calculateNextCell(Descriptor desc)
	{
		// in tunnel, calculate cell do nothing, since nextCell implemented in update method
		if(getCurrentLocation().col() <= 0 || getCurrentLocation().col() >= getMaze().getNumColumns() - 1)
		{
			return;
		}

		if(getMode() == Mode.SCATTER)
		{
			findTheNextDirection(getScatterTarget());
		}
		else if(getMode() == Mode.DEAD)
		{
			findTheNextDirection(getHomeLocation());
		}
		else if(getMode() == Mode.FRIGHTENED)
		{
			frightenedMood(getCurrentLocation());
		}
		else if( getMode() == Mode.CHASE)
		{
			Location targetCell = chaseTarget(desc);
			findTheNextDirection(targetCell);
		}
	}
   
	/** 
	 * Find the target cell for each ghost
	 * @param desc
	 * @return
	 *   targetCell
	 */
	protected abstract Location chaseTarget(Descriptor desc);

	/**
     * Find the next cell and direction based on the samllest distance around ghost currentlocation and target cell
     * nextCell and nextDirection cannot be the opposite direction to current Direction
     * @param TargetTail
     */
	protected void findTheNextDirection(Location TargetTail)
	{
		// find vertical and horizon distance between targetCell and ghost 
		Location currentLoc = getCurrentLocation();
		double verticalDistance   = currentLoc.row() - TargetTail.row();
		double horizontalDistance = currentLoc.col() - TargetTail.col();

		double upDistance    = 1000;
		double leftDistance  = 1000;
		double downDistance  = 1000;
		double rightDistance = 1000;

		//up cell  
		if(!getMaze().isWall(currentLoc.row() - 1, currentLoc.col()) && getCurrentDirection() != Direction.DOWN)
		{
			upDistance = Math.sqrt(Math.pow(verticalDistance - 1,2) + Math.pow(horizontalDistance,2));
		}
		
		//left cell
		if(!getMaze().isWall(currentLoc.row(), currentLoc.col() - 1) && getCurrentDirection() != Direction.RIGHT )
		{
			leftDistance = Math.sqrt(Math.pow(verticalDistance,2) + Math.pow(horizontalDistance -1,2));
		}
		
		//down Cell
		if(!getMaze().isWall(currentLoc.row()+ 1, currentLoc.col()) && getCurrentDirection() != Direction.UP)
		{
			downDistance = Math.sqrt(Math.pow(verticalDistance + 1,2) + Math.pow(horizontalDistance,2));
		}
		
		//right cell
		 if(!getMaze().isWall(currentLoc.row(), currentLoc.col() + 1) && getCurrentDirection() != Direction.LEFT)
		{
			rightDistance = Math.sqrt(Math.pow(verticalDistance,2) + Math.pow(horizontalDistance + 1,2));
		}

		// find miniDistance in 4 direction
		double[] dis = {upDistance, leftDistance, downDistance, rightDistance};

		double miniDistance = dis[0];
		int indx = 0;

		for (int i = 1; i < dis.length; i += 1)
		{
			if(dis[i] < miniDistance - ERR )
			{
				miniDistance = dis[i];
				indx = i; 
			}
		}
		
		//set nextDirection and nextLocation
		if (indx == 0)
		{
			nextLoc = new Location(getCurrentLocation().row() - 1, getCurrentLocation().col());
      		nextDir = Direction.UP;
		}
		else if(indx == 1)
		{
			nextLoc = new Location(getCurrentLocation().row(), getCurrentLocation().col() - 1);
			nextDir = Direction.LEFT;
		}
		else if (indx == 2)
		{
			nextLoc = new Location(getCurrentLocation().row() + 1, getCurrentLocation().col());
			nextDir = Direction.DOWN;
		}
		else if(indx == 3)
		{
			nextLoc = new Location(getCurrentLocation().row(), getCurrentLocation().col() + 1);
			nextDir = Direction.RIGHT;
		}
	}
	
	/**
	 * Find the next Direction and next cell in frightenMode
	 * using random number
	 * @param Loc
	 */
	protected void frightenedMood(Location Loc)
    {
    	ArrayList <Direction> arr = new ArrayList<>();   // creat empty arraylist
    	
    	//up cell
    	if(!getMaze().isWall(Loc.row() - 1, Loc.col()))  // if it is not a wall, added to the list
    	{
    		arr.add(Direction.UP);
    	}
    	
    	//left cell
    	if(!getMaze().isWall(Loc.row(), Loc.col() - 1))
    	{
    		arr.add(Direction.LEFT);
    	}
    	
    	//down cell
    	if(!getMaze().isWall(Loc.row() + 1, Loc.col()))
    	{
    		arr.add(Direction.DOWN);
    	}
    	
    	// right cell
    	if(!getMaze().isWall(Loc.row(), Loc.col() + 1))
    	{
    		arr.add(Direction.RIGHT);
    	}
    	
    	int size = arr.size();        // find the size of arr
    	int num  = rand.nextInt(size); // get random number for direction
    	nextDir  = arr.get(num);       // set next direction based on random num in the arrayList

    	if(nextDir == Direction.UP)   //find the nextCell based on nextDirection
    	{
    		nextLoc =  new Location(getCurrentLocation().row() - 1, getCurrentLocation().col());
    	}
    	else if(nextDir == Direction.LEFT)
    	{
    		nextLoc = new Location(getCurrentLocation().row(), getCurrentLocation().col() - 1);
    	}
    	else if(nextDir == Direction.DOWN)
    	{
    		nextLoc = new Location(getCurrentLocation().row() + 1, getCurrentLocation().col());
    	}
    	else if(nextDir == Direction.RIGHT)
    	{
    		nextLoc = new Location(getCurrentLocation().row(), getCurrentLocation().col() + 1);
    	}
    }
	
}
