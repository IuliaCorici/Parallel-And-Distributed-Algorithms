package parallelTree;

public class TreeNode {
	int name;
	public TreeNode left = null;
	public TreeNode right = null;
	public Integer lock;

	public TreeNode(int name) {
		this.name = name;
		lock = 2;
	}

	public void addChild(TreeNode child) {
		synchronized (lock) {
			if (left == null) {
				left = child;
				return;
			}
			right = child;
		}
	}

	public TreeNode getNode(int name) {
	synchronized (lock) {
			TreeNode aux = null;

			if (this.name == name) {
				return this;
			}

			if (left != null) {
				aux = left.getNode(name);
			}

			if (aux != null) {
				return aux;
			}

			if (right != null) {
				aux = right.getNode(name);
			}

			return aux;
		}
	}
}
