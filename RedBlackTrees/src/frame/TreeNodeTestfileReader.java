package frame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Do NOT change anything in this class!
 * 
 * This class contains a method for reading the input files into a TreeNode class.
 * 
 * @author Stefan Kropp, Felix Rohrbach
 */
public class TreeNodeTestfileReader {

	private String filename = null;

	/**
	 * The file should be in the same directory as the java application. if not,
	 * you have to provide the absolute or relative path information within the
	 * filename string.
	 * 
	 * @param filename
	 *            the name of the file to read
	 */
	public TreeNodeTestfileReader(String filename) {
		this.filename = filename;
	}

	/**
	 * Reads a file, specified in the private field filename and returns the
	 * information read. The file should have the same format as specified in
	 * the first lab.
	 * 
	 * @return Returns a Vector which holds the TreeNode objects. In the case
	 *         an error occurred we throw a RuntimeException.
	 */
	public ArrayList<TreeNode> readFile() {
		try {
			ArrayList<TreeNode> nodes = new ArrayList<TreeNode>(); 
			FileReader fr = new FileReader(filename);
			BufferedReader in = new BufferedReader(fr);
	
			String line;
			while ((line = in.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				String[] data = line.split(";");
				String value = data[0];
				int key = Integer.parseInt(data[1]);
				Process dummyProcess = new Process(value, new ArrayList<Integer>());
				TreeNode node = new TreeNode();
				node.value = dummyProcess;
				node.key = key;
				nodes.add(node);
			}
	
			in.close();
			fr.close();
	
			return nodes;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Testfile is broken!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Testfile is broken!");
		}
	}
	
}
