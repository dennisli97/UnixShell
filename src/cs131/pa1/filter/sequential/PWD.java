package cs131.pa1.filter.sequential;

import java.util.LinkedList;

public class PWD extends SequentialFilter{
	
	public PWD() {
		output = new LinkedList<String>();
	}
	public void process() {
		// add the current working directory to the output queue
		output.add(SequentialREPL.currentWorkingDirectory);
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
