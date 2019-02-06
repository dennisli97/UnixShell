package cs131.pa1.filter.concurrent;

public class PwdFilter extends ConcurrentFilter {
	public PwdFilter() {
		super();
	}
	
	public void process() {
		outQ.add(processLine(""));
	}
	
	public String processLine(String line) {
		return ConcurrentREPL.currentWorkingDirectory;
	}

	@Override
	public void run() {
		process();
		outQ.add("xxxxthisisapoisonpill123123123hahahaha");// tell the printer filter, the process is done
		
	}
}
