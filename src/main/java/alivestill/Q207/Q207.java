package alivestill.Q207;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Q207 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(so.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(so.canFinish(2, new int[][]{{1, 0}}));
        System.out.println(so.canFinish(3, new int[][]{{1, 0}, {2, 0}}));

    }
}

class Solution {

    /// @brief check circle, topology sort
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 1) {
            // literally the ${prerequisites} array should be empty, however we check it to ensure
            return prerequisites.length == 0;
        }
        boolean[] visited = new boolean[numCourses];
        Graph graph = new Graph(numCourses);
        for (int[] ele : prerequisites) {
            graph.link(ele[1], ele[0]);
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < numCourses; ++ i) {
            if (graph.getInDegree(i) == 0) {
                stack.addLast(i);
            }
        }

        while (!stack.isEmpty()) {
            int index = stack.removeLast();
            if (visited[index]) {
                continue;
            }
            visited[index] = true;
            Integer[] neighbors = graph.getNeighbors(index).toArray(new Integer[0]);
            for (int toNodeIndex : neighbors) {
                if (graph.getInDegree(toNodeIndex) == 1) {
                    // remove neighbor relation
                    stack.addLast(toNodeIndex);
                }
                graph.unlink(index, toNodeIndex);
            }
        }

        for (boolean visit : visited) {
            if (!visit) {   // any node is not visited, circle exists
                return false;
            }
        }
        return true;
    }

    class Graph {
        class GraphNode {
            // store Integers(indexes) rather than the nodes themselves
            Set<Integer> neighbors = new HashSet<>();
            public void addNeighbor(int index) {
                neighbors.add(index);
            }
            public void removeNeighbor(int index) {
                neighbors.remove(index);
            }
            public Set<Integer> getNeighbors() {
                return neighbors;
            }
            public int getOutDegree() {
                return neighbors.size();
            }
        }

        private GraphNode[] graphNodes;
        private int[] inDegrees;
        private int count;

        public Graph(int count) {
            this.count = count;
            graphNodes = new GraphNode[count];
            inDegrees = new int[count];
            for (int i = 0; i < graphNodes.length; ++ i) {
                graphNodes[i] = new GraphNode();
            }
        }

        public void link(int fromNode, int toNode) {
            graphNodes[fromNode].addNeighbor(toNode);
            ++ inDegrees[toNode];
        }

        public void unlink(int fromNode, int toNode) {
            graphNodes[fromNode].removeNeighbor(toNode);
            -- inDegrees[toNode];
        }

        public GraphNode[] getGraphNodes() {
            return graphNodes;
        }

        public Set<Integer> getNeighbors(int fromNode) {
            return graphNodes[fromNode].getNeighbors();
        }

        public int getInDegree(int index) {
            return inDegrees[index];
        }
    }
}