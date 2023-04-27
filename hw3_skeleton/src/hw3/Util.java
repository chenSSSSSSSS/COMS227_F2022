package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import api.GridCell;
import api.Line;
import api.Location;
import api.StringUtil;

/**
 * Utility class with methods to help initializing a Lines game from 
 * a string descriptor, and for creating a collection of games from
 * a file containing descriptors.
 * @author Chen Sang
 */

public class Util
{
  /**
   * Given a 2d array of GridCell, constructs an array of Line
   * objects based on the information in the grid.  Specifically,
   * for each pair of endpoints with matching ids, a corresponding
   * Line object is constructed with that id and with the given endpoints.
   * The order of the endpoints (endpoint 0 vs endpoint 1) is unspecified.
   * If there are more than two endpoints with the same id, or if there
   * is only one endpoint with the given id, this
   * method returns null. No other error-checking is performed (e.g. there
   * may be middle cells with no matching endpoint, or the game
   * may be unsolvable for other reasons).
   * <p>
   * Note that in general the id for a Line will <em>not</em> be the
   * same as its index in the returned array.
   * @param grid
   *   a 2d array of GridCell
   * @return
   *   array of Line objects based on the grid information
   */
	public static ArrayList<Line> createLinesFromGrid(GridCell[][] grid)
	{

//		ArrayList <Line> l       = new ArrayList<Line>();
//		ArrayList <Location> loc = new ArrayList<Location> ();
//
//
//		for(int row = 0; row < grid.length; row ++)  // iterate all the cells
//		{
//			for(int col = 0; col < grid[0].length; col ++)
//			{
//				if(grid[row][col].isEndpoint())     // check the cell if it is the endPoints
//				{
//					Location locc = new Location(row, col);  // create a location variable
//					loc.add(locc);                           // add this to Location list
//				}
//			}
//		}
//
//		int count;   //count for the endpoint with specific id;
//
//		for(Location coor: loc)   
//		{
//			int id = grid[coor.row()][coor.col()].getId();  // get the id from coor
//			int k  = loc.indexOf(coor);                     // find this coor index in the Location ArrayList
//
//			Line ll = new Line(id);                         // creat new line object
//			ll.addEndpoint(coor);                           // add this endpoint to the line
//			count = 1;                                     // increament by 1 after added the first endPoint
//
//			for (int i = 0; i < loc.size(); i++ )
//			{
//				Location cell = loc.get(i);                            // Get this object from arrayLocation list
//				int id2       = grid[cell.row()][cell.col()].getId();  // find the ID for this location  
//				int indexEd   = loc.indexOf(cell);                     // get the index of this cell in ArrayList 
//
//				if (k != indexEd && id == id2 )     // find Edpoints that has same ID and has different index
//				{
//					count += 1;
//
//					if (count != 2)
//					{
//						return null;
//					}
//					else 
//					{
//						ll.addEndpoint(cell);
//					}
//				}
//
//			}
//
//			l.add(ll);
//		}
//
//		return l;  

	  
	 ArrayList <Line> line = new ArrayList <Line>();
	 GridCell[][] grids    = grid;
	 
	 int row;
	 int col;
	 int row1;
	 int col1;
	 
	 Location points;
	 int id1;	
	 int count;
	 
	 //find all the line in the grid, then add to the arrayList 
	 for(row = 0; row < grids.length; row += 1)
	 {
		 for (col = 0; col <grids[0].length; col +=1)
		 {
			 if (grids[row][col].isEndpoint())    // find the first EndPoint 
			 {
				 id1 = grid[row][col].getId();
				 count = 1;

				 points=null;

				 for (row1 = 0; row1 < grids.length; row1 += 1)
				 {
					 for (col1 = 0; col1 < grids[0].length; col1 += 1 )
					 {
						 int id2 = grid[row1][col1].getId();
						 
						 if ( (row != row1 || col != col1) && id1 == id2 && grids[row1][col1].isEndpoint()) // find the second points
						 {
							 count += 1;
							 points = new Location(row1,col1);
						 }
					 }
				 }
				 if( count != 2) // if did not find two endpoints return null
				 {
					 return null;
				 }
				 line.add(new Line(id1, new Location (row,col), points));  
			 }

		 }
		
	 }
	 
	 // since i have a duplicate line in the arrayList
	 // i creat an another empty list then use contains method to check
	 // if the new list does not have the this line object, add to the s
	 // if the new list has this line object, do nothing
	 ArrayList<Line> s = new ArrayList <Line>();  
	 
	 for(Line l: line)
	 {
		 if(!s.contains(l))
		 {
			 s.add(l);
		 }
	 }
	return s;
	  
   
  }
  
  /**
   * Reads the given file and constructs a list of LinesGame objects, one for
   * each descriptor in the file.  Descriptors in the file are separated by one or more
   * blank lines, where a "blank line" consists of some amount of whitespace and a 
   * newline character. The file may have extra whitespace at the beginning, 
   * and it must always end with one or more blank lines. Invalid descriptors
   * are ignored, so the method may return an empty list.  (A descriptor is "invalid"
   * if either createGridFromStringArray returns null, or createLinesFromGrid
   * returns null.)
   * @param filename
   *   name of the file to read
   * @return
   *   list of LinesGame objects created from the valid descriptors in the file
   * @throws FileNotFoundException
   *   if a file with the given name can't be opened
   */ 
  
  public static ArrayList<LinesGame> readFile(String filename) throws FileNotFoundException
  {

	 File files = new File(filename);
	 Scanner sc = new Scanner(files);
	 String line;
	 ArrayList<LinesGame> lineG = new ArrayList <>();
	 ArrayList<String>    s     = new ArrayList <>();
	 
	 while (sc.hasNextLine())
	 {
		 line = sc.nextLine();
		 
		 if (line.trim()== "")   // if line is empty, seperate the discritor
		 {
			if(!s.isEmpty())  // seprate the discritor if s is not empty is true
			{
				String [] arr = new String[s.size()];  // creat a new string arr list has same size with the string arraylist
				
				for(int i = 0; i < arr.length; i++)
				{
					arr[i] = s.get(i);     // add all the elements in List in Str array
				}
				
				GridCell [][] grid     = StringUtil.createGridFromStringArray(arr);  // create the grid 
				ArrayList <Line> linee = Util.createLinesFromGrid(grid);             // find all the line obaject in the gird
				
				if (grid != null && linee != null)            // if it is valid, add to the LineGame List
				{
					lineG.add(new LinesGame(grid, linee));
				}
				
				s.clear();                   // clear out the String arrayList 
			}
		 }
		 else
		 {
			s.add(line);  // add line to the List if it is not empty
		 }

	 }
	 sc.close();
    
	 return lineG;
  }
  

  
  /**
   * Determines whether a line between two diagonally adjacent locations
   * would cross any existing line in the given list.
   * The check is based on the following test:
   * <ul>
   *  <li>Let (rOld, cOld) denote the current cell location and let (rNew, cNew) denote
   * the new cell location.  
   *  <li>Let rDiff = rNew - rOld and cDiff = cNew - cOld.
   *  <li>If either rDiff or cDiff does not have absolute value 1, then
   *  the two positions are not diagonally adjacent and the method returns false
   *  <li>If the two positions are diagonally adjacent, then p0 = (rOld, cOld + cDiff) 
   *  and p1 = (rOld + rDiff, cOld) always form the opposite diagonal (i.e., the 
   *  line that could potentially be crossed).
   *  <li>The method returns true if p0 and p1 occur consecutively, in either order,
   *  in any existing line in the given array.
   * </ul>
   * 
   * @param lines
   *   list of Line objects
   * @param currentLoc
   *   any Location
   * @param newLoc
   *   any Location
   * @return
   *   true if the two locations are diagonally adjacent and some
   *   existing line crosses the opposite diagonal
   */
  public static boolean checkForPotentialCrossing(ArrayList<Line> lines, Location currentLoc, Location newLoc)
  {

	  int rOld = currentLoc.row();
	  int cOld = currentLoc.col();
	  
	  int rNew = newLoc.row();
	  int cNew = newLoc.col();
	  
	  int rDiff = rNew - rOld;
	  int cDiff = cNew - cOld;
	  
	  Location p0 = new Location (rOld, cOld + cDiff);   // new cell p0
	  Location p1 = new Location (rOld + rDiff, cOld);   // new cell p1
	  
	  if (Math.abs(rDiff) != 1 || Math.abs(cDiff) != 1)
	  {
		  return false;
	  }
	  
	  for (Line l: lines)
	  {
		  ArrayList<Location> loc = l.getCells(); 
		  
		  int i = loc.indexOf(p0);    
		  int j = loc.indexOf(p1);
		  
		  if (i >= 0 && j >= 0)
		  {
			  if (i == j - 1 || j == i - 1)
			  {
				  return true;
			  }
		  }
		  
	  }
	 
    return false;
  }
  
  /**
   * Determines whether any line in the given array already contains the segment between 
   * the given locations; that is, whether the two given locations occur consecutively,
   * in either order, in any of the given lines.
   * @param lines 
   *   any array of lines
   * @param currentLoc
   *   any position object
   * @param newLoc
   *   any position object
   * @return
   *   true if the two locations occur consecutively in some line
   */
  public static boolean checkForLineSegment(ArrayList<Line> lines, Location currentLoc, Location newLoc)
  {
	  for (Line l: lines)   // iterate the line List
	  {
		  ArrayList<Location> loc = l.getCells(); // get all the locations List in the line
		  
		  int indexOldCell = loc.indexOf(currentLoc);  // find index of the currentLoc in List 
		  int indexNewCell = loc.indexOf(newLoc);      // find index of the newLoc in List
		  
		  if (indexOldCell >= 0 && indexNewCell >= 0 ) 
		  {
			  if(indexOldCell == indexNewCell - 1 || indexNewCell == indexOldCell -1)
			  {
				  return true;
			  }
		  }
		  
	  }
	return false;
  }

}

