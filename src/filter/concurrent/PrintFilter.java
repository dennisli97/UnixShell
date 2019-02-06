package cs131.pa1.filter.concurrent;

public class PrintFilter extends ConcurrentFilter {
	public PrintFilter() {
		super();
		isInterrupted = false;
	}
	
	public void process() {
		while(!isDone() && !isInterrupted) {
			try{
				//continue process if the poison pill isnt in the inQ
				if (inQ.peek() != null && !inQ.peek().equals("xxxxthisisapoisonpill123123123hahahaha")) {
					String line = inQ.take();
					processLine(line);
				}
			}catch(InterruptedException e) {
				//raise interrupt flag internally because an external interrupt should not be able to terminate a thread
				isInterrupted = true;
			}
		}
	}
	
	public String processLine(String line) {
		System.out.println(line);
		return null;
	}

	@Override
	public void run() {// always last command, no poison pill
		process();
	}
}
