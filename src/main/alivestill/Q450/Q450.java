package alivestill.Q450;

public class Q450 {
}


//   Definition for a binary tree node.
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        TreeNode  parent = new TreeNode(-1);    // sentinel
         parent.left =  parent.right = root;
        // find it, than delete it
        TreeNode node = root;
        while (node != null) {
            if (key == node.val) {
                break;
            } else if (key > node.val) {
                 parent = root;
                 node = node.right;
            } else {
                parent = root;
                node = node.left;
            }
        }
        if (node != null) {
            removeNode(node, parent);
        }
        return root;
    }

    private void removeNode(TreeNode root, TreeNode  parent) {
        if (root.left == null) {
            if (parent.left == root) {
                parent.left = root.right;
                return ;
            } else {
                parent.right = root.right;
                return ;
            }
        } else if (root.right == null) {
            if (parent.left == root) {
                parent.left = root.left;
                return ;
            } else {
                parent.right = root.left;
                return ;
            }
        } else {
            if (root.right.left == null) {
                if (parent.left == root) {
                    parent.left = root.right;
                } else {
                    parent.right = root.right;
                }
                root.right.left = root.left;
            } else {
                TreeNode rightSon = root.right;
                TreeNode replace = rightSon.left;
                TreeNode rp = rightSon;
                while (replace.left != null) {
                    rp = replace;
                    replace = replace.left;
                }
                rp.left = replace.right;
                if (root == parent.left) {
                    parent.left = replace;
                } else {
                    parent.right = replace;
                }
                replace.left = root.left;
                replace.right = root.right;
            }
        }
    }
}