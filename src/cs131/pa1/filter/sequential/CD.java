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
		String endDir = currWD + FILE_SEPARATOR + change;
		//to check if user's desired dir exists
		if (Files.isDirectory(Paths.get(endDir))) {
			//check for .. subcmd
			if (change.equals("..") || change.equals(".")) {
				if (change.equals("..")) {
					int cut = currWD.lastIndexOf(FILE_SEPARATOR);
					currWD = currWD.substring(0, cut);
				}
			} else {
				currWD = currWD + FILE_SEPARATOR + change;
			}
			SequentialREPL.currentWorkingDirectory = currWD;
<<<<<<< HEAD
		} else {
=======
		//case where user's desired dir does not exist
		} else { 
>>>>>>> bd6bbc191988f3a766dfefd1ca275e0878083cd8
			System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter("cd " + change));
		}
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
