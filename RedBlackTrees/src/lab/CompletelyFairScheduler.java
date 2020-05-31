package lab;

/**
 * Aufgabe H1b)
 * 
 * Abgabe von: <name>, <name> und <name>
 */

import frame.TreeNode;
import frame.Process;

public class CompletelyFairScheduler {
	
	private RedBlackTree tree;
	private int windowMaxLength;
	
	/**
	 * Creating a new CompletelyFairScheduler
	 * @param windowMaxLength is the maximal length of one execution window
	 */
	public CompletelyFairScheduler(int windowMaxLength) {
		tree = new RedBlackTree();
		this.windowMaxLength = windowMaxLength;
	}
	
	/**
	 * Distribute execution windows to the processes.
	 * @param windows The number of execution windows to distribute
	 */
	public void run(int windows) {
		// TODO: Implement
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Add a process to the Scheduler.
	 */
	public void add(Process process) {
		// TODO: Implement
		throw new RuntimeException("Not implemented yet!");
	}
	
	// DO NOT MODIFY
	// used for the tests
	public RedBlackTree getTree() {
		return tree;
	}

}
