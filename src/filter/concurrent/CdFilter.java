package filter.concurrent;

import java.io.File;

import filter.Filter;
import filter.Message;

public class CdFilter extends ConcurrentFilter {
	private String dirToSet;
	
	public CdFilter(String line) throws Exception {
		super();
		isInterrupted = false;
		dirToSet = ConcurrentREPL.currentWorkingDirectory;
		String[] args = line.trim().split(" ");
		if(args.length == 1) {
			System.out.printf(Message.REQUIRES_PARAMETER.toString(), line.trim());
			throw new Exception();
		}
		if(args[1].equals("..")) {
			String current = ConcurrentREPL.currentWorkingDirectory;
			current = current.substring(0, current.lastIndexOf(Filter.FILE_SEPARATOR));
			dirToSet = current;
		} else if (!args[1].equals(".")) {
			String current = ConcurrentREPL.currentWorkingDirectory;
			current = current + Filter.FILE_SEPARATOR + args[1];
			File test = new File(current);
			if (test.isDirectory()) {
				dirToSet = current;
			} else {
				System.out.printf(Message.DIRECTORY_NOT_FOUND.toString(), line);
				throw new IllegalArgumentException();
			}
		}
	}
	
	public void process() {
		processLine("");
	}
	
	public String processLine(String line) {
		ConcurrentREPL.currentWorkingDirectory = dirToSet;
		return null;
	}

	@Override
	public void run() {
		process();
		try {
			//insert poison pill
			outQ.put("xxxxthisisapoisonpill123123123hahahaha");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//raise interrupt flag internally because an external interrupt should not be able to terminate a thread
			isInterrupted = true;
		}
	}
}
