package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class CAT extends SequentialFilter{
	// constructor to initialize th efilter
	public CAT(String filesNames, String userCommand) {
		this.userCommand = userCommand;
		// if there is multiple files to cat, add them separately in the input queue
		String[] files = filesNames.split(" ");
		for(int i=0; i<files.length;i++) {
			input.add(files[i]);
		}
	}
	
	
	
	public void process(String currentWorkingDirectory) throws FileNotFoundException {
		File[] files = new File(currentWorkingDirectory).listFiles();// list all file in the directory
		boolean foundFile; // to check if file is found in the directory
		while (!input.isEmpty()){
			foundFile = false;// reassaign every time there is a new file to be find
			String fileName = input.poll();
			for (File file : files) {// go through file to find the desire file 
			    if (file.getName().equals(fileName)) {
			        File toCat = new File(currentWorkingDirectory+fileName);
			        readFile(toCat);
			        foundFile = true;
			    }
			}
			if(foundFile == false) {// if the file is not found in the directory, error message
				System.out.println(Message.FILE_NOT_FOUND.with_parameter(userCommand));
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
