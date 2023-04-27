package hw3;


import java.util.ArrayList;

import api.GridCell;
import api.Line;
import api.Location;
import api.StringUtil;

/**
 * Game state for a Lines game.
 * @author Chen Sang
 */
public class LinesGame
{
  /*
   * grid cell fo this game
   */
  private GridCell[][] grid;
  /*
   * arraylist<line> object to save the line information
   */
  private ArrayList<Line> lines;
  
  /*
   * user's current line 
   */
  private Line currentLine;
  
  //private Location cells;
  
  /*
   * count the successful move from user
   */
  private int countMove;


  /**
   * Constructs a LinesGame from the given grid and Line list.
   * This constructor does not do any error-checking to ensure
   * that the grid and the Line array are consistent. Initially
   * the current line is null.
   * @param givenGrid
   *   a 2d array of GridCell
   * @param givenLines
   *   list of Line objects
   */
  public LinesGame(GridCell[][] givenGrid, ArrayList<Line> givenLines)
  {
    // TODO
	  grid        = givenGrid;
	  lines       = givenLines;
	  currentLine = null;
 
  }
  
  /**
   * Constructs a LinesGame from the given descriptor. Initially the
   * current line is null.
   * @param descriptor
   *   array of strings representing initial state
   */
  public LinesGame(String[] descriptor)
  {
    currentLine = null;
    grid        = StringUtil.createGridFromStringArray(descriptor);
    lines       = Util.createLinesFromGrid(grid);
    countMove   = 0;
    
    
	  
  }
  
  /**
   * Returns the number of columns for this game.
   * @return
   *  width for this game
   */ 
  public int getWidth()
  {
	  return grid[0].length;
  }
  
  /**
   * Returns the number of rows for this game.
   * @return
   *   height for this game
   */ 
  public int getHeight()
  {
	  
    return grid.length;
  }
  
  /**
   * Returns the current cell for this game, possibly null.
   * The current cell is just the last location, if any, 
   * in the current line, if there is one. Returns null
   * if the current line is null or if the current line
   * has an empty list of locations.
   * @return
   *   current cell for this game, or null
   *   
   */
  public Location getCurrentLocation()
  {
	  if(currentLine == null)
	  {
		  return null;
	  }
	  else 
	  {
		  return currentLine.getLast();
	  }
  }
  
  /**
   * Returns the id for the current line, or -1
   * if the current line is null.
   * @return
   *   id for the current line
   */
  public int getCurrentId()
  {
	if (currentLine == null)
	{
		 return -1;
	}
	else 
	{
		return currentLine.getId();
	}
  }
  
  /**
   * Return this game's current line (which may be null).
   * @return
   *   current line for this game
   */
  public Line getCurrentLine()
  {
    return currentLine;
  }
  
  /**
   * Returns a reference to this game's grid.  Clients should
   * not modify the array.
   * @return
   *   the game grid
   */
  public GridCell[][] getGrid()
  {
    return grid;
  }
  
  /**
   * Returns the grid cell at the given position.
   * @param row
   *   given row
   * @param col
   *   given column
   * @return
   *   grid cell at (row, col)
   */
  public GridCell getCell(int row, int col)
  { 
    return grid[row][col];
  }
  
  /**
   * Returns all Lines for this game.  Clients should not modify
   * the returned list or the Line objects.
   * @return
   *   list of lines for this game
   */ 
  public ArrayList<Line> getAllLines()
  {
    return lines;
  }
  
  /**
   * Returns the total number of moves.  A "move" means that a 
   * new Location was successfully added to the current line
   * in addCell.
   * @return
   *   total number of moves so far in this game
   */
  public int getMoveCount()
  {
 
    return countMove;
  }
  
  /**
   * Returns true if all lines are connected and all
   * cells are at their maximum count.
   * @return
   *   true if all lines are complete and all cells are at max
   */ 
  public boolean isComplete()
  {
	  for (Line l: lines)    //iterate everyLine object in the list
	  {
		  if( !l.isConnected()) 
		  {
			  return false;
		  }
      }
	  
	  for (int row = 0; row < getHeight(); row ++)
	  {
		  for (int col = 0; col < getWidth(); col ++)
		  {
			  if (!grid[row][col].maxedOut())
			  {
				  return false;
			  }
		  }
	  }
	  
	  return true;
  }
  
  /**
   * Attempts to set the current line based on the given
   * row and column.  When using a GUI, this method is typically 
   * invoked when the mouse is pressed. If the current line is 
   * already non-null, this method does nothing.
   * There are two possibilities:
   * <ul>
   *   <li>Any endpoint can be selected.  Selecting an 
   *   endpoint clears the line associated with that endpoint's id,
   *   and all cells that were previously included in the line are decremented.
   *   The line then becomes the current line, and the endpoint is incremented
   *   and placed on the line's list of locations as its only element.
   *   <li>A non-endpoint cell can be selected if it is not a crossing
   *   and if it is the last cell in some line.  That line then becomes
   *   the current line.
   * </ul>
   * If neither of the above conditions is met, or if the
   * current line is non-null, this method does nothing.
   * 
   * @param row
   *   given row
   * @param col
   *   given column
   */
  public void startLine(int row, int col)
  {
	  Location loc = new Location (row, col);
	  GridCell cells = grid[row][col];

	  if (currentLine != null)
	  {
		  return;
	  }

	  // clean the points once i clicked the endpoints
	  if (cells.isEndpoint())
	  {
		  for (Line l: lines)
		  {   
			  if (cells.getId() == l.getId()) // find line's id with the same id in endPoints
			  {
				  ArrayList<Location> line = l.getCells(); // find the sequence in the line
				  for(Location d : line)  
				  {    
					  grid[d.row()][d.col()].decrement();   // decrement for each cell in the list
				  }

				  l.clear();            // clean the line assciate with the endpoints
				  currentLine = l;      // the line become current line
				  currentLine.add(loc); // add this endpoint to the location
				  grid[row][col].increment(); // increament the cell 

			  }
		  }
	  }
	  else if (!cells.isCrossing())// non end-points can be selceted to be current line
	  {
		  for (Line l: lines)
		  {  
			  if(cells.getId() == l.getId()) // find the line that has same id with the cell
			  {
				  if (l.getLast().col() == loc.col() && l.getLast().row() == loc.row()) // check if this cell in the last location in the line
				  {
					  currentLine = l;
				  }
			  }
		  }
	  }


  }
  
  /**
   * Sets the current line to null. When using a GUI, this method is 
   * typically invoked when the mouse is released.
   */
  public void endLine()
  {
	  currentLine = null;
  }
  
  /**
   * Attempts to add a new cell to the current line.  
   * When using a GUI, this method is typically invoked when the mouse is 
   * dragged.  In order to add a cell, the following conditions must be satisfied.
   * Here the "current cell" is the last cell in the current line, and "new cell"
   * is the cell at the given row and column:
   * :
   * <ol>
   *   <li>The current line is non-null
   *   <li>The current line is not connected
   *   <li>The given row and column are adjacent to the location of the current cell
   *       (horizontally, vertically, or diagonally) and not the same as the current cell
   *   <li>The count for the new cell is less than its max count
   *   <li>If the new cell is a MIDDLE or ENDPOINT, then its id matches
   *   the id for the current line
   *   <li>Adding the new cell will not cause the line to re-trace any
   *   existing line (according to the result of Util.checkForLineSegment)
   *   <li>Adding the new cell to the line would not cross any existing line
   *   (according to the result of Util.checkForPotentialCrossing)
   * </ol>
   * If the above conditions are met, a new Location at (row, col) is added
   * to the current line and the cell count is incremented.  Otherwise, the 
   * method does nothing.  If a new location
   * is added to the current line, the move counter is increased by 1.
   * @param row
   *   given row for the new cell
   * @param col
   *   given column for the new cell
   */
  public void addCell(int row, int col)
  {
 
	  Location newCell = new Location(row, col);

	  Location currentCell = currentLine.getLast();

	  GridCell cell = grid[row][col];

	  //crossing cell does not have same id with currentLine, need to fix the bug!
	  //cellCheckHelper for adjacent might have bugs too
//	  if(currentLine != null && !currentLine.isConnected())
//	  {
//
//		  if (!cell.maxedOut() && (cell.isCrossing()|| cell.isOpen()))
//		  {
//			  currentLine.add(newCell);
//			  countMove += 1;
//			  cell.increment();
//		  }
//		  if (cellCheckHelper(currentCell, newCell) && (!Util.checkForLineSegment(line,currentCell,newCell))
//				  && (!Util.checkForPotentialCrossing(line, currentCell, newCell))
//				  && (currentLine.getId() == cell.getId()) && (!cell.maxedOut())
//				  && (cell.isMiddle() || cell.isEndpoint()))
//		  {
//			  currentLine.add(newCell);
//			  countMove += 1;
//			  grid[row][col].increment();
//		  }
//	  }
      if (currentLine != null && !currentLine.isConnected())   // check cLine is not null and line is not connected
      {
    	  if(cellCheckHelper(currentCell, newCell))            // check the new cell if it adjacent to currentCell 
    	  {
    		  if (!Util.checkForLineSegment(lines,currentCell,newCell) && (!Util.checkForPotentialCrossing(lines, currentCell, newCell)))
    		  {
    			  if((cell.isCrossing()|| cell.isOpen()) && !cell.maxedOut())
    			  {
    				  currentLine.add(newCell);
    				  countMove += 1;
    				  cell.increment();
    			  }
    			  if ((cell.isMiddle() || cell.isEndpoint()) && (currentLine.getId() == cell.getId()) && (!cell.maxedOut()))
    			  {
    				  currentLine.add(newCell);
    				  countMove += 1;
    				  cell.increment();
    			  }
    		  }
    	  }
      }
	  
  }
  

  /**
   * Check new cell if is adjacent to the current cell
   * if it is not adjecent to the current cell return false
   * else return true
   * 
   * @parm currentCell 
   *   is the last cell in the current line
   * @parm newCell
   *   is the cell at the given row and column from addCell
   * 
   * @return 
   *   return true if is a newCell is adjacent to currentCel
   *   return false if is not a newCell is adjacent to currentCel
   */
  private static boolean cellCheckHelper(Location currentCel, Location newCell)
  {
	  int row = currentCel.row();
	  int col = currentCel.col();
	  
	  int row1 = newCell.row();
	  int col1 = newCell.col();
	  
	  if((Math.abs(row - row1)<=1 && Math.abs(col - col1) <= 1) && (row != row1 || col != col1) )  
	  {
		  return true;
	  }

	 return false;
}

  /**
   * Returns a string representation of this game.
   */
  public String toString()
  {
    String result = "";
    result += "-----\n";
    result += StringUtil.originalGridToString(getGrid());
    result += "-----\n";
    result += StringUtil.currentGridToString(getGrid(), getAllLines());
    result += "-----\n";
    result += StringUtil.allLinesToString(getAllLines());
    Line ln = getCurrentLine();
    if (ln != null)
    {
      result += "Current line: " + ln.getId() + "\n";
    }
    else
    {
      result += "Current line: null\n";
    }
    return result;
  }

}
