package cs131.pa1.filter.sequential;

import java.util.Scanner;

public class GREP extends SequentialFilter {
	
	String searchString;// feild to hold the searching string
	// constructor to initialize  searchString
	public GREP(String searchString) {
		this.searchString = searchString;
	}
	
	
	
	
	@Override
	protected String processLine(String line) {
		Scanner lineScan = new Scanner(line);// scanner to scan the line
		String words;
		while(lineScan.hasNext()) {
			words = lineScan.next();
			if(words.equals(searchString)) {
				// if any word in the line match up with the searchString, return line
				return line;
			}
		}
		return null;// else, return null
	}

}
