package hw3;

import java.util.ArrayList;

import api.GridCell;
import api.Line;
import api.Location;
import api.StringUtil;

public class Test {
	public static void main(String[]args){
		
		
		 String[] test = {
			      "GRrR",
			      "ggGY",
			      "Yyyy"
			    };
		
		 GridCell[][] grid = StringUtil.createGridFromStringArray(test);
		 
		 ArrayList<Line> lines = new ArrayList<>();

		
		 System.out.println(StringUtil.originalGridToString(grid));
		 System.out.println();
		 System.out.println(StringUtil.allLinesToString(lines));
		 System.out.println();
		 
		
		 ArrayList <Line> lineee = Util.createLinesFromGrid(grid);
		  
		 for(Line l:  lineee) {
			 System.out.println(l);
		 }
		 
		    
		
		 
	}
	

}