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
	public TreeNode sent;
	
	public RedBlackTree() {
		_nil = new TreeNode();
		_root = _nil;	
		sent = createSentinel();
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
		return searchRec(_root, key); //Ruft die Rekursionsmethode auf.
		
	}
	
	//recursion for search
	public TreeNode searchRec(TreeNode x, int key) {
		
		if(x == _nil) //Dies ist der Rekursionsanker: Ist der Knoten _nil, ist der Algorithmus
					 //am Ende des Pfades angelangt, der Schlüsselwert wurde also nicht gefunden.
			return _nil; //Gibt _nil zurück, was bedeutet, dass der Schlüsselwert im Baum nicht
						 //existiert.
		
		if(x.key == key) //Entspricht der Schlüsselwert des momentan betrachteten Knotens
						 // dem des gesuchten Schlüsselwerts?
			return x;	// Ja -> Gib den momentan betrachteten Knoten zurück,andernfalls fahre
						//mit der Methode fort.
		
		/*Ist der Schlüsselwert des momentan betrachteten Knotens größer dem gesuchten
		 * Schlüsselwert, wird im linken Kind weitergesucht, andernfalls im rechten.
		 * Dieser Aufruf erfolgt rekursiv so lange, bis entweder der gesuchte
		 * Schlüsselwert gefunden wurde oder auf _nil verwiesen wird, also das Ende des
		 * Pfades erreicht worden ist.
		 * Dies funktioniert, weil Rot-Schwarz-Bäume eine echte Teilmenge der Binären
		 * Suchbäume sind, welche ihre Werte so geordnet haben, dass der Schlüsselwert des
		 * linken Kinds kleiner dem Schlüsselwert des momentan betrachteten Knotens
		 * (bzw. Elternknoten) ist und der Schlüsselwert des rechten Kinds größer dem
		 * Schlüsselwert des momentan betrachteten Knotens(bzw.Elternknoten) ist.*/
		
		if(x.key > key)	//Ist der Schlüsselwert des momentan betrachteten Knotens größer
						//dem gesuchten Schlüsselwert?
			return searchRec(x.left, key); //Ja -> Überprüfe das linke Kind
		else			
			return searchRec(x.right, key);//Nein -> Überprüfe das rechte Kind
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
		if(x == _nil) //Ist der übergebene Knoten leer, gib einen leeren Knoten zurück,
			return _nil;// da es so keinen Knoten mit kleinstem Wert geben kann.
		
		/*Ist das linke Kind des momentan betrachteten Knotens nicht leer (also _nil), dann
		 * weise dem Zeiger x das linke Kind zu, so dass wir nun das linke Kind des zuvor betrachteten
		 * Knotens betrachten.
		 * Da in einem BST der Schlüsselwert des linken Kinds eines Knotens immer kleiner
		 * dem Schlüsselwert des Knotens selbst ist, muss man, um an den Knoten mit dem
		 * kleinsten Wert zu gelangen, immer das linke Kind aufrufen, bis man am Ende des
		 * Pfades angekommen ist.*/
		
		while(x.left != _nil) //Führe Inhalt der while-Schleife nur aus, wenn linkes Kind nicht leer ist
			x = x.left; //Weise x das linke Kind von x zu, um anschließend zu überprüfen,
						//ob x nun der letzte Knoten im linken Pfad und somit der Knoten mit
						//dem kleinsten Schlüsselwert ist.
		
		return x;		//Ist die while-Schleife beendet, zeigt x auf den Knoten mit kleinstem
						//Schlüsselwert, somit zeigt x auf den gesuchten Knoten und kann zurückgegeben werden.
		
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
		//maximumSubtree funktioniert analog wie minimumSubtree, nur mit rechtem Kind:
		
		/*Ist der übergebene Knoten leer, gib einen leeren Knoten zurück, da es in einem
		 * leeren Teilbaum keinen Knoten mit höchstwertigen Schlüsselwert gibt. */
		if(x == _nil)
			return _nil;
		
		/* Ist das rechte Teilkind nicht leer, so weise dem Zeiger x, welcher auf den momentan
		 * betrachteten Knoten zeigt, nun das rechte Kind des momentan betrachteten Knotens zu.
		 * Es wird immer das rechte Kind aufgerufen, weil in einem BST das rechte Kind eines Knotens
		 * immer einen höherwertigen Schlüsselwert hat als sein Elternknoten.
		 */
		
		while(x.right != _nil)
			x = x.right;
		
		return x;	//Ist die while-Schleife beendet, zeigt x auf den Knoten mit höchstwertigem
					//Schlüsselwert, somit zeigt x auf den gesuchten Knoten und kann zurückgegeben werden.
	}

	/**
	 * Insert newNode into the tree.
	 */
	public void insert(TreeNode newNode) {
		// TODO: Implement
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
		TreeNode y;
		TreeNode x;
		
		if(toDelete.left == _nil || toDelete.right == _nil) {
			y = toDelete;
		} else {
			if(toDelete.right != _nil) {
				y = minimumSubtree(toDelete.right);
			} else {
				TreeNode temp = toDelete.p;
				while(temp != _nil && toDelete == temp.right) {
					toDelete = temp;
					temp = temp.p;
				}
				y = temp;
			}
		}
		
		if(y.left != _nil) {
			x = y.left;
		} else {
			x = y.right;
		}
		x.p = y.p;
		if(y.p == _nil) {
			_root = x;
		} else if(y == y.p.left) {
			y.p.left = x;
		} else {
			y.p.right = x;
		}
		
		if(y != toDelete) {
			toDelete.key = y.key;
		}
		
		if(y.color == NodeColor.BLACK) {
			fixup(x);
		}
	}
	
	//left rotation
	public void rotateLeft(TreeNode x) {
				
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
	
	private void fixup (TreeNode x) {
		TreeNode w;
		while(x != _root && x.color == NodeColor.BLACK) {
			if(x == x.p.left) {
				w = x.p.right;
				if(w.color == NodeColor.RED) {
					w.color = NodeColor.BLACK;
					x.p.color = NodeColor.RED;
					rotateLeft(x.p);
					w = x.p.right;
				}
				if(w.left.color == NodeColor.BLACK && w.right.color == NodeColor.BLACK) {
					w.color = NodeColor.RED;
					x = x.p;
				} else {
					if(w.right.color == NodeColor.BLACK) {
						w.left.color = NodeColor.BLACK;
						w.color = NodeColor.RED;
						rotateRight(w);
						w = x.p.right;
					}
					w.color = x.p.color;
					x.p.color = NodeColor.BLACK;
					w.right.color = NodeColor.BLACK;
					rotateLeft(x.p);
					x = _root;
				}
			} else {
				w = x.p.left;
				if(w.color == NodeColor.RED) {
					w.color = NodeColor.BLACK;
					x.p.color = NodeColor.RED;
					rotateRight(x.p);
					w = x.p.left;
				}
				if(w.right.color == NodeColor.BLACK && w.right.color == NodeColor.BLACK) {
					w.color = NodeColor.RED;
					x = x.p;
				} else {
					if(w.left.color == NodeColor.BLACK) {
						w.right.color = NodeColor.BLACK;
						w.color = NodeColor.RED;
						rotateLeft(w);
						w = x.p.left;
					}
					w.color = x.p.color;
					x.p.color = NodeColor.BLACK;
					w.left.color = NodeColor.BLACK;
					rotateRight(x.p);
					x = _root;
				}
			}
		}
		x.color = NodeColor.BLACK;
	}
	
}
