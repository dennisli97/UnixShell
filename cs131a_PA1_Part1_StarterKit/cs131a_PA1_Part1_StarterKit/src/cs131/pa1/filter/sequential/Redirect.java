package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class Redirect extends SequentialFilter {
	
	private String fileName;
	
	public Redirect (String fileName) {
		this.fileName = fileName;
	}
	
	public void process() {
		String endPath = SequentialREPL.currentWorkingDirectory + FILE_SEPARATOR + fileName;
		// build a file with the path
		File file = new File(endPath);
		//overwrite if it already exists
		if (file.exists()) {
			file.delete();
		}
		// printStream to write in file
		PrintStream insert;
		try {
			insert = new PrintStream(file);
			//case to check for input
			if (input == null) {
				System.out.println(Message.REQUIRES_INPUT.with_parameter("> " + fileName));
				return;
			}
			while(!isDone()) {
				insert.println(input.poll());// add all input into the file
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
