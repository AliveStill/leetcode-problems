package alivestill.Q841;

import java.util.List;

public class Q841 {
}

class Solution {
    boolean[] visited;
    int lens;
    int count = 0;

    /**
     * dfs
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        lens = rooms.size();
        visited = new boolean[lens];
        dfs(rooms, 0);
        return count == lens;
    }

    public void dfs(List<List<Integer>> rooms, int currentRoom) {
        if (visited[currentRoom]) {
            return ;
        }
        visited[currentRoom] = true;
        ++ count;
        for (int index : rooms.get(currentRoom)) {
            dfs(rooms, index);
        }
    }


}
