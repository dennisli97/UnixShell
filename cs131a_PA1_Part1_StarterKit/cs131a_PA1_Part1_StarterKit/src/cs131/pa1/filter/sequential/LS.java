package cs131.pa1.filter.sequential;

import java.io.File;
import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class LS extends SequentialFilter{
	public LS() {
		output = new LinkedList<String>();
	}
	
	public void process() {
		if(input != null) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("ls"));
			return;
		}
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
