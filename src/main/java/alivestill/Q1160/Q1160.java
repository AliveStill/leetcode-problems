package alivestill.Q1160;

import java.util.HashMap;
import java.util.Map;

public class Q1160 {
}

class Solution {
    /**
     * naive
     * @param words
     * @param chars
     * @return
     */
    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length(); ++ i) {
            map.put(chars.charAt(i), map.getOrDefault(chars.charAt(i), 0) + 1);
        }
        int ret = 0;
        label:
        for (String str : words) {
            Map<Character, Integer> container = new HashMap<>();
            for (int i = 0; i < str.length(); ++ i) {
                char ch = str.charAt(i);
                if (!map.containsKey(ch) || container.getOrDefault(ch, 0) + 1 > map.get(ch)) {
                    continue label;
                }
                container.put(ch, container.getOrDefault(ch, 0) + 1);
            }
            ret += str.length();
        }
        return ret;
    }
}
