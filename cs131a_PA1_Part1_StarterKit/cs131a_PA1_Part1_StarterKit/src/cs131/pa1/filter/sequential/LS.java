package cs131.pa1.filter.sequential;

import java.io.File;
import java.util.LinkedList;

public class LS extends SequentialFilter{

	public void process() {
		output = new LinkedList<String>();
		String currentWorkingDirectory = SequentialREPL.currentWorkingDirectory;
		// array to hold all files and add each file anme to the output queue
		File curr = new File(currentWorkingDirectory);
		String[] allNames = curr.list();
		for (String name : allNames) {
		    output.add(name);
		}    
	}
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
