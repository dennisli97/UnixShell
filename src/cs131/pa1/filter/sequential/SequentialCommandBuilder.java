package cs131.pa1.filter.sequential;

import java.util.List;
import cs131.pa1.filter.Message;
import java.util.*;
import java.io.*;

public class SequentialCommandBuilder {
	//user commands is the string from user input
	private String userCommands;
	//queue of commands after being verified
	private List<SequentialFilter> verifiedCommands;
	//current working directory
	private String currentWorkingDirectory;
	//set of unique recognized userCommands
	private static Set<String> recognizedCommands;

	public SequentialCommandBuilder(String userCommands, String currentWorkingDirectory) {
		this.userCommands = userCommands;
		this.verifiedCommands = new LinkedList<SequentialFilter>();
		this.currentWorkingDirectory = currentWorkingDirectory;
		this.recognizedCommands = new HashSet<String>(Arrays.asList(
			new String[] {"pwd", "ls", "cd", "cat", "grep", "wc", "uniq", ">"}
		));
	}

	//verifies the user's input syntax, and adds it to a linked list of verified commands to be executed by process
	public static Queue<String> verifyAndSplit(String userCommands) {
		//create a queue to hold the verified commands
		Queue<String> verified = new LinkedList<String>();
		//if user doesnt enter any input
		if (userCommands == null) {
			return null;
		}
		//split the userInput by | and put them into an Array
		userCommands.replace(">", "|>");
		userCommands.replace("|", "\\|");
		String[] split = userCommands.split("\\|");
		//loop verify each command/add it to the queue of verified commands
		int count = 0;
		String currCmdString = "";
		while (count < split.length) {
			//single out each cmd and check if it is recognized
			currCmdString = split[count];
			String cmd, subCmd = "";
			Scanner readCmd = new Scanner(currCmdString);
			cmd = readCmd.nextLine();
			//check if subCmd is present, if yes: save it
			if (readCmd.hasNext()) {
				subCmd += readCmd.next();
			}
			//boolean to check and see if there is more than 1 subcmd
			boolean multSubs = false;
			//need to support multiple subcmds for cat
			while (readCmd.hasNext()) {
				subCmd = subCmd + " " + readCmd.next();
				multSubs = true;
			}
			readCmd.close();
			//boolean to determine whether or not all cmds are valid
			boolean containsBrokenCmds = false;
			//boolean to check if piping happens or not
			boolean pipe = false;
			//checks all possible/acceptable combinations of cmds and subcmds
			//check if cmd is valid, if not return null queue
			if (!recognizedCommands.contains(cmd)) {
				containsBrokenCmds = true;
				System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(cmd));
			//else case: cmd is verified
			} else {
				//1: check pwd cases
				if (cmd.equals("pwd")) {
					//case for pwd with subcmd
					if (subCmd.length() != 0) {
						containsBrokenCmds = true;
						System.out.print(Message.INVALID_PARAMETER.with_parameter(cmd));
					}
				//2: check ls cases
				} else if (cmd.equals("ls")) {
					//case for ls with subcmd
					if (subCmd.length() != 0) {
						containsBrokenCmds = true;
						System.out.print(Message.INVALID_PARAMETER.with_parameter(cmd));
					}
				//3: check cd cases, pass over empty string even if it happens
				} else if (cmd.equals("cd")) {
					//cd cannot have more than 1 subcmd
					if (multSubs) {
						containsBrokenCmds = true;
						System.out.print(Message.INVALID_PARAMETER);
					}
				//4: check cat cases
				} else if (cmd.equals("cat")) {

				//5: check grep cases
				} else if (cmd.equals("grep")) {

				//6: check wc cases
				} else if (cmd.equals("wc")) {

				//7:check uniq cases
				} else if (cmd.equals("uniq")) {

				//8: check > cases
				} else {

				}

			}
			//rej everything if a single brokencmd is present
			if (containsBrokenCmds) {
				return null;
			//concat the cmd and subcmd and attach to queue
			} else {
				if (subCmd.length() != 0) {
					cmd = cmd + " " + subCmd;
				}
				verified.add(cmd);
			}
			count++;
		}
		return verified;
	}

	public List<SequentialFilter> createFiltersFromCommand(String userCommands){
		Queue<String> splitCmds = verifyAndSplit(userCommands);
		String currFullCmd;
		String cmd;
		//traverse the list of verified user cmds and construct filters one by one
		if (splitCmds != null) {
			while (splitCmds.peek() != null) {
				currFullCmd = splitCmds.poll();
				Scanner breakCurrCmd = new Scanner(currFullCmd);
				cmd = breakCurrCmd.next();
				if (cmd.equals("pwd")) {
					this.verifiedCommands.add(new PWD(currFullCmd));
				} /*else if (cmd.equals("ls")) {
					this.verifiedCommands.add(new LS(currFullCmd));
				} else if (cmd.equals("cd")) {
					this.verifiedCommands.add(new CD(currFullCmd));
				} else if (cmd.equals("cat")) {
					this.verifiedCommands.add(new CAT(currFullCmd));
				} else if (cmd.equals("grep")) {
					this.verifiedCommands.add(new GREP(currFullCmd));
				} else if (cmd.equals("wc")) {
					this.verifiedCommands.add(new WC(currFullCmd));
				} else if (cmd.equals("uniq")) {
					this.verifiedCommands.add(new UNIQ(currFullCmd));
				} else {
					//to handle ">" cmd
				}*/
				breakCurrCmd.close();
			}
		}
		return verifiedCommands;
	}

	private static SequentialFilter determineFinalFilter(String command){
		return null;
	}

	private static String adjustCommandToRemoveFinalFilter(String command){
		return null;
	}

	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		return null;
	}
	//to link an already verified and instantiated cmd to the list
	private static boolean linkFilters(List<SequentialFilter> verifiedCommands, SequentialFilter cmd){
		verifiedCommands.add(cmd);
		return true;
	}
}
