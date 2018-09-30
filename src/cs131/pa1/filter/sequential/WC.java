package cs131.pa1.filter.sequential;

import java.util.LinkedList;
import java.util.Scanner;

public class WC extends SequentialFilter{
	
	public void process() {
		int lineCount =0;
		int wordCount =0;
		int charCount =0;// variable to count the lines, words and characters

		output = new LinkedList<String>();
		
		while(!isDone()) {// while input is not empty
			String line = input.poll();
			lineCount++;
			Scanner lineScan = new Scanner(line);// scan in the line
			String word;
			while(lineScan.hasNext()) {
				word = lineScan.next();
				wordCount++;
				charCount += word.length();// add number of char in each word
			}
		}
		String addOut = lineCount +" "+ wordCount +" " + charCount;// contruct the output 
		output.add(addOut);
		
	}
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
