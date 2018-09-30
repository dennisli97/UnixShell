package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class PWD extends SequentialFilter{
	
	public void process() {
		// add the current working directory to the output queue
		
		if(input == null) {
			output = new LinkedList<String>();
			output.add(SequentialREPL.currentWorkingDirectory);
		} else {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("pwd"));
		}
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
