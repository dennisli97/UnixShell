package cs131.pa1.filter.sequential;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class CD extends SequentialFilter{
	
	// constructor to add change of directory into input and initialize feild userCommand
	// for error message
	private String change;
	
	public CD(String change) {
		this.change = change;
	}
	// process input queue to add on currentWorkingDirectory
	// contain returns to update directory
	public void process() {
		String currWD = SequentialREPL.currentWorkingDirectory;
		String endDir = currWD + System.getProperty("file.separator") + change;
		//to check if user's desired dir exists
		if (Files.isDirectory(Paths.get(endDir))) {
			//check for .. subcmd
			if (change.equals("..")) {
				int cut = currWD.lastIndexOf(FILE_SEPARATOR);
				currWD = currWD.substring(0, cut);
			}
			currWD = currWD + FILE_SEPARATOR + change;
			SequentialREPL.currentWorkingDirectory = currWD;
		} else { 
			System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter("cd " + change));
		}
//		String currentWorkingDirectory = SequentialREPL.currentWorkingDirectory;
////		String changes= input.poll();
//		// case of no subcommand, cd to the most basic directory
//		if(change.equals("")) {
//			SequentialREPL.currentWorkingDirectory = System.getProperty("user.dir");
//			//			int count=0;
////			int space =2;// most basic directory should only have 2/
////			for(int i=0; i < currentWorkingDirectory.length(); i++) {
////				char ch = currentWorkingDirectory.charAt(i);
////				if(ch == '/' && space !=0) {// if char is /, and has only reach the first two /
////					count = i;
////					space--;
////				}
////			}
////			if(space == 0) {
////				SequentialREPL.currentWorkingDirectory = currentWorkingDirectory;
////			}else {
////				SequentialREPL.currentWorkingDirectory = currentWorkingDirectory.substring(0, count);
////			}
//			
//		// case of one period as subcommand, stay in same working directory	
//		}else if(change.equals(".")) {
//			SequentialREPL.currentWorkingDirectory =  currentWorkingDirectory; 
//		
//		}else if(change.equals("..")) {// case of tow period, return to the previous directory
//			
//			int count=0;// counting the number of /
//			for(int i=0; i < currentWorkingDirectory.length(); i++) {
//				char ch = currentWorkingDirectory.charAt(i);
//				if(ch == '/') {
//					count = i;
//				}
//			}
//			SequentialREPL.currentWorkingDirectory = currentWorkingDirectory.substring(0, count);// cut the string before the last /
//		
//		}else {// case of going into a directory
//			Path newPath = Paths.get(currentWorkingDirectory+"/"+change);// initialize the path 
//			if(Files.exists(newPath)) {// check if directory exist
//				SequentialREPL.currentWorkingDirectory =  currentWorkingDirectory+"/"+change;
//			}else {// else, error massage print out, stay in same directory
//				System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter("CD "+change));
//				SequentialREPL.currentWorkingDirectory =  currentWorkingDirectory;
//			}
//		}
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	} 
}
