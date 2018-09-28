package cs131.pa1.filter.sequential;

import java.io.File;
import java.util.LinkedList;

public class LS extends SequentialFilter{
	public LS() {
		output = new LinkedList<String>();
	}
	public void process() {
		String currentWorkingDirectory = SequentialREPL.currentWorkingDirectory;
		// array to hold all files and add each file anme to the output queue
		File[] files = new File(currentWorkingDirectory).listFiles();
		for (File file : files) {
		    if (file.isFile()) {
		        output.add(file.getName());
		    }
		}    
	}
	
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
