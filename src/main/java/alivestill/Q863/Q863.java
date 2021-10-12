package alivestill.Q863;

import org.junit.jupiter.api.Test;
import alivestill.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q863 {

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n0.left = n1;
        n1.left = n3; n1.right = n2;
        Solution so = new Solution();
        List<Integer> arr = so.distanceK(n0, new TreeNode(2), 1);
        for (int ele : arr) {
            System.out.println(ele);
        }
    }

    @Test
    public void test1() {
        // [0,1,null,null,2,null,3,null,4]
        //3
        //0
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n0.left = n1;
        n1.right = n2;
        n2.right = n3;
        n3.right = n4;
        Solution so = new Solution();
        List<Integer> arr = so.distanceK(n0, new TreeNode(3), 0);
        for (int ele : arr) {
            System.out.println(ele);
        }
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    /**
     * dfs
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // locate the node and list of ancestors
        // find nodes upwards & downwards
        if (k == 0) {
            return new ArrayList<>(){{this.add(root.val);}};
        }
        this.targetKey = target.val;
        locate(root, new ArrayList<>());
        // then found
        dfs(this.targetNode, k, 0);    // downwards
        if (root == this.targetNode) {
            return ret;
        } else {
            list.add(targetNode);
            for (int i = list.size() - 2; i >= 0; -- i) {
                int directionAllowed;   // same as refer to direction in dfs
                // left child
                if (list.get(i).left == list.get(i + 1)) {
                    directionAllowed = 2;
                } else {
                    directionAllowed = 1;
                }
                int param;
                if ((param = k - (list.size() - i - 1)) < 0) {
                    break;
                }
                dfs(list.get(i), param, directionAllowed);
            }
        }
        return ret;
    }

    private List<TreeNode> list = new ArrayList<>();
    int targetKey;
    TreeNode targetNode;
    boolean found = false;
    private List<Integer> ret = new ArrayList<>();

    private void locate(TreeNode root, List<TreeNode> ancestors) {
        if (found) {
            return ;
        }
        if (root == null) {
            return ;
        }
        if (root.val == targetKey) {
            list.addAll(ancestors);
            targetNode = root;
            found = true;
            return;
        }
        ancestors.add(root);
        locate(root.left, ancestors);
        locate(root.right, ancestors);
        ancestors.remove(ancestors.size() - 1);
    }

    /**
     *
     * @param root
     * @param dist
     * @param direction indicate allowed direction,
     *                  direction = 1, left allowed,
     *                  direction = 2, right allowed,
     *                  direction = 0, both sides allowed
     */
    private void dfs(TreeNode root, int dist, int direction) {
        if (root == null) {
            return ;
        }
        if (dist == 0) {
            ret.add(root.val);
            return ;
        }
        if (direction != 2) {
            dfs(root.left, dist - 1, 0);
        }
        if (direction != 1) {
            dfs(root.right, dist - 1, 0);
        }
    }
}
