package alivestill.Q865;

import alivestill.utils.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class Q865 {
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // mapping of child to parent
    Map<TreeNode, TreeNode> map = new HashMap<>();

    /**
     * bfs
     * @param root
     * @return
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        collect(root, null);
        // bfs to get all deepest node
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<TreeNode> aux = new ArrayDeque<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            Iterator<TreeNode> iter = stack.iterator();
            while (iter.hasNext()) {
                TreeNode node = iter.next();
                if (node.left != null) {
                    aux.addLast(node.left);
                }
                if (node.right != null) {
                    aux.addLast(node.right);
                }
            }
            if (aux.isEmpty()) {
                break;
            }
            stack = aux;
            aux = new ArrayDeque<>();
        }
        // the remaining node in the stack is the deepest node
        List<TreeNode> list = stack.stream().collect(Collectors.toList());
        List<TreeNode> al = collectAncestors(list.get(0));
        TreeNode common = al.get(0);
        OUTER:
        for (int i = 1; i < list.size(); ++ i) {
            TreeNode node = list.get(i);
            // find the lowest common ancestor of ans & node
            List<TreeNode> al2 = collectAncestors(node);
            int pos1 = al.size() - 1, pos2 = al2.size() - 1;
            // at least execute one round, due to the tree root
            while (pos1 >= 0 && pos2 >= 0 /* && al.get(pos1) != common */
                    && al.get(pos1) == al2.get(pos2)) {
                if (al.get(pos1) == common) {
                    // accelerate
                    continue OUTER;
                }
                -- pos1; -- pos2;
            }
            common = al.get(pos1 + 1);
        }
        return common;
    }

    private void collect(TreeNode node, TreeNode parent) {
        if (node == null) {
            return ;
        }
        map.put(node, parent);
        collect(node.left, node);
        collect(node.right, node);
    }

    /**
     * collect ancestors, self included
     * @param node
     * @return
     */
    private List<TreeNode> collectAncestors(TreeNode node) {
        List<TreeNode> list = new ArrayList<>();
        while (node != null) {
            list.add(node);
            node = map.get(node);
        }
        return list;
    }
}
