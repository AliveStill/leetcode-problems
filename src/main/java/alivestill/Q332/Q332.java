package alivestill.Q332;

import java.util.*;

public class Q332 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>() {
            {
//                this.add(Arrays.asList(new String[]{"MUC", "LHR"}));
//                this.add(Arrays.asList(new String[]{"JFK", "MUC"}));
//                this.add(Arrays.asList(new String[]{"SFO", "SJC"}));
//                this.add(Arrays.asList(new String[]{"LHR", "SFO"}));
                  this.add(Arrays.asList(new String[]{"JFK", "ATL"}));
                  this.add(Arrays.asList(new String[]{"ATL", "JFK"}));

            }
        };
        for (String ele : so.findItinerary(list)) {
            System.out.println(ele);
        }
    }
}

class Solution {

    class Data implements Comparable{
        String str;
        boolean visited;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public Data(String str, boolean visited) {
            this.str = str;
            this.visited = visited;
        }

        @Override
        public int compareTo(Object o) {
            return this.getStr().compareTo(((Data)o).getStr());
        }
    }
    boolean find = false;
    List<String> list = null;
    HashMap<String, ArrayList<Data>> map = new HashMap<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ls : tickets) {
            if (!map.containsKey(ls.get(0))) {
                map.put(ls.get(0), new ArrayList<>());
            }
            map.get(ls.get(0)).add(new Data(ls.get(1), false));
        }
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        List<Data> intermediate = new ArrayList<>();
        dfs("JFK", tickets.size(), intermediate);
        return list;
    }

    /// @brief dfs
    private void dfs(String src, int count, List<Data> intermediate) {
        if (count == 0) {
            list = new ArrayList<>();
            for (Data data : intermediate) {
                list.add(data.getStr());
            }
            list.add(src);  // cyclic, add tail
            find = true;
            return ;
        }
        if (map.get(src) != null) { // in case destination
            for (Data data : map.get(src)) {
                if (find) {
                    break;
                }
                if (!data.isVisited()) {
                    data.setVisited(true);
                    intermediate.add(new Data(src, false));
                    dfs(data.getStr(), count - 1, intermediate);
                    intermediate.remove(intermediate.size() - 1);
                    data.setVisited(false);
                }
            }
        }
    }
}