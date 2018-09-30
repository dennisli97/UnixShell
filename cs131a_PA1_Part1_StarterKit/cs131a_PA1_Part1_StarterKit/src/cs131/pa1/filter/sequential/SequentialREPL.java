package cs131.pa1.filter.sequential;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;


public class SequentialREPL {

	static String currentWorkingDirectory;

	public static void main(String[] args){

		Scanner console = new Scanner(System.in);// scanner to read input
		System.out.print(Message.WELCOME);
		System.out.print(Message.NEWCOMMAND);
		String userCommands = console.nextLine();
		currentWorkingDirectory = System.getProperty("user.dir");// work in the directory where the program is running 
		while(!userCommands.equals("exit")) {// if user didn't type exit
			SequentialCommandBuilder current = new SequentialCommandBuilder(userCommands, currentWorkingDirectory);
			List<SequentialFilter> filterList = current.createFiltersFromCommand();
			if(filterList != null) {// if there is filters in the filterList
				Iterator<SequentialFilter> itr = filterList.iterator();
				SequentialFilter currFilter = null;
				while(itr.hasNext()) {
					currFilter =  itr.next();
					currFilter.process();// process the filter
				}
				if (currFilter.output != null) {
					while(!currFilter.output.isEmpty()) {
						System.out.println(currFilter.output.poll());
					}
				}
			}
			System.out.print(Message.NEWCOMMAND);
			userCommands = console.nextLine();
		}
		System.out.print(Message.GOODBYE);

	}

}
