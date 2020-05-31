package frame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Do NOT change anything in this class!
 * 
 * This class contains a method for reading the input files into a Process class.
 * 
 * @author Stefan Kropp, Felix Rohrbach
 */
public class ProcessTestfileReader {

	private String filename = null;

	/**
	 * The file should be in the same directory as the java application. if not,
	 * you have to provide the absolute or relative path information within the
	 * filename string.
	 * 
	 * @param filename
	 *            the name of the file to read
	 */
	public ProcessTestfileReader(String filename) {
		this.filename = filename;
	}

	/**
	 * Reads a file, specified in the private field filename and returns the
	 * information read. The file should have the same format as specified in
	 * the first lab.
	 * 
	 * @return Returns a Vector which holds the Process objects. In the case
	 *         an error occurred we throw a RuntimeException.
	 */
	public ArrayList<Process> readFile() {
		try {
			ArrayList<Process> processes = new ArrayList<Process>(); 
			FileReader fr = new FileReader(filename);
			BufferedReader in = new BufferedReader(fr);
	
			String line;
			while ((line = in.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				String[] data = line.split(";");
				String processName = data[0];
				ArrayList<Integer> processWindows = new ArrayList<Integer>();
				for( int i=1; i<data.length; i++ ) {
					processWindows.add(Integer.parseInt(data[i]));
				}
				Process process = new Process(processName, processWindows);
				processes.add(process);
			}
	
			in.close();
			fr.close();
	
			return processes;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Testfile is broken!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Testfile is broken!");
		}
	}
	
}
