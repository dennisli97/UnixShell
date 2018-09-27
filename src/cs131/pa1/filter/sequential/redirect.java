package cs131.pa1.filter.sequential;

import java.nio.file.Path;

public class redirect extends SequentialFilter {
	
	private String fileNamne;
	
	public redirect (String fileName) {
		this.fileNamne = fileName;
	}
	
	public void process() {
		Path path = newPath.get(SequentialREPL.currentWorkingDirectory);
		
	}
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
