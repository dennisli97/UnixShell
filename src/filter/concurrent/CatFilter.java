package filter.concurrent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import org.hamcrest.core.IsNot;

import filter.Message;

public class CatFilter extends ConcurrentFilter{
	private Scanner reader;
	
	
	public CatFilter(String line) throws Exception {
		super();
		isInterrupted = false;
		//parsing the cat options
		String[] args = line.split(" ");
		String filename;
		//obviously incorrect number of parameters
		if(args.length == 1) {
			System.out.printf(Message.REQUIRES_PARAMETER.toString(), line);
			throw new Exception();
		} else {
			try {
				filename = args[1];
			} catch (Exception e) {
				System.out.printf(Message.REQUIRES_PARAMETER.toString(), line);
				throw new Exception();
			}
		}
		try {
			reader = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.printf(Message.FILE_NOT_FOUND.toString(), line);
			//add posion pil to end this process
			outQ.put("xxxxthisisapoisonpill123123123hahahaha");
			throw new FileNotFoundException();
		}
	}

	public void process() {
		while(reader.hasNext() && !isInterrupted) {
			String processedLine = processLine("");
			if(processedLine == null) {
				break;
			}
			try{
				outQ.put(processedLine);// method to wait till there is spot for the proceessedLInd in the q, and put in 
			}catch(InterruptedException e){
				//raise interrupt flag internally because an external interrupt should not be able to terminate a thread
				isInterrupted = true;
			}
		}
		reader.close();
	}
	
	public void run() {
		process();
		outQ.add("xxxxthisisapoisonpill123123123hahahaha");// insert poison pill after process is done
	}

	public String processLine(String line) {
		if(reader.hasNextLine()) {
			return reader.nextLine();
		} else {
			return null;
		}
	}
}
