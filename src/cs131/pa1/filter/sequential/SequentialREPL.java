package cs131.pa1.filter.sequential;


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
		currentWorkingDirectory = "";
		while(!userCommands.equals("exit")) {
			SequentialCommandBuilder current = new SequentialCommandBuilder(userCommands, currentWorkingDirectory);
			// procvess and print out output  from command
			
			
			System.out.print(Message.NEWCOMMAND);
			userCommands = console.nextLine();
		}

	}

}
