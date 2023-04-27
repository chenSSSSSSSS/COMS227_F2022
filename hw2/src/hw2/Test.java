package hw2;
import hw2.DartGame;
import static api.ThrowType.*;

public class Test {
	public static void main(String [] args ) {
		DartGame g = new DartGame(1,100,3);
		System.out.println(g.getScore(0)); //100
		System.out.println(g.getScore(1)); //100
		System.out.println(g.getCurrentPlayer()); //1
		System.out.println(g.getDartCount()); //3
		System.out.println(g.toString());  //Player 0: 100  Player 1: 100  Current: Player 1  Darts: 3
		System.out.println(g);
		System.out.println();
		
		
		g = new DartGame(1,100,3);
		g.throwDart(DOUBLE,10);    
	    System.out.println(g);
	    g.throwDart(DOUBLE,10);
	    System.out.println(g);
	    g.throwDart(DOUBLE,10);
	    System.out.println(g);
	    g.throwDart(DOUBLE,10);
	    System.out.println(g);
	    System.out.println();
	    
	 // checking the static method calcPoints
	    System.out.println(DartGame.calcPoints(MISS, 42));   // expected 0 (second arg ignored)
	    System.out.println(DartGame.calcPoints(SINGLE, 7)); // expected 7
	    System.out.println(DartGame.calcPoints(DOUBLE, 10)); // expected 20
	    System.out.println(DartGame.calcPoints(TRIPLE, 8)); // expected 24
	    System.out.println(DartGame.calcPoints(OUTER_BULLSEYE, 42)); // expected 25
	    System.out.println(DartGame.calcPoints(INNER_BULLSEYE, 42)); // expected 50
	    System.out.println();
	    
	  //
	    g = new DartGame(1,100,3);
		g.throwDart(DOUBLE,15);    
	    System.out.println(g);     //Player 0: 50  Player 1: 20  Current: Player 1  Darts: 2
	    g.throwDart(DOUBLE,12);
	    System.out.println(g);     //Player 0: 50  Player 1: 50  Current: Player 1  Darts: 3
	    System.out.println();
	    
	    g = new DartGame(1, 50, 3); 
	    g.throwDart(DOUBLE, 15); 
	    System.out.println(g); 
	    g.throwDart(SINGLE, 5); 
	    g.throwDart(SINGLE, 5); 
	    System.out.println(g);  // player 0's turn, player 1 score now 10 
	    g.throwDart(DOUBLE, 1); 
	    g.throwDart(DOUBLE, 1); 
	    g.throwDart(DOUBLE, 1); // player 0 having a bad day 
	    System.out.println(g);      
	    g.throwDart(SINGLE, 5); 
	    g.throwDart(SINGLE, 5); // player 1 gone bust 
	    System.out.println(g);  // player 0's turn, player 1 score back to 10 
	    System.out.println();
	    
	    // Expected:
	    //    Player 0: 50  Player 1: 20  Current: Player 1  Darts: 2
	    //    Player 0: 50  Player 1: 10  Current: Player 0  Darts: 3
	    //    Player 0: 44  Player 1: 10  Current: Player 1  Darts: 3
	    //    Player 0: 44  Player 1: 10  Current: Player 0  Darts: 3
	    
	 // doubling in
	    g = new DartGame(1,20,2);
	    g.throwDart(DOUBLE, 5);
	    g.throwDart(DOUBLE, 5);
	    
	    System.out.println(g);   // score still 100
//	    
	    
	    g = new DartGame(1);
	    
	    g.throwDart(DOUBLE,10);
	    g.throwDart(DOUBLE,10);
	    g.throwDart(DOUBLE,10);
	    System.out.println(g); 
	    System.out.println();
	    
	    g.throwDart(DOUBLE,10);
	    g.throwDart(DOUBLE,10);
	    g.throwDart(DOUBLE,10);
	    System.out.println(g); 
	    System.out.println();
	    
	    g.throwDart(DOUBLE,10);
	    g.throwDart(DOUBLE,10);
	    g.throwDart(DOUBLE,10);
	    System.out.println(g); 
	    System.out.println();
	    
	    g.throwDart(DOUBLE,10); 
	    g.throwDart(SINGLE,10);
	    g.throwDart(SINGLE,10);
	    System.out.println(g); 
	    System.out.println();
	    
	    
	    g.throwDart(SINGLE,10);
	    g.throwDart(SINGLE,10);
	    g.throwDart(SINGLE,10);
	    System.out.println(g); 
	    System.out.println();
	    
	    g.throwDart(SINGLE,10);
	    g.throwDart(SINGLE,10);
	    g.throwDart(SINGLE,10);
	    System.out.println(g); 
	    System.out.println();
	    
	    g.throwDart(SINGLE,10);
	    g.throwDart(SINGLE,10);
	    System.out.println(g); 
	    System.out.println();
	    
	    
	    //g.throwDart(DOUBLE, 7);
//	    System.out.println(g);   // ok, score now 86
	    // Expected:   
	    //    Player 0: 100  Player 1: 100  Current: Player 1  Darts: 2
	    //    Player 0: 100  Player 1: 86  Current: Player 1  Darts: 1
	    

	}
}
