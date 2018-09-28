package cs131.pa1.filter.sequential;

import java.util.LinkedList;
import java.util.Queue;

public class UNIQ extends SequentialFilter{

	private Queue<String> uniqOutput;
	public UNIQ() {
		uniqOutput = new LinkedList<String>();// new list to hold the uniq lines
	}
	public void process() {
		String toCheck;
		while(!isDone()) {// while there is still input
			toCheck= input.poll();
			if(!uniqOutput.contains(toCheck)) {// check if input is already in the uniq list, if not, add the line in
				uniqOutput.add(toCheck);
			}
		}
		output = uniqOutput;
	}
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
