package cs131.pa1.filter.concurrent;

import java.util.LinkedList;
/**
 * 
 * @author ek
 * obj to associate the threadlist of each background process with the command the user entered
 */
public class BackgroundCommands {
	
	private String command;
	private Thread lastThread;
	
	public BackgroundCommands(Thread lastThread, String command) {
		this.command = command;
		this.lastThread = lastThread;
	}
	
	public Thread getLastThread() {
		return lastThread;
	}
	
	public String getCommandString() {
		return command;
	}
}
