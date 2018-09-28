package cs131.pa1.filter.sequential;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;


public class SequentialREPL {

	static String currentWorkingDirectory;
	
	public static void main(String[] args){

		Scanner console = new Scanner(System.in);
		System.out.print(Message.NEWCOMMAND);
		System.out.print(Message.WELCOME);
		System.out.print(Message.NEWCOMMAND);
		String userCommands = console.nextLine();
		currentWorkingDirectory = System.getProperty("user.dir");
		while(!userCommands.equals("exit")) {
			SequentialCommandBuilder current = new SequentialCommandBuilder(userCommands, currentWorkingDirectory);
			List<SequentialFilter> filterList = current.createFiltersFromCommand();
			Iterator<SequentialFilter> itr = filterList.iterator();
			SequentialFilter currFilter;
			if(itr != null) {
				while(itr.hasNext()) {
					currFilter =  itr.next();
					currFilter.process();
				}
			}
			System.out.print(Message.NEWCOMMAND);
			userCommands = console.nextLine();
		}
		System.out.println(Message.GOODBYE);

	}

}
