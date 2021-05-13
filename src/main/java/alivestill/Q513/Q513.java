package alivestill.Q513;

public class Q513 {
}



// Definition for a binary tree node.
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
}


class Solution {
    /// @brief dfs
    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return bottomLeft;
    }

    int bottomLeft = 0; // default value, never returned before change
    int maxDepth = -1;

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return ;
        }
        if (depth > maxDepth) {
            bottomLeft = root.val;
            maxDepth = depth;
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}