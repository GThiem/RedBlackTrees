package lab;

/**
 * Aufgabe H1a)
 * 
 * Abgabe von: <name>, <name> und <name>
 */


import frame.TreeNode;

/**
 * An implementation of a Black-Red-Tree
 */
public class RedBlackTree {
	
	private TreeNode _root;
	private TreeNode _nil;
	
	public RedBlackTree() {
		_nil = new TreeNode();
		_root = _nil;
	}
	
	public TreeNode root() {
		return this._root;
	}
	
	public TreeNode nil() {
		return this._nil;
	}
	
	/**
	 * Return the node with the given key, or nil, if no such node exists.
	 */
	public TreeNode search(int key) {
        // TODO: Implement
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Return the node with the smallest key
	 */
	public TreeNode minimum() {
		return minimumSubtree(_root);
	}
	
	/**
	 * Return the node with the smallest key in the subtree that has x as root node.
	 */
	public TreeNode minimumSubtree(TreeNode x) {
		// TODO: Implement
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Return the node with the greatest key.
	 */
	public TreeNode maximum() {
		return maximumSubtree(_root);
	}
	
	/**
	 * Return the node with the greatest key in the subtree that has x as root node.
	 */
	public TreeNode maximumSubtree(TreeNode x) {
		// TODO: Implement
		throw new RuntimeException("Not implemented yet!");
	}

	/**
	 * Insert newNode into the tree.
	 */
	public void insert(TreeNode newNode) {
		// TODO: Implement
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Delete node toDelete from the tree.
	 */
	public void delete(TreeNode toDelete) {
		// TODO: Implement
		throw new RuntimeException("Not implemented yet!");
	}

}
