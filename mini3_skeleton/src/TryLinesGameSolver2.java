
import java.util.ArrayList;
import api.Line;
import api.Location;
import api.Solution;
import hw3.LinesGame;
import mini3.LinesGameSolver;
/**
 * Variation of TryLinesGameSolver that allows you to specify
 * a maximum number of solutions to find and terminates the search.
 * This uses some programming tricks we have not seen yet (inheritance,
 * polymorphism, and exception handling) but will have covered by the 
 * end of the semester.
 */
public class TryLinesGameSolver2
{
  public static void main(String[] args)
  {
    String[] desc = TryLinesGameSolver.testgrid8;
    
    // try finding just one solution
    ArrayList<Solution> solutions = doSolveAlt(desc, 1);
    
    System.out.println(solutions.get(0));
  }
  /**
   * Similar to the doSolve method of LinesGameSolver, but stops after finding
   * at most 'max' solutions.
   * @param descriptor
   * @param max
   * @return
   */
  private static ArrayList<Solution> doSolveAlt(String[] descriptor, int max)
  {
    LinesGame game = new LinesGame(descriptor);
    ArrayList<Line> lines = game.getAllLines();
    Line line = lines.get(0);
    Location loc = line.getEndpoint(0);
    game.startLine(loc.row(), loc.col());
    
    // Use an "n-element array list" instead of an arraylist
    NElementArrayList solutions = new NElementArrayList(max);
    
    try
    {
      LinesGameSolver.findSolutions(game, solutions); 
    }
    catch (FoundEnoughSolutionsException e)
    {
      // intentionally fall through, in case we didn't actually find
      // enough solutions to trigger the exception
    }
    
    return solutions;
  }
  
  
  /**
   * Extension of ArrayList that throws a special exception as soon
   * as a fixed number of elements have been added via the 'add' method.
   */
  private static class NElementArrayList extends ArrayList<Solution>
  {
    private int max;
    public NElementArrayList(int max)
    {
      this.max = max;
    }
    @Override
    public boolean add(Solution s)
    {
      super.add(s);
      if (this.size() == max)
      {
        throw new FoundEnoughSolutionsException();
      }
      return true;
    }
  }
  /**
   * Exception type used in NElementArrayList.
   */
  private static class FoundEnoughSolutionsException extends RuntimeException
  {
    
  }
}