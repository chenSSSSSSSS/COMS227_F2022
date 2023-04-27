package mini1;
import java.util.Scanner;

public class LostInTheLoop 
{
	/**
	 * Private constructor disables instantiation.
	 */
	private LostInTheLoop()
	{
	
	}
	/**
	 * Returns the initial number of matching characters in
	 * two strings, allowing for a certain number of mismatches.
	 * In a memory game, the player is has to repeat a sequence of
	 * characters in a given "target" string.  The score is the number 
	 * of characters that are
	 * correctly repeated before the first mistake. However, it is
	 * also possible to allow a certain number of mistakes, as 
	 * represented by the third parameter, to be skipped over
	 * and ignored. This method returns the score, given the guessed
	 * string, the target string, and the maximum number of mistakes.
	 * For example, 
	 * <ul>
	 *   <li>for ("abcdefg", "abcxeyg", 0), the score is 3 
	 *   <li>for ("abcdefg", "abcxeyg", 1), the score is 4 
	 *   <li>for ("abcdefg", "abcxeyg", 2), the score is 5 
	 *   <li>for ("abcdefg", "abcxdef", 10), the score is 3 
	 * </ul>
	 * The method return -1 if the strings do not have the same length.
	 * @param s
	 *   guessed string
	 * @param t
	 *   target string
	 * @param maxMistakes
	 *   number of mismatches allowed to skip
	 * @return
	 *   player score
	 */
	public static int memoryGameChecker(String s, String t, int maxMistakes)
	{  
		int score = 0;
		int i;

		if(s.length()== t.length()) 
		{
			for (i = 0; i<=s.length()-1 && maxMistakes >= 0;i +=1) 
			{
				if(s.charAt(i)==t.charAt(i))
				{
					score +=1;
				}
				else
				{
					maxMistakes -= 1;
				}
			}
			return score;
		}
		return -1;	
    }

	/**
	 * Returns a string constructed from the given string by removing
	 * all runs of the same character.  For example,
	 * <ul>
	 *   <li>from "abbccccdeeef", return "abcdef"
	 *   <li>from "aaabcccc", return "abc"
	 * </ul>
	 * If the given string is empty, returns an empty string.
	 * * @param s
	 *   given string
	 * @return
	 *   new string obtained by eliminating runs of the same character
	 */
	public static String compressRuns(String s)
	{
		if (s.length() > 0) 
		{
			String result = "" + s.charAt(0);
			for (int i = 1; i <= s.length()-1; i+=1)
			{
				if (result.charAt(result.length()-1)!= s.charAt(i))
				{
					result += s.charAt(i);
				}
			}
			return result;
		}
		return "";
	}

	/**
	   * Determines how many terms of the sum of reciprocal triangle
	   * numbers are needed to get within a specified distance of 2.0.
	   * The series 
	   * <pre>
	   * 1 + 1/3 + 1/6 + 1/10 + 1/15 + ...
	   * </pre>
	   * approaches arbitrarily close to 2.0 as more terms are added.
	   * This method determines how many terms are needed
	   * to obtain a sum that is within a given margin of error
	   * of 2.0. The terms of the series are the reciprocals of the "triangle" 
	   * numbers 1, 3, 6, 10, 15, 21 ... in which the nth term is 
	   * the previous term plus n, e.g.,
	   * <pre>
	   *   3 =   1 + 2
	   *   6 =   3 + 3
	   *   10 =  6 + 4
	   *   15 = 10 + 5
	   *   21 = 15 + 6
	   * </pre>
	   * ... and so on.  Example: countTriangleNumberSum(0.4) returns 4, since
	   * <pre>
	   * 1 + 1/3 + 1/6 = 1.5
	   * 1 + 1/3 + 1/6 + 1/10 = 1.6
	   * </pre>
	   * so four terms is within 0.4 of 2.0, but three terms is not 
	   * close enough.
	   * @param err
	   *   given margin of error
	   * @return
	   *   number of terms
	   */
	  public static int countTriangleNumberSum(double err)
	  {

		int count  = 0;
		double sum = 0;
		
		while (sum < 2.0 - err)
		{
			count += 1;
			double term = 2.0 / ((count + 1) * count);
			
			sum += term;		
		}
	    return count;
	  }
	  
	  /**
	   * Parses a string containing names and quiz scores, and
	   * returns the name and score for the highest score.
	   * For example, given the string "Steve 42 June 137 Guang 75",
	   * the method returns the string "June 137" (where there is
	   * exactly one space between the name and number).  You can
	   * assume that the names do not contain any whitespace, and
	   * that the given string is either empty or valid. If the
	   * argument is an empty string, the method returns an empty
	   * string.  If two or more names are associated with the 
	   *  * same highest score, the first one is returned.
	   * @param s
	   *   given string of name and score pairs
	   * @return
	   *   string with name and score for highest score
	   */
	  public static String findHighestScore(String s)
	  {
		  Scanner sc = new Scanner (s);
		  
		  String name  = "";
		  String winner= ""; 
		  int score;
		  int max;

		  //check if string is empty
		  if(s.length() > 0)
		  {
		  // save the score and name for the first person
			  winner = sc.next();
			  max = sc.nextInt();
			  
			  while(sc.hasNext())  // check if there is next token in String 
			  {
				  name = sc.next();   
				  score = sc.nextInt();
				  				   
					  if(score > max )  //compare this score with max. if it's true, save the score and the name
					  {
						  max = score;
						  winner = name;
					  }				 
			  }

			  String abv = winner + " " + max;
			  return abv;
		  }
		  return "";
	  }

	  /**
	   * Given a size n >= 1, prints a picture of a tree to standard output,
	   * in the pattern shown below for n = 5.
	   * <pre>    
	        /\
	       //\\
	      ///\\\
	     ////\\\\
	    /////\\\\\
	        || 
	   *     
	   * </pre>
	   * Note that at the widest part of the tree the line should have no leading
	   * spaces.  WARNING: in Java code you can't directly use the backslash
	   * character as a literal value; the way you type a literal backslash
	   * character is with TWO backslashes: '\\'.
	   * 
	   * @param n
	   *   height of the tree, not including the trunk
	   */
	  public static void printTree(int n)
	  { 
		  //print tree leafs
		  for (int i = 1; i <= n; i++)        //print each row
		  {
			  for (int j = n - i; j > 0; j--) // print space
			  {
				  System.out.print(" ");
			  }
			  for (int j = 1; j <= i ; j++)   // print "/"
			  {
				  System.out.print("/");
			  }
			  for (int j = 1; j<=i;j++)       // print "\"
			  {
				  System.out.print("\\");
			  }
			  System.out.println();           
		  }

		  // print trunk of the tree
		  for (int i = 1; i < n; i ++)         
		  {
			  System.out.print(" ");
		  }
		  System.out.print("||");
	  }

	  /**
	   * Checks a guess for a secret word and returns a feedback string.
	   * This algorithm is inspired by, but not the same as, that used
	   * by the game Wordle.
	   * The returned string is the same length as the guess, and the
	   * character at index i is filled in as follows, where  
	   * g_i, s_i, and r_i denote the character at position i in
	   * the guess, the secret word, and the result string, respectively.
	   * 
	   * <ul>
	   *   <li>if g_i matches s_i, then r_i is '*'
	   *   <li>if g_i does not occur in the secret word at all, 
	   *   then r_i is '-'
	   *   <li>if g_i does not match s_i, but the secret word does
	   *   have an unmatched occurrence of g_i, then r_i is '?'.
	   *   (More precisely, an "unmatched occurrence" means that there is some 
	   *   index j such that g_i is equal to s_j but g_j is not equal
	   *   to s_j.)
	   * </ul>
	   * 
	   * For example,
	   *  * <pre>
	   *   Guess:  "guess"
	   *   Secret: "geese"
	   *   Result: "*-**-"
	   *   
	   *   Guess:  "bobbly"
	   *   Secret: "blobby"
	   *   Result: "*??*?*"
	   *   
	   *   Guess:  "aabbbb"
	   *   Secret: "abbcde"
	   *   Result: "*-*???"
	   * </pre>
	   * (Aside to Wordle fans: note that the latter case differs from 
	   * the algorithm actually
	   * used by Wordle, which would return "*-*?--", because it would 
	   * count the number of unmatched b's in the secret word and note 
	   * that there is only one, so only the first incorrect b in the guess
	   * is labeled with a question mark.  In this method we are ignoring
	   * that subtlety.)
	   * <p>
	   * The method returns null if the two given strings are not the same length.
	   * 
	   * 
	   * @param guess
	   *   the guessed word
	   * @param secret
	   *   the secret word
	   * @return
	   *   result string with feedback for the guess
	   */
	  public static String wordGameChecker(String guess, String secret)
	  {
		  String s = "";
		  String haha = "";
		  
		  if(guess.length()== secret.length())
		  {
			  //creat a string that have chars does not match
			  for (int j = 0; j < secret.length();j++)
			  {
				  if(guess.charAt(j)!= secret.charAt(j))
					  haha += secret.charAt(j);	  
			  }
			  
			  
			  for (int i = 0; i < secret.length();i++)
			  {
				  if(guess.charAt(i)== secret.charAt(i))  // if the char at index in two string are match, print "*"
				  {
					  s += "*";
				  }
			     else
			     {  
			    	 if( haha.indexOf(guess.charAt(i)) >= 0)   // if there is a unmatch char but wrong index 
			    	     s += "?";
			    	 else 
			    	 {                 
			    		 s +="-";         // do not appear in the guess unmach string at all
			    	 }
			     }
			  } 

             return s;
		  } 
			 return null;
		  
	  }
	  
	  /**
	   * Given a string, returns a new string obtained by successively removing 
	   * all adjacent matching characters.  For example, given "abbc", the method
	   * returns "ac", and given "abcddcbeffg", the method returns "aeg". 
	   * Note that multiple iterations may be required for the latter; that
	   * is, after removing the matching "dd" and "ff", the resulting
	   * string is "abccbeg", which now has a matching pair "cc"; after removing
	   * "cc", the string is "abbeg, which now has a matching pair "bb". 
	   * You can assume that the given string contains alphabetic characters only.
	   * @param s
	   *   given string
	   * @return
	   *   string obtained by removing matching pairs of adjacent characters
	   */
	  public static String cancelAdjacentPairs(String s)
	  {
		  boolean isPair = true;
		  String  ss     = "";
		  String dup = s;
		  
		  while(isPair)
		  {
			  if (s.isEmpty())
			  {
				  return s;
			  }
			  isPair = false;
			  
			  for (int i = 0; i < s.length()-1; i++)
			  {

				  if(s.charAt(i) != s.charAt(i+1))
				  {
					  ss = ss + s.charAt(i);
					  
				  } 
				  else 
				  {
					  isPair = true;
					  i++;
				  }  
			  }

			  if(s.length()==1 )
			  {
				  return s;
			  }
			  
			  if (s.charAt(s.length()-1) != s.charAt(s.length()-2))
			  {
				  ss = ss + s.charAt(s.length()-1);
			  }
		      
			  if (ss.isEmpty() && s.length() % 2 != 0) 
			  {
				  ss = ss + s.charAt(s.length()-1);
			  }
			  
			  if (ss.length()==s.length())
			  {
				  return ss;
			  }
			  
			  s = ss;
			  ss = "";
			  
		  }
	    return ss;
	  }
}





