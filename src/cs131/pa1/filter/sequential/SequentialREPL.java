package cs131.pa1.filter.sequential;


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
		System.out.println(currentWorkingDirectory);
		while(!userCommands.equals("exit")) {
			SequentialCommandBuilder currBuild = new SequentialCommandBuilder(userCommands, currentWorkingDirectory);
			// procvess and print out output  from command
			List<SequentialFilter> currCmds = currBuild.createFiltersFromCommand();
			
			
			System.out.print(Message.NEWCOMMAND);
			userCommands = console.nextLine();
		}

	}

}
