package hw2;

import api.ThrowType;
import static api.ThrowType.*;

/**
 * This class models a standard game of darts, keeping track of the scores, whose turn it is, 
 * and how many darts the current player has remaining. 
 * The number of starting points and the number of darts used in a player's turn are configurable.
 * @author Chen Sang
 */
public class DartGame {
	/**
	 * Score for player 0
	 */
	private int scorePlayer0;
	
	/**
	 * Score for player 1
	 */
    private int scorePlayer1;
	
    /** 
     * initial points of this game
     */
	private int initialPoints;
	
	/**
	 * number of darts for each player
	 */
	private int numberDart;
	
	/** 
	 * save for the current player (0 or 1) 
	 */
	private int currentPlayer;
	
	/** 
	 * current number of darts for this current player
	 */
	private int currentDart;
	
	/** 
	 * save current number of points for current player before they start throw darts
	 */
	private int turningPlayerPoints;
	
	/**
	 * wheather the player0 or player1 has doubling in 
	 */
	private boolean doublingInPlayer0;   // defalt false 
	
	private boolean doublingInPlayer1;   // defalt false 
	
/**
 * Constructs a DartGame with the given starting player, initial points 301, and three darts for each player's turn.
 * @param startingPlayer
 */
	public DartGame(int startingPlayer) 
	{
		currentPlayer       = startingPlayer;
		scorePlayer0        = 301;
		scorePlayer1        = 301;
		initialPoints       = 301;
		numberDart          = 3;
		currentDart         = numberDart;
		turningPlayerPoints = 0;
	}
	
/**
 * Constructs a DartGame with the given starting player, initial number of points, and number of darts.
 * @param startingPlayer
 * @param startingPoints
 * @param numDarts
 */
	public DartGame (int startingPlayer, int startingPoints, int numDarts ) 
	{
		currentPlayer       = startingPlayer;
		initialPoints       = startingPoints;
		numberDart          = numDarts;	
		scorePlayer0        = initialPoints;
		scorePlayer1        = initialPoints;
		currentDart         = numberDart;
		turningPlayerPoints = 0;
	}
	
	/**
	 * Returns the player whose turn it is. 
	 * (When the game is over, this method always returns the winning player.)
	 * @return currentPlayer(0 or 1)
	 */
	public int getCurrentPlayer() 
	{
		return currentPlayer;
	}
	
	/**
	 * Returns the score of the indicated player (0 or 1). 
	 * If the argument is any value other than 0 or 1, the method returns -1.
	 * @param which - indicator for which player (0 or 1)
	 * @return score for the indicated player, or -1 if the argument is invalid
	 */
	public int getScore(int which ) {

		if (which == 0)             // if  player is player 0
		{
			return scorePlayer0;               
		}
		else if (which == 1)        // if  player is player 1
		{
			return scorePlayer1;         
		}
		else
		{
			return -1;              // unvalied player return -1
		}
	}
   
    /**
     * Returns the number of darts left in the current player's turn.
     * @return the number of darts left in the current player's turn
     */
    public int getDartCount() 
    {
    	return currentDart;     	
    }
    
    /**
     * find the current player's points 
     * @return - score of current player
     */
    private int currentPlayerPoints() 
    {
     if (currentPlayer == 0) 
     {
    	 return scorePlayer0;
     }
     else
     {
    	 return scorePlayer1;
     }
    }
    
    /**
     * check if the current player has doubling in or not, return true or false
     * @return true or false
     *    true  - current player has doubling in 
     *    false - current player has not doubling in 
     */
    private boolean hasDoubledIn()
    {
    	if (currentPlayer == 0) 
    	{
    		return doublingInPlayer0;
    	}
    	else if (currentPlayer == 1)
    	{
    		return doublingInPlayer1;
    	}
    	return false;
    }
    
   /**
    * Simulates the action of the current player throwing one dart, adjusting the points as needed. 
    * Switches to the other player if the current player uses up all darts or busts; 
    * does not switch to the other player if the current player wins.
    * Does nothing if the game is already over. 
    * The number parameter is ignored in the case of type MISS, OUTER_BULLSEYE, or INNER_BULLSEYE. 
    * @param type of throw 
    * @param number -segment of the dartboard on which the throw lands
    */
    public void throwDart(ThrowType type, int number)
    {
    	int points = calcPoints(type, number);
    	int newDarts = numberDart-1;

    	// before throw dart, check wheather the game is over or not
    	// if isOver is ture, exits throw dart method
    	// if isOver is false, continue the throw dart method
    	if (isOver())
    	{
    		return;                
    	}

    	currentDart -= 1;                                      // update darts everytime they throw 
    	
    	if (!hasDoubledIn())                                   // check current player wheather has doubling in or not
    	{                                                      // if player doesn't have it, not going to counts the score
    		if (type.equals(DOUBLE)|| type.equals(INNER_BULLSEYE))
    		{
    			doublingIn ();                                 // update if the current player has doubling in 
    		}
//    		if (currentDart == 0)
//        	{
//        		switchPlayer();
//        	}
    	}
 
    	// excute when player has doubling in 
    	if (hasDoubledIn())                                    
    	{	
    		if (newDarts == currentDart) 
    		{
    			turningPlayerPoints = currentPlayerPoints();   // save current score before the turn starts
    		}
    	     

    		adjustScore(points);                      

    		 //check if currentplayer won 
    		if ((currentPlayerPoints() == 0) && ((type.equals(DOUBLE) || type.equals(INNER_BULLSEYE))))  
    		{
    			whoWon();
    		}
    		// check if current player is busted, then resetting the score and switch player
    		else if (currentPlayerPoints() == 1 || currentPlayerPoints() < 0)   
    		{
    			resettingScore();
    			switchPlayer();
    		}
    		else if(currentPlayerPoints() == 0 && (!(type.equals(DOUBLE) || type.equals(INNER_BULLSEYE)))) 
    		{
    			resettingScore();
    			switchPlayer();
    		}
    		// if current user finished their darts, then switch to next player
    		else if (currentDart == 0)                                    
    		{
    			switchPlayer();
    		}
    	}
    	else if (currentDart == 0)
    	{
    		switchPlayer();
    	}
    }
    
    /**
     * update if current player has doubling in 
     */
    private void doublingIn () 
    {
    	if (currentPlayer == 0)
    	{
    		doublingInPlayer0 = true;
    	}
    	else if(currentPlayer == 1)
    	{
    		doublingInPlayer1 = true;
    	}
    }  
    
    /**
     * find the currentplayer to
     * reset the socre before every turn starts in case they busts 
     */
    private void resettingScore()
    {
    	if (currentPlayer == 1)
    	{
    		scorePlayer1 = turningPlayerPoints;
    	}
    	else 
    	{
    		scorePlayer0 = turningPlayerPoints;
    	}
    }
   
    /**
     * Reduces the score for the current player by the given amount 
     * @param amount
     * number of points to subtract
     */
    private void adjustScore(int amount)
    {
    	if (currentPlayer == 1)
    	{
    		this.scorePlayer1 = this.scorePlayer1 - amount;
    	}
    	
    	if (currentPlayer == 0)
    	{
    		this.scorePlayer0 = this.scorePlayer0 - amount;
    	}
    }
    
    /**
     * Switches players and resets the dart count 
     * and satrting score for the current player's turn
     */
    private void switchPlayer()
    {
    	currentDart = numberDart;

    	if (currentPlayer == 1)
    	{
    		currentPlayer = 0;
    	}
    	else 
    	{
    		currentPlayer = 1;
    	}
    	turningPlayerPoints = currentPlayerPoints(); 			
    }

    /**
     * Returns true if one of the players has a score of zero, false otherwise.
     * @return true if the game has ended, false otherwise
     */
    public boolean isOver()
    {
    	if (scorePlayer0 == 0 || scorePlayer1 == 0)
    	{
    		return true;
    	}
    	else
    	{
    	    return false;
    	}
    }

    /**
     * Returns the winner (0 or 1), or -1 if the game is not over.
     * @return winner (0 or 1), or -1 in case the game is not over
     */
    public int whoWon()
    {
    	if (scorePlayer1 == 0)
    	{
    		return 1;
    		
    	}
    	else if (scorePlayer0 == 0)
    	{
    		return 0;
    	}
    	else
    	{
    		return -1;
    	}
    }
      
    /**
    * Returns the number of points for the given throw. 
    * The number parameter is ignored in the case of type MISS, OUTER_BULLSEYE, or INNER_BULLSEYE.
    * @param type
    * @param number
    * @return number of points for the given throw
    */
    public static int calcPoints(ThrowType type, int number)
    {    
    	ThrowType t = type;
        int points  = 0;
     	if (t == ThrowType.DOUBLE)
     	{
     		points = number * 2;
     	}
     	else if (t == ThrowType.SINGLE)
     	{
     		points = number * 1;
     	}
     	else if (t == ThrowType.TRIPLE)
     	{
     		points =  number * 3;
     	}
     	else if (t == ThrowType.OUTER_BULLSEYE)
     	{
     		points =  25;
     	}
     	else if (t == ThrowType.INNER_BULLSEYE)
     	{
     		points =  50;
     	}
     	else if (t == ThrowType.MISS)
     	{
     		points =  0;
     	}
       return points;
    }
    
    /**
     * Returns a string representation of the current game state.
     */
    public String toString()
    {
      String result = "Player 0: " + getScore(0) +
                      "  Player 1: " + getScore(1) +
                      "  Current: Player " + getCurrentPlayer() +
                      "  Darts: " + getDartCount();
      return result;
    }
    
  }
    
    
