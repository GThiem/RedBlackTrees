package lab;

/**
 * Aufgabe H1a)
 * 
 * Abgabe von: Alexander Stichling(2847229), Phuoc Pham(Matr.-Nr.: 2903082) und Gabriel Thiem(Matr.-Nr.: 2831581)
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
					 //am Ende des Pfades angelangt, der Schluesselwert wurde also nicht gefunden.
			return _nil; //Gibt _nil zurueck, was bedeutet, dass der Schluesselwert im Baum nicht
						 //existiert.
		
		if(x.key == key) //Entspricht der Schluesselwert des momentan betrachteten Knotens
						 // dem des gesuchten Schluesselwerts?
			return x;	// Ja -> Gib den momentan betrachteten Knoten zurueck,andernfalls fahre
						//mit der Methode fort.
		
		/*Ist der Schluesselwert des momentan betrachteten Knotens groeßer dem gesuchten
		 * Schluesselwert, wird im linken Kind weitergesucht, andernfalls im rechten.
		 * Dieser Aufruf erfolgt rekursiv so lange, bis entweder der gesuchte
		 * Schluesselwert gefunden wurde oder auf _nil verwiesen wird, also das Ende des
		 * Pfades erreicht worden ist.
		 * Dies funktioniert, weil Rot-Schwarz-Bäume eine echte Teilmenge der Binaeren
		 * Suchbaeume sind, welche ihre Werte so geordnet haben, dass der Schluesselwert des
		 * linken Kinds kleiner dem Schluesselwert des momentan betrachteten Knotens
		 * (bzw. Elternknoten) ist und der Schluesselwert des rechten Kinds groeßer dem
		 * Schluesselwert des momentan betrachteten Knotens(bzw.Elternknoten) ist.*/
		
		if(x.key > key)	//Ist der Schluesselwert des momentan betrachteten Knotens groeßer
						//dem gesuchten Schluesselwert?
			return searchRec(x.left, key); //Ja -> Überpruefe das linke Kind
		else			
			return searchRec(x.right, key);//Nein -> Überpruefe das rechte Kind
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
		if(x == _nil) //Ist der uebergebene Knoten leer, gib einen leeren Knoten zurück,
			return _nil;// da es so keinen Knoten mit kleinstem Wert geben kann.
		
		/*Ist das linke Kind des momentan betrachteten Knotens nicht leer (also _nil), dann
		 * weise dem Zeiger x das linke Kind zu, so dass wir nun das linke Kind des zuvor betrachteten
		 * Knotens betrachten.
		 * Da in einem BST der Schluesselwert des linken Kinds eines Knotens immer kleiner
		 * dem Schluesselwert des Knotens selbst ist, muss man, um an den Knoten mit dem
		 * kleinsten Wert zu gelangen, immer das linke Kind aufrufen, bis man am Ende des
		 * Pfades angekommen ist.*/
		
		while(x.left != _nil) //Führe Inhalt der while-Schleife nur aus, wenn linkes Kind nicht leer ist
			x = x.left; //Weise x das linke Kind von x zu, um anschliessend zu überprüfen,
						//ob x nun der letzte Knoten im linken Pfad und somit der Knoten mit
						//dem kleinsten Schluesselwert ist.
		
		return x;		//Ist die while-Schleife beendet, zeigt x auf den Knoten mit kleinstem
						//Schluesselwert, somit zeigt x auf den gesuchten Knoten und kann zurueckgegeben werden.
		
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
		
		/*Ist der uebergebene Knoten leer, gib einen leeren Knoten zurück, da es in einem
		 * leeren Teilbaum keinen Knoten mit hoechstwertigen Schluesselwert gibt. */
		if(x == _nil)
			return _nil;
		
		/* Ist das rechte Teilkind nicht leer, so weise dem Zeiger x, welcher auf den momentan
		 * betrachteten Knoten zeigt, nun das rechte Kind des momentan betrachteten Knotens zu.
		 * Es wird immer das rechte Kind aufgerufen, weil in einem BST das rechte Kind eines Knotens
		 * immer einen höherwertigen Schlüsselwert hat als sein Elternknoten.
		 */
		
		while(x.right != _nil)
			x = x.right;
		
		return x;	//Ist die while-Schleife beendet, zeigt x auf den Knoten mit hoechstwertigem
					//Schluesselwert, somit zeigt x auf den gesuchten Knoten und kann zurueckgegeben werden.
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
		/*
		 * Ermittle Nachfolger von toDelete.
		 */
		if(toDelete.left == _nil || toDelete.right == _nil) { //Falls toDelete weniger als zwei Kinder hat.
			y = toDelete;
		} 
		else {
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
		/*
		 * Im folgenden werden Teilbaeume umgehaengt.
		 */
		if(y.left != _nil) {
			x = y.left;
		} else {
			x = y.right;
		}
		x.p = y.p;
		if(y.p == _nil) { //Ist der Elternknoten des betrachteten Knotens _nil, ist der betrachtete Elternknoten des Knotens y die Wurzel.
			_root = x;	  //Daraufhin wird der Wurzel der Wert von x zugewiesen, also ist der Elternknoten von y nun y.left oder y.right.
		} 
		/*
		 * Hier wird der betrachtete Knoten y durch die Wurzel des Teilbaums x ersetzt.
		 */
		else if(y == y.p.left) { 
			y.p.left = x;
		} else { 
			y.p.right = x;
		}
		//War y nicht der zu loeschende Knoten, wird der Wert des zu loeschenden Knotens einfach ueberschrieben.
		if(y != toDelete) {
			toDelete.key = y.key;
		}
		//Ist der nun umgehaengte Knoten des Teilbaums schwarz, muss beachtet werden, dass die Eigenschaft der gleichmaeßgen Schwarzhöhe erhalten bleibt.
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
	/* 
	 * Die folgende while-Schleife wird so lange ausgefuehrt, bis der Elternknoten des eingefuegten Knotens nicht mehr rot ist,
	 * da der eingefuegte Knoten auch rot ist und ansonsten, wenn der Elternknoten rot bleiben wuerde, die Eigenschaft verletzt waere, dass
	 * ein roter Elternknoten keine roten Kinder haben darf. 
	 */
		while(z.p.color == NodeColor.RED) {
			if(z.p == z.p.p.left) { //Wenn der Elternknoten ein linkes Kind ist, dann:
				
				TreeNode y = z.p.p.right; //Weise y den Onkel unseres eingefuegten Knotens zu.
				/*
				 * Hier wird, wenn der Onkel von z rot ist und nicht _nil ist, sowohl der Onkel als auch der Elternknoten unseres 
				 * eingefuegten Knotens schwarz gefärbt und anschliessend der Großelternknoten unseres eingefügten Knotens rot gefärbt.
				 * Dies geschieht, damit die Schwarzhöhe balanciert bleibt, also die Anzahl an schwarzen Knoten von jedem, von einem beliebigen Knoten
				 * ausgehenden, Pfad gleich ist.
				 */
				if(y != _nil && y.color == NodeColor.RED) {
					z.p.color = NodeColor.BLACK;
					y.color = NodeColor.BLACK;
					z.p.p.color = NodeColor.RED;
					z = z.p.p; //Anschliessend wird im Kopf der while-Schleife ueberprueft, dass der nun rot gefaerbte Großelternknoten nicht auch die
							   //Nicht-Rot-Rot-Regel verletzt.
				} 
				
				/*
				 * Ansonsten wird geschaut, ob unser betrachteter Knoten ein rechtes Kind ist. In diesem Fall weisen wir z den Elternknoten unseres
				 * betrachteten Knotens zu und führen eine Linksdrehung durch. Anschließend wird der Elternknoten des momentan betrachteten Knotens
				 * (also der Großelternknoten) schwarz gefärbt und der Großeltenknoten des momentan betrachteten Knotens (also der Urgroßelternknoten
				 * des urpsrünglich betrachteten Knotens) rot gefärbt. Anschließend wird nochmal um den Großelternknoten des momentan betrachteten Knotens
				 * nach rechts gedreht.
				 */
				
				else {
					if(z == z.p.right) { //Ob z ein linkes oder rechtes Kind ist ist wichtig, damit wir wissen, in welche Richtung wir drehen sollen (hier nach links).
						z = z.p;
						rotateLeft(z);
					}
					z.p.color = NodeColor.BLACK; //Diese Färbungen werden vorgenommen, damit die Schwarzhöhe erhalten bleibt und die Nicht-Rot-Rot-Regel
					z.p.p.color = NodeColor.RED; //eingehalten wird.
					rotateRight(z.p.p);
				}
				
			} 
			/*
			 * Im "else"-Fall funktioniert alles analog wie vorher, nur dass right und left vertauscht worden sind.
			 */
			else {
				TreeNode y = z.p.p.left; //Weise y Onkel zu.
				
				/*
				 * Hier wird, wenn der Onkel von z rot ist und nicht _nil ist, sowohl der Onkel als auch der Elternknoten unseres 
				 * eingefügten Knotens schwarz gefärbt und anschließen der Großelternknoten unseres eingefügten Knotens rot gefärbt.
				 * Dies geschieht, damit die Schwarzhöhe balanciert bleibt, also die Anzahl an schwarzen Knoten von jedem, von einem beliebigen Knoten
				 * ausgehenden, Pfad gleich ist.
				 */
				if(y != _nil && y.color == NodeColor.RED) {
					z.p.color = NodeColor.BLACK;
					y.color = NodeColor.BLACK;
					z.p.p.color = NodeColor.RED;
					z = z.p.p;//Anschließend wird im Kopf der while-Schleife überprüft, dass der nun rot gefärbte Großelternknoten nicht auch die
					          //Nicht-Rot-Rot-Regel verletzt.
				} 
				/*
				 * Ansonsten wird geschaut, ob unser betrachteter Knoten ein linkes Kind ist. In diesem Fall weisen wir z den Elternknoten unseres
				 * betrachteten Knotens zu und führen eine Rechtsdrehung durch. Anschließend wird der Elternknoten des momentan betrachteten Knotens
				 * (also der Großelternknoten) schwarz gefärbt und der Großeltenknoten des momentan betrachteten Knotens (also der Urgroßelternknoten
				 * des urpsrünglich betrachteten Knotens) rot gefärbt. Anschließend wird nochmal um den Großelternknoten des momentan betrachteten Knotens
				 * nach links gedreht.
				 */
				
				else {
					if(z == z.p.left) { //Ob z ein linkes oder rechtes Kind ist ist wichtig, damit wir wissen, in welche Richtung wir drehen sollen(hier nach rechts).
						z = z.p;
						rotateRight(z);
					}
					z.p.color = NodeColor.BLACK; //Diese Färbungen werden vorgenommen, damit die Schwarzhöhe erhalten bleibt und die Nicht-Rot-Rot-Regel
					z.p.p.color = NodeColor.RED; //eingehalten wird.
					rotateLeft(z.p.p);
				}
			}
		}
		
		_root.color = NodeColor.BLACK;//Da ein Rot-Schwarz-Baum immer eine schwarze Wurzel hat, wird die Wurzel sicherheitshalber nochmal schwarz gefärbt.
		
	}
	
	public TreeNode createSentinel() {
		TreeNode sent = new TreeNode();
		sent.p = sent;
		sent.left = sent;
		sent.right = sent;
		_root.p = sent;
		
		return sent;
	}
	/*
	 * Im folgenden werden die Farben des Baums repariert, nachdem ein Knoten gelöscht worden sind. x zeigt auf einen Knoten, der entweder doppelt schwarz ist
	 * oder Rot-Schwarz ist.
	 */
	private void fixup (TreeNode x) {
		TreeNode w;
		while(x != _root && x.color == NodeColor.BLACK) {
			if(x == x.p.left) {
				w = x.p.right;
				//1.Fall: x's Bruder ist rot
				//Der Zweck der folgenden if-Anweisung ist, x schwarz zu färben um dann weitere Fälle behandeln zu können.
				if(w.color == NodeColor.RED) {
					w.color = NodeColor.BLACK; //Vertausche Farben
					x.p.color = NodeColor.RED;
					rotateLeft(x.p); //Drehe nach Links
					w = x.p.right;
				}
				//2.Fall: x's Bruder ist schwarz, die Kinder des Bruders sind auch schwarz
				if(w.left.color == NodeColor.BLACK && w.right.color == NodeColor.BLACK) {
					w.color = NodeColor.RED; //Bruder wird schwarz gefärbt, um Nicht-Rot-Rot-Regel nicht zu verletzen.
					x = x.p; // Betrachte nun den Elternknoten von x.
				} else {
					//3.Fall: x's Bruder ist schwarz, linkes Kind des Bruders ist rot, rechtes schwarz.
					//Der 3.Fall ist dazu da, um in den 4.Fall zu überführen.
					if(w.right.color == NodeColor.BLACK) {
						w.left.color = NodeColor.BLACK;
						w.color = NodeColor.RED;
						rotateRight(w);
						w = x.p.right;
					}
					//4.Fall: x's Bruder ist schwarz und das rechte Kind des Bruders ist rot. Hier soll das doppelte Schwarz von x entfernt werden,
					//um alle Eigenschaften des Rot-Schwarz-Baums zu erhalten.
					w.color = x.p.color;
					x.p.color = NodeColor.BLACK;
					w.right.color = NodeColor.BLACK;
					rotateLeft(x.p);
					//x ist nun nicht mehr doppelt schwarz.
					x = _root; //x wird zur Wurzel, die while-Schleife terminiert.
				}
			} 
			/**
			 * Hier geschieht alles analog zum vorherigen Block der if-else-Anweisung, nur mit left und right vertauscht.
			 */
			 else {
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
		//Färbe die Wurzel schwarz, damit Eigenschaft des Rot-Schwarz-Baums nicht verletzt wird, dass Knoten schwarz sein muss.
		x.color = NodeColor.BLACK;
	}
	
}
