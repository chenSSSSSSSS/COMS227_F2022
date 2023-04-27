package mini2;

/**
 * Some loop practice problems involving arrays.
 * @author Chen Sang
 */

public class UpUpAndArray {
	/**
	 * Private constructor disables instantiation.
	 */
	private UpUpAndArray()
	{

	}

	// The isPermutation code below is given.  It will be useful in 
	// implementing the rearrange() method.
	/**
	 * Determines whether the given array is a permutation.
	 * A permutation is defined to be an int array that
	 * contains each number from 0 through n - 1 exactly
	 * once, where n is the length of the array.  In other
	 * words, each value is between 0 and n - 1, inclusive,
	 * and there are no duplicates.
	 * @param arr
	 *   given array
	 * @return
	 *   true if the given array is a permutation, false otherwise
	 */
	public static boolean isPermutation(int[] arr)
	{
		// set found[x] to true if we find x in arr
		boolean[] found = new boolean[arr.length];
		for (int i = 0; i< arr.length; ++i)
		{
			int x = arr[i];
			if (x < 0 || x >= arr.length || found[x])
			{
				// x is out of range, or is a duplicate
				return false;
			}
			found[x] = true;
		}
		return true;
	}

	/**
	 * Constructs a new array consisting of elements of arr between
	 * the given start and end indices, inclusive.  If start is 
	 * less than zero it is clamped up to zero, and if end is greater
	 * than arr.length - 1, it is clamped down to arr.length - 1. If
	 * end is less than start, an empty array is returned.
	 * The given array is not modified.
	 * <p>
	 * For example, if arr = [10, 20, 30, 40, 50, 60, 70], start is 2, and
	 * end is 5, the method returns the array [30, 40, 50, 60].
	 * @param arr
	 *   given array
	 * @param start
	 *   starting index to include in the subarray
	 * @param end
	 *   ending index to include in the subarray
	 * @return
	 *   array containing elements of arr between start and end, inclusive
	 */
	public static int[] makeSubArray(int[] arr, int start, int end)
	{
		if (end < start )
		{
			return arr;
		}
		if (start < 0)
		{
			start = 0;
		}
		if(end > arr.length -1)
		{
			end = arr.length -1;
		}
		
		int i;
		int j = start;
		int length = end - start +1;
		int[] temp = new int [length];
		
		
		for (i = 0; i <= length -1 && j <= end; ++i)
		{
			temp[i] = arr[j];
			j+=1;
		}
		return temp;
	}

	/**
	 * Exchanges adjacent pairs of elements in the given array.
	 * If the given array is of odd length, the last element
	 * is not moved.
	 * @param arr
	 *   given array to be modified
	 */
	public static void swapPairs(int[] arr)
	{
		int i; 
		int temp;
		
		for (i = 0; i < arr.length -1; ++i)
		{
            if (i % 2 == 0)
            {	
				temp     = arr[i];
				arr[i]   = arr[i+1];
				arr[i+1] = temp;
			}
		}
	}

	/**
	 * Exchanges two columns of a 2D array.  For example, if
	 * arr is:
	 * <pre>
	 * 1 2 3 4 5
	 * 6 7 8 9 0
	 * </pre>
	 * then after the call swapColumns(arr, 1, 4), arr contains:
	 * <pre>
	 * 1 5 3 4 2
	 * 6 0 8 9 7
	 * </pre>
	 * You can assume that the given array is nonempty and 
	 * rectangular (i.e. all rows are the same length).
	 * If i or j is out of range, the method does nothing.
	 * @param arr
	 *   given 2D array
	 * @param i
	 *   index of column to swap
	 * @param j
	 *   index of column to swap
	 */
	public static void swapColumns(int[][] arr, int i, int j)
	{
		int temp;
		
		for (int k = 0; k < arr.length; k+=1)
		{
		   if (j < arr[k].length)
		   {
			temp = arr[k][i];
			arr[k][i] = arr[k][j];
			arr[k][j] = temp;
		   }
		}
	}

	/**
	 * Rearranges the given array according to the given permutation,
	 * where perm[i] is the new index of element arr[i].
	 * For example, if arr is [10, 20, 30, 40] and perm is
	 * [2, 0, 3, 1], then after calling this method, arr contains
	 * [20, 40, 10, 30].  If perm is not the same length as arr,
	 * or if perm is not a permutation, the method does nothing.
	 * @param arr
	 *   given array
	 * @param perm
	 *   given array to be used as a permutation, if possible
	 */
	public static void rearrange(int[] arr, int[] perm)
	{   		
		if (perm.length == arr.length && isPermutation(perm))
		{
			int [] temp = new int [arr.length];


			for (int i = 0; i < temp.length; i += 1)
			{
				int index = perm[i];
				temp[index] = arr[i];
			}

			for (int j = 0; j < arr.length; j += 1)
			{
				arr[j] = temp[j];
			}
		}
	}

	/**
	 * Finds the longest common suffix among the strings
	 * in an array.  For example, if arr is
	 * ["gladly", "badly", "boodly", "sadly"], the method
	 * returns the string "dly".  If arr is ["foo", "food"],
	 * the method returns an empty string.
	 * @param arr
	 *   array of strings
	 * @return
	 *   longest common suffix
	 */
	public static String longestCommonSuffix(String[] arr)
	{
		if (arr[0] == "") {
			return "";
		}
		
		String same = "";
		int minLength = arr[0].length();
		
		for(int i = 1; i < arr.length; i += 1)
		{
			minLength = Math.min(minLength, arr[i].length());
		}
		
		int k = 1;
		boolean flag = true;
		while(k <= minLength && flag)
		{
			char sameLetter = arr[0].charAt(arr[0].length()-k); 
			
			for (int i = 1; i < arr.length; i += 1)
			{
				if (sameLetter != arr[i].charAt(arr[i].length()-k) && same.equals(""))
				{
					return same;
				}
				else if (sameLetter != arr[i].charAt(arr[i].length()-k) && same.equals("")== false)
				{
					flag = false;
				}
			}
			
			if (flag)
			{	
				same += sameLetter;
				k +=1;
			}
		}
		
		String yes = "";
		for (int s = same.length()-1; s >= 0; s -= 1)
		{
			yes += same.charAt(s);
		}
		
		return yes;
	}

	/**
	 * Inserts one array into another at the given position.  Existing
	 * elements are shifted to the right.  For example, if 
	 * arr is [10, 20, 30, 40, 50, 60], pos is 1, and toInsert is the 
	 * array [101, 102, 103], then after calling this method,
	 * arr contains [10, 101, 102, 103, 20, 30].  If pos plus the
	 * length of toInsert exceeds the length of arr, the extra elements
	 * of toInsert are ignored. If pos is out of range, the method
	 * does nothing.
	 * @param arr
	 *   given array to be modified
	 * @param pos
	 *   starting insert position
	 * @param toInsert
	 *   elements to be inserted
	 */
	public static void insertArray(int[] arr, int pos, int[] toInsert)
	{
		
		if(pos>=0 && pos<arr.length) 
		{
			int i = 0;
			int j = 0;

			for (j = toInsert.length -1; j >=0; j -= 1) 
			{
				for (i = arr.length -1; i > pos  ; i -= 1)
				{
					arr[i] = arr[i-1];	
				}
				arr[pos] = toInsert[j];
			}
		}
	}


	/**
	 * Finds a permutation with the property that if arr 
	 * is rearranged according to that permutation, it will
	 * end up in ascending order.  That is, for an array arr,
	 * after executing the code
	 * <pre>
	 *    int[] p = findPermutationToSort(arr);
	 *    rearrange(arr, p);
	 * </pre>
	 * the array arr is sorted.  For example, if 
	 * arr is [12, 7, 2, 5, 8], the method returns 
	 * the array [4, 2, 0, 1, 3].  You can assume that
	 * the given array has no duplicates.
	 * @param arr
	 *   given array
	 * @return
	 *   permutation that will rearrange arr to be sorted in
	 *   ascending order
	 */
	public static int[] findPermutationToSort(int[] arr)
	{
		// TODO
      	int [] result   = new int[arr.length];
      	//int i = 0;
      	//int j =0;
      	
      	//int minNum =0;

      	int x = 0;

      	for(int i = 0; i < arr.length; i++) 
      	{
      		x = 0;
      		for (int j = 0; j < arr.length; j++) 
      		{

      			if ( i == j) 
      			{
                     // do nothing
      			}
      			else if (arr[i] > arr[j]) 
      			{
      				x ++;
      			}

      		}
      		result [i] = x;
      	}
      	
      
      	
//
//      	//for (i = 0; i < result.length; i += 1)
//      	{
//      	//	if (temp[minNum] == true)
//      	//	{
//      		//	minNum += 1;
//      	//	}
//      	//	minNum = 0;
//      		
//      		
//      	//	for (j = 1; j < arr.length; j += 1)
//      		//{
//      		//	if (arr[j] <= arr[minNum]  && temp[j] == false)
//      		//	{
//      				
//      				
//      				minNum= j;
//      			}
//   		
//      		}
//      		
//      		
//      		result[minNum] = i;
//      		minNum +=1;
//      		temp[minNum]   = true;	
//      	}	
		return result;
	}

	/**
	 * NOTE: THIS IS AN EXTRA CREDIT PROBLEM.
	 * 
	 * Finds the longest arithmetic run in an int array.
	 * An <em>arithmetic run</em> is a sequence of consecutive
	 * elements with the same difference between them. For example, 
	 * in the array [10, 20, 30, 25, 20, 15, 8, 9], all of 
	 * the following are arithmetic runs:
	 * <ul>
	 *   <li>[10, 20, 30] (difference 10)
	 *   <li>[30, 25, 20] (difference -5)
	 *   <li>[25, 20, 15] (difference -5)
	 *   <li>[30, 25, 20, 15] (difference -5)
	 * </ul>
	 * Note that any adjacent pair of elements is an arithmetic run
	 * of length 2. In this case the method would return [30, 25, 20, 15].
	 * If there are multiple arithmetic runs of the same length, the 
	 * method returns the one with the smallest starting index. For example,
	 * for array [12, 6, 2, 5, 9], the method returns [12, 6].
	 * For an array of length 0, 1, or 2, the method just returns 
	 * the array itself.
	 * 
	 * @param arr
	 *   given array
	 * @return
	 *   new array containing the longest arithmetic run in the given array
	 */
	public static int[] findLongestArithmeticRun(int[] arr)
	{
		// TODO
		return null;
	}

}
