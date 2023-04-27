package mini2;

import java.sql.Array;
import java.util.Arrays;

public class TestMini2 {
	public static void main (String [] arg) {

	int [] arr = {1, 12, 7, 2, 5, 8, 13};
	int[] p = UpUpAndArray.findPermutationToSort(arr);
	
	System.out.println(Arrays.toString(p));
	
}}
