package alivestill.Q811;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q811 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        for (String str : so.subdomainVisits(new String[]{
                "9001 holy.shit.com", "1 shit.com"})) {
            System.out.println(str);
        }
    }
}


class Solution {
    /**
     * naive
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String domain : cpdomains) {
            String[] strs = domain.split(" ");
            int count = Integer.parseInt(strs[0]);
            String str = strs[1];
            int index = -1;
            do {
                str = str.substring(index + 1);
                map.put(str, map.getOrDefault(str, 0) + count);
            } while ((index = str.indexOf('.')) != -1);
        }
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            list.add(String.valueOf(map.get(key)) + " " + key);
        }
        return list;
    }
}