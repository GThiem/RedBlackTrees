package lab;

/**
 * Aufgabe H1a)
 * 
 * Abgabe von: <name>, <name> und <name>
 */


import frame.TreeNode;
import frame.TreeNode.NodeColor;

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
		return searchRec(_root, key);
		
	}
	
	//recursion for search
	public TreeNode searchRec(TreeNode x, int key) {
		
		if(x == _nil) 
			return _nil;
		
		if(x.key == key)
			return x;
		
		if(x.key > key)
			return searchRec(x.left, key);
		else
			return searchRec(x.right, key);
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
		if(x == _nil)
			return _nil;
		
		while(x.left != _nil)
			x = x.left;
		
		return x;
		
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
		if(x == _nil)
			return _nil;
		
		while(x.right != _nil)
			x = x.right;
		
		return x;
	}

	/**
	 * Insert newNode into the tree.
	 */
	public void insert(TreeNode newNode) {
		// TODO: Implement
		TreeNode sent = createSentinel();
		TreeNode x = _root;
		TreeNode px = sent;
		newNode.right = newNode.left = _nil;
		
		while(x != _nil) {
			px=x;
			if(x.key > newNode.key) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		newNode.p = px;
		
		if(px == sent) {
			_root = newNode;
		} else {
			if(px.key > newNode.key) {
				px.left = newNode;
			} else {
				px.right = newNode;
			}
		}
		
		newNode.color = NodeColor.RED;
		fixColorAfterInsertion(newNode);
		
	}
	
	/**
	 * Delete node toDelete from the tree.
	 */
	public void delete(TreeNode toDelete) {
		// TODO: Implement
	}
	
	//left rotation
	public void rotateLeft(TreeNode x) {
		
		TreeNode sent = createSentinel();		
		TreeNode y = x.right;
		x.right = y.left;
		
		if(y.left != _nil) {
			y.left.p = x;
		}
		
		y.p = x.p;
		
		if(x.p == sent) {
			_root = y;
		} else {
			if(x == x.p.left) {
				x.p.left = y;
			} else {
				x.p.right = y;
			}
		}
		
		y.left = x;
		x.p = y;
	}
	
	//right rotation
	public void rotateRight(TreeNode x) {
		
		TreeNode sent = createSentinel();		
		TreeNode y = x.left;
		x.left = y.right;
		
		if(y.right != _nil) {
			y.right.p = x;
		}
		
		y.p = x.p;
		
		if(x.p == sent) {
			_root = y;
		} else {
			if(x == x.p.right) {
				x.p.right = y;
			} else {
				x.p.left = y;
			}
		}
		
		y.right = x;
		x.p = y;
	}
	
	public void fixColorAfterInsertion(TreeNode z) {
		
		while(z.p.color == NodeColor.RED) {
			if(z.p == z.p.p.left) {
				
				TreeNode y = z.p.p.right;
				if(y != _nil && y.color == NodeColor.RED) {
					z.p.color = NodeColor.BLACK;
					y.color = NodeColor.BLACK;
					z.p.p.color = NodeColor.RED;
					z = z.p.p;
				} else {
					if(z == z.p.right) {
						z = z.p;
						rotateLeft(z);
					}
					z.p.color = NodeColor.BLACK;
					z.p.p.color = NodeColor.RED;
					rotateRight(z.p.p);
				}
				
			} else {
				
				TreeNode y = z.p.p.left;
				if(y != _nil && y.color == NodeColor.RED) {
					z.p.color = NodeColor.BLACK;
					y.color = NodeColor.BLACK;
					z.p.p.color = NodeColor.RED;
					z = z.p.p;
				} else {
					if(z == z.p.left) {
						z = z.p;
						rotateRight(z);
					}
					z.p.color = NodeColor.BLACK;
					z.p.p.color = NodeColor.RED;
					rotateLeft(z.p.p);
				}
			}
		}
		
		_root.color = NodeColor.BLACK;
		
	}
	
	public TreeNode createSentinel() {
		TreeNode sent = new TreeNode();
		sent.p = sent;
		sent.left = sent;
		sent.right = sent;
		_root.p = sent;
		
		return sent;
	}
	
}
