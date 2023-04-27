package mini1;

public class MiniTest {
	public static void main(String [] arg)
	{
		System.out.println(LostInTheLoop.countTriangleNumberSum(0.4));
//		PROBLEM: 
//			countTriangleNumberSum(0.1) should return 20. 
//			expected:<20> but was:<19>
//
//			PROBLEM: 
//			countTriangleNumberSum(0.15) should return 13. 
//			expected:<13> but was:<172019516>
//
//			PROBLEM: 
//			countTriangleNumberSum(0.01) should return 200. 
//			expected:<200> but was:<199>
//		System.out.println(LostInTheLoop.countTriangleNumberSum(0.1));
//		System.out.println(LostInTheLoop.countTriangleNumberSum(0.15));
//		System.out.println(LostInTheLoop.countTriangleNumberSum(0.01));
	    
		//String s = "bobbly", "blobby";
		//System.out.println(LostInTheLoop.findHighestScore(s));

		//		findHighestScore("Steve -137 June -42 Guang -137 Matt -138") should return "June -42". 
//				expected:<[June -42]> but was:<[ 0]>
		//"Steve 137 June 137 Guang 137 Matt 137"
		//System.out.println(LostInTheLoop.findHighestScore(s));
		
		//"aaaaaaaaa"
		//abbaxcddceffex
		//System.out.println(LostInTheLoop.cancelAdjacentPairs(s));
		
		System.out.println(LostInTheLoop.wordGameChecker("bobbly", "blobby"));
	}

}