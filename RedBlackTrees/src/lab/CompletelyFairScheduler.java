package lab;

/**
 * Aufgabe H1b)
 * 
 * Abgabe von: Alexander Stichling(2847229), Phuoc Pham(Matr.-Nr.: 2903082) und Gabriel Thiem(Matr.-Nr.: 2831581)
 */

import frame.TreeNode;
import frame.TreeNode.NodeColor;
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
        while(windows >= windowMaxLength  && tree.root() != tree.nil()) {
            TreeNode v = tree.minimum();
            v.value.run(windowMaxLength);
            tree.delete(v);
            if(!v.value.finished()) {
                TreeNode w = new TreeNode(tree.nil(), tree.nil(), tree.nil(), v.value.executionTime(), NodeColor.BLACK, v.value);
                while(tree.search(w.key) != tree.nil()) {
                    w.key += 1;
                }
                tree.insert(w);
            } 
            windows--;
        }
    }
	
	/**
	 * Add a process to the Scheduler.
	 */
	public void add(Process process) {
        // TODO: Implement
        TreeNode v = new TreeNode(tree.nil(), tree.nil(), tree.nil(), process.executionTime(), NodeColor.BLACK, process);
        while(tree.search(v.key) != tree.nil()) {
            v.key += 1;
        }
        tree.insert(v);
        
    }
	
	// DO NOT MODIFY
	// used for the tests
	public RedBlackTree getTree() {
		return tree;
	}

}
