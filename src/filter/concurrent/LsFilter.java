package cs131.pa1.filter.concurrent;
import java.io.File;

public class LsFilter extends ConcurrentFilter{
	int counter;
	File folder;
	File[] flist;
	
	public LsFilter() {
		super();
		isInterrupted = false;
		counter = 0;
		folder = new File(ConcurrentREPL.currentWorkingDirectory);
		flist = folder.listFiles();
	}
	
	@Override
	public void process() {
		while(counter < flist.length && !isInterrupted) {
			try {
				outQ.put(processLine(""));
			} catch (InterruptedException e) {
				//raise interrupt flag internally because an external interrupt should not be able to terminate a thread
				isInterrupted = true;
			}
		}
	}
	
	@Override
	public String processLine(String line) {
		return flist[counter++].getName();
	}

	@Override
	public void run() {
		process();
		try {
			outQ.put("xxxxthisisapoisonpill123123123hahahaha");// insert poison pill after process is done
		} catch (InterruptedException e) {
			//raise interrupt flag internally because an external interrupt should not be able to terminate a thread
			isInterrupted = true;
		}
		
	}
}
