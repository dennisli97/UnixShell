package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class CAT extends SequentialFilter{
	private String filesNames;
	
	// constructor to initialize the filter
	public CAT(String filesNames) {
		// if there is multiple files to cat, add them separately in the input queue
		this.filesNames = filesNames;
		String[] files = filesNames.split(" ");
		input = new LinkedList<String>();
		output = new LinkedList<String>();
		for(int i=0; i<files.length;i++) {
			input.add(files[i]);
		}
	}
	
	
	
	public void process() {
		String currentWorkingDirectory = SequentialREPL.currentWorkingDirectory;
		File[] files = new File(currentWorkingDirectory).listFiles();// list all file in the directory
		boolean foundFile; // to check if file is found in the directory
		while (!input.isEmpty()){
			foundFile = false;// reassaign every time there is a new file to be find
			String fileName = input.poll();
			for (File file : files) {// go through file to find the desire file 
			    if (file.getName().equals(fileName)) {
			        File toCat = new File(currentWorkingDirectory+"/"+fileName);
			        try {
						readFile(toCat);
						foundFile = true;
					} catch (FileNotFoundException e) {
						// exception has been handled
					}
			    }
			}
			if(foundFile == false) {// if the file is not found in the directory, error message
				System.out.print(Message.NEWCOMMAND+Message.FILE_NOT_FOUND.with_parameter("CAT " + filesNames));
				return;
			}
		}
		
	}
	
	// method to read the file and add every line into the output queue
	private void readFile(File file) throws FileNotFoundException {
		Scanner fileScan = new Scanner(file);
		String lines;
		while(fileScan.hasNextLine()) {
			lines = fileScan.nextLine();
			output.add(lines);
		}
		
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
