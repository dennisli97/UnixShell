package cs131.pa1.filter.sequential;

import java.util.LinkedList;
import java.util.Queue;

public class UNIQ extends SequentialFilter{

	private Queue<String> uniqOutput;
	public UNIQ() {
		uniqOutput = new LinkedList<String>();
	}
	public void process() {
		String toCheck;
		while(!isDone()) {
			toCheck= input.poll();
			if(!uniqOutput.contains(toCheck)) {
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
