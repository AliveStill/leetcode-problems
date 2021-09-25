package alivestill.Q814;

public class Q814 {
}

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}



class Solution {
    /**
     * naive
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        return prune(root) ? root : null;
    }

    /**
     * @param root
     * @return true if there is any subtree(including self) containing 1
     */
    private boolean prune(TreeNode root) {
        if (root == null) {
            return false;
        }
        boolean ret = false;
        if (root.val == 1) {
            return true;
        }
        boolean leftContaining1 = prune(root.left);
        boolean rightContaining1 = prune(root.right);
        if (!leftContaining1) {
            root.left = null;
        }
        if (!rightContaining1) {
            root.right = null;
        }
        ret |= leftContaining1;
        ret |= rightContaining1;
        return ret;
    }
}