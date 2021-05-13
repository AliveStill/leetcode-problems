package alivestill.Q210;

import java.util.*;

public class Q210 {
}


class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 1) {
            // literally the ${prerequisites} array should be empty, however we check it anyway
            if (0 == prerequisites.length) {
                return new int[0];
            } else {
                return new int[]{0};
            }
        }
        boolean[] visited = new boolean[numCourses];
        int[] ret = new int[numCourses];
        int cnt = 0;
        Graph graph = new Graph(numCourses);
        for (int[] ele : prerequisites) {
            graph.link(ele[1], ele[0]);
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < numCourses; ++i) {
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
            ret[cnt] = index;
            ++ cnt;
            Integer[] neighbors = graph.getNeighbors(index).toArray(new Integer[0]);
            for (int toNodeIndex : neighbors) {
                if (graph.getInDegree(toNodeIndex) == 1) {
                    // remove neighbor relation
                    stack.addLast(toNodeIndex);
                }
                graph.unlink(index, toNodeIndex);
            }
        }
        return (cnt == numCourses) ? ret : new int[0];
    }
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

    private Graph.GraphNode[] graphNodes;
    private int[] inDegrees;
    private int count;

    public Graph(int count) {
        this.count = count;
        graphNodes = new Graph.GraphNode[count];
        inDegrees = new int[count];
        for (int i = 0; i < graphNodes.length; ++ i) {
            graphNodes[i] = new Graph.GraphNode();
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

    public Graph.GraphNode[] getGraphNodes() {
        return graphNodes;
    }

    public Set<Integer> getNeighbors(int fromNode) {
        return graphNodes[fromNode].getNeighbors();
    }

    public int getInDegree(int index) {
        return inDegrees[index];
    }
}