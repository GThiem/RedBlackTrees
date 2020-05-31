package frame;

import java.util.ArrayList;

/**
 * A process which should be given time windows to execute
 * 
 * @author Felix Rohrbach
 *
 */
public class Process {
	
	private String name;
	private int executionTime;
	private ArrayList<Integer> windows;
	
	public Process() {
		windows = new ArrayList<Integer>();
		executionTime = 0;
	}
	
	public Process(String name, ArrayList<Integer> windows) {
		this.windows = windows;
		executionTime = 0;
		this.name = name;
	}
	
	/**
	 * Returns the time used by the process on the CPU. This should be used as a weight in the
	 * red-black-tree of the scheduler.
	 */
	public int executionTime() {
		return this.executionTime;
	}
	
	/**
	 * Check whether the process is finished running.
	 * @return true if finished, false otherwise.
	 */
	public boolean finished() {
		return windows.size() == 0;
	}
	
	/**
	 * Give the process an execution window on the CPU.
	 * @param maxlength The maximal length of the window.
	 */
	public void run(int maxlength) {
		if( windows.size() == 0 ) {
			return;
		}
		if( windows.get(0) > maxlength ) {
			windows.set(0, windows.get(0) - maxlength);
			executionTime += maxlength;
		} else {
			executionTime += windows.get(0);
			windows.remove(0);
		}
		
	}
	
	/**
	 * @return The name of the process.
	 */
	public String name() {
		return this.name;
	}

}
