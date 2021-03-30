package alivestill.Q515;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q515 {
}


//Definition for a binary tree node.
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
    /// @brief hashmap
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        dfs(root, 0);
        int depth = 0;
        List<Integer> list = new ArrayList<>();
        while (map.get(depth) != null) {
            list.add(map.get(depth));
            ++ depth;
        }
        return list;
    }

    Map<Integer, Integer> map = new HashMap<>();

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return ;
        }
        if (map.getOrDefault(depth, Integer.MIN_VALUE) <= root.val) {
            map.put(depth, root.val);
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}
