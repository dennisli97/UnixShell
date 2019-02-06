package cs131.pa1.filter.concurrent;

import java.util.HashSet;
//
public class UniqFilter extends ConcurrentFilter{
	private HashSet<String> existingStringSet;
	//This set will record what strings are existing
	
	public UniqFilter () throws Exception {
		existingStringSet = new HashSet<String> ();
	}

	
	public String processLine(String line) {
		if(existingStringSet.contains(line)) {
			return null;
		}else {
			existingStringSet.add(line);
			return line;
		}
	}
	
	public void run() {
		process();
		try {
			outQ.put("xxxxthisisapoisonpill123123123hahahaha");// insert poison pill after process is done
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
