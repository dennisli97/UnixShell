package filter.concurrent;

import filter.Message;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class ConcurrentREPL {

	static String currentWorkingDirectory;
	
	public static void main(String[] args){
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner s = new Scanner(System.in);
		System.out.print(Message.WELCOME);
		String command;
		LinkedList<BackgroundCommands> bgCmds = new LinkedList<BackgroundCommands>();
		
		int count = 1;
		
		while (true) {
			//obtaining the command from the user
			System.out.print(Message.NEWCOMMAND);
			command = s.nextLine();
			if(command.equals("exit")) {
				break;
			//if user requests for background processes that are running
			} else if (command.trim().equals("repl_jobs")) {
				LinkedHashSet<String> allbgCmds = new LinkedHashSet<String>();
				for (BackgroundCommands bgCmd : bgCmds) {
					if (bgCmd.getLastThread().isAlive()) {
						allbgCmds.add(bgCmd.getCommandString());
					}
				}
				for (String bgCmd : allbgCmds) {
					System.out.println("\t" + bgCmd);
				}
			//check if user wants to kill a background process
			} else if (command.trim().split(" ")[0].equals("kill")) {
				cleanupBg(bgCmds);
				String[] cmds = command.trim().split(" ");
				//check to assert that kill has a param
				if (cmds.length == 1) {
					System.out.print(Message.REQUIRES_PARAMETER.with_parameter(command));
				//check to assert that kill only has 1 param
				} else if (cmds.length > 2 || !isInteger(cmds[1])) {
					System.out.print(Message.INVALID_PARAMETER.with_parameter(command));	
				} else {
					int index = Integer.parseInt(cmds[1]);
					//check that the process the user wants to kill is actually within the list
					if (index > bgCmds.size()) {
						System.out.print(Message.INVALID_PARAMETER.with_parameter(command));
					} else {
						BackgroundCommands curr = bgCmds.get(index-1);
						curr.getLastThread().interrupt();
						bgCmds.remove(curr);
					}	
				}
			} else if(!command.trim().equals("")) {
				//first check if filters need to run in background or not
				boolean backG = false;
				if (command.charAt(command.length() - 1) == '&') {
					backG = true;
				}
				//building the filters list from the command
				ConcurrentFilter filterlist = ConcurrentCommandBuilder.createFiltersFromCommand(command);
				if (filterlist != null) {
					//list of all threads of filters
					LinkedList<Thread> allThreads = new LinkedList<Thread>();
					//loop through the filterlist to create a thread for each filter
					while (filterlist != null) {
						Runnable curr = filterlist;
						Thread currThread = new Thread(curr, command);
						filterlist.setCurrThread(currThread);
						//add each thread into a list of threads for this current command
						allThreads.add(currThread);
						//if needs to run in foreground, last thread needs join() not start()
						if (filterlist.getNext() == null && !backG) {
							try {
								currThread.start();
								currThread.join();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//if needs to run in bg, add list of threads to List of bgcmds
						} else if (filterlist.getNext() == null && backG) {
							String fullJob = count+". "+ command;
							bgCmds.add(new BackgroundCommands(currThread, fullJob));
							count++;
							currThread.start();
						} else {
							currThread.start();
						}
						filterlist = (ConcurrentFilter) filterlist.getNext();
					}
				}
			}
		}
		s.close();
		System.out.print(Message.GOODBYE);
	}
	/**
	 * 
	 * @param background - list of threads
	 * @return true if any are alive
	 */
	public static boolean anyAlive(LinkedList<Thread> background) {
		boolean isRunning = false;
		if (background != null) {
			ListIterator<Thread> itr = background.listIterator();
			//loop thru each thread in this list to check if any one is alive
			while (itr.hasNext()) {
				if (itr.next().isAlive()) {
					isRunning = true;
				}
			}
		}
		return isRunning;
	}
	/**
	 * check if string contains only ints
	 * @param s - param for kill command
	 * @return - true if string s only contains chars that are ints
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	/**
	 * method to remove all dead commands from the list of background cmds	
	 * @param bgCmds - list of all processes running in background
	 */
	public static void cleanupBg(LinkedList<BackgroundCommands> bgCmds) {
		for (BackgroundCommands bgCmd : bgCmds) {
			//if last thread of that command is not alive, remove it from list
			if (bgCmd != null && !bgCmd.getLastThread().isAlive()) {
				bgCmds.remove(bgCmd);
			}
		}
	}
}