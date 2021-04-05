package alivestill.Q1600;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q1600 {
}

class ThroneInheritance {

    class TreeNode {
        String name = "";
        boolean alive = true;
        List<TreeNode> children = new ArrayList<>();

        public TreeNode(String name) {
            this.name = name;
        }

        public void addChild(TreeNode node) {
            children.add(node);
        }

        public void die() {
            setAlive(false);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isAlive() {
            return alive;
        }

        public void setAlive(boolean alive) {
            this.alive = alive;
        }

        public List<TreeNode> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNode> children) {
            this.children = children;
        }
    }

    Map<String, TreeNode> familyMap = new HashMap<>();

    TreeNode ancestor;

    public ThroneInheritance(String kingName) {
        ancestor = new TreeNode(kingName);
        familyMap.put(kingName, ancestor);
    }

    public void birth(String parentName, String childName) {
        TreeNode child = new TreeNode(childName);
        familyMap.put(childName, child);
        familyMap.get(parentName).addChild(child);
    }

    public void death(String name) {
        familyMap.get(name).die();
    }

    public List<String> getInheritanceOrder() {
        List<String> list = new ArrayList<>();
        dfs(ancestor, list);
        return list;
    }

    private void dfs(TreeNode node, List<String> list) {
        if (node == null) {
            return ;
        }
        if (node.isAlive()) {
            list.add(node.getName());
        }
        for (TreeNode child : node.getChildren()) {
            dfs(child, list);
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */
