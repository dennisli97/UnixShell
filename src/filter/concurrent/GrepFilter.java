package filter.concurrent;

import filter.Message;

public class GrepFilter extends ConcurrentFilter {
	private String toFind;
	
	public GrepFilter(String line) throws Exception {
		super();
		isInterrupted = false;
		String[] param = line.split(" ");
		if(param.length > 1) {
			toFind = param[1];
		} else {
			System.out.printf(Message.REQUIRES_PARAMETER.toString(), line);
			throw new Exception();
		}
	}
	
	public String processLine(String line) {
		if(line != null && line.contains(toFind)) {
			return line;
		} else {
			return null;
		}
	}
	
	public void run() {
		process();
		try {
			//insert pill to signify that this process() has finished running
			outQ.put("xxxxthisisapoisonpill123123123hahahaha");// insert poison pill
		} catch (InterruptedException e) {
			e.printStackTrace();
			//raise interrupt flag internally because an external interrupt should not be able to terminate a thread
			isInterrupted = true;
		}
	}
}
