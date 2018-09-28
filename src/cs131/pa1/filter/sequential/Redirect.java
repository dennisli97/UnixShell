package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

public class Redirect extends SequentialFilter {
	
	private String fileName;
	
	public Redirect (String fileName) {
		this.fileName = fileName;
	}
	
	public void process() {
		// build a file with the path
		File file = new File(SequentialREPL.currentWorkingDirectory+fileName+".txt");
		try {// try to creat the file
			file.createNewFile();
		} catch (IOException e) {// if file already exist, error message
			// file already exist, over write the file
		}
		// printStream to wirte in file
		PrintStream desireFile;
		try {
			desireFile = new PrintStream(file);
			while(!isDone()) {
				desireFile.println(input.poll());// add all input into the file
			}
		} catch (FileNotFoundException e) {
			//file will be created if no file;
		}
		
		
	}
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
