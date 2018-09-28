package cs131.pa1.filter.sequential;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;


public class SequentialREPL {

	static String currentWorkingDirectory;
	
	public static void main(String[] args){

		Scanner console = new Scanner(System.in);// scanner to read input
		System.out.print(Message.NEWCOMMAND);
		System.out.print(Message.WELCOME);
		System.out.print(Message.NEWCOMMAND);
		String userCommands = console.nextLine();
		currentWorkingDirectory = System.getProperty("user.dir");// work in the directory where the program is runnning 
		while(!userCommands.equals("exit")) {// if user didn't type exit
			SequentialCommandBuilder current = new SequentialCommandBuilder(userCommands, currentWorkingDirectory);
			List<SequentialFilter> filterList = current.createFiltersFromCommand();
			Iterator<SequentialFilter> itr = filterList.iterator();
			SequentialFilter currFilter;
			if(itr != null) {// if there is filters in the filterList
				while(itr.hasNext()) {
					currFilter =  itr.next();
					currFilter.process();// process the filter
				}
			}
			System.out.print(Message.NEWCOMMAND);
			userCommands = console.nextLine();
		}
		System.out.println(Message.GOODBYE);

	}

}
