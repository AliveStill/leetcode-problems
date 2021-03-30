package alivestill.Q508;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q508 {
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
    /// @brief naive
    public int[] findFrequentTreeSum(TreeNode root) {
        // post order traversal
        postOrder(root);
        int maxFrequency = 0;
        List<Integer> list = new ArrayList<>();
        for (Integer key : map.keySet()) {
            int value = map.get(key);
            if (value > maxFrequency) {
                list.clear();
                list.add(key);
                maxFrequency = value;
            } else if (value == maxFrequency) {
                list.add(key);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    private Map<Integer, Integer> map = new HashMap<>();

    private int postOrder(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = postOrder(root.left) + postOrder(root.right) + root.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }
}