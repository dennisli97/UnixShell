package cs131.pa1.filter.concurrent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;

public class RedirectFilter extends ConcurrentFilter {
	private FileWriter fw;
	
	public RedirectFilter(String line) throws Exception {
		super();
		isInterrupted = false;
		String[] param = line.split(">");
		if(param.length > 1) {
			if(param[1].trim().equals("")) {
				System.out.printf(Message.REQUIRES_PARAMETER.toString(), line.trim());
				throw new Exception();
			}
			try {
				fw = new FileWriter(new File(ConcurrentREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + param[1].trim()));
			} catch (IOException e) {
				System.out.printf(Message.FILE_NOT_FOUND.toString(), line);	//shouldn't really happen but just in case
				throw new Exception();
			}
		} else {
			System.out.printf(Message.REQUIRES_INPUT.toString(), line);
			throw new Exception();
		}
	}
	
	public void process() {
		//while loop continues unless poison pill is found in inQ and that nothing tries to interrupt this thread
		while (!isInterrupted && !isDone()) {
			String line;
			try {
				//if poison pill is not found, we take whatever is in inQ
				if (inQ.peek() != null && !inQ.peek().equals("xxxxthisisapoisonpill123123123hahahaha")) {
					line = inQ.take();
					processLine(line);
				}
			} catch (InterruptedException e) { 
				//raise interrupt flag internally because an external interrupt should not be able to terminate a thread
				isInterrupted = true;
			}
		}
	}
	
	public String processLine(String line) {
		if (line.equals("xxxxthisisapoisonpill123123123hahahaha")) {
			return null;
		}
		try {
			fw.append(line + "\n");
		}catch (IOException e) {
			System.out.printf(Message.FILE_NOT_FOUND.toString(), line);
		}
		return null;
	}

	@Override
	public void run() {
		process();
		try {
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			outQ.put(processLine("xxxxthisisapoisonpill123123123hahahaha"));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}// tell the printer filter, the process is done
	}
}
