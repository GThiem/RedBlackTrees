package frame;

public class TreeNode {
	public enum NodeColor { RED, BLACK };
	
	public TreeNode left;
	public TreeNode right;
	public TreeNode p;
	public int key;
	public Process value;
	public NodeColor color;
	
	public TreeNode(TreeNode left, TreeNode right, TreeNode p, int key, NodeColor color, Process value) {
		this.left = left;
		this.right = right;
		this.p = p;
		this.key = key;
		this.color = color;
		this.value = value;
	}
	
	public TreeNode() {
		left = null;
		right = null;
		p = null;
		key = 0;
		color = NodeColor.BLACK;
	}
}
