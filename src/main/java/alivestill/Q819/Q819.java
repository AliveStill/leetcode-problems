package alivestill.Q819;

import alivestill.utils.Pair.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Q819 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        String ret = so.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"});
        System.out.println(ret);
    }
}

class Solution {
    /**
     * naive
     * @param paragraph
     * @param banned
     * @return
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        List<String> strs = Arrays.stream(paragraph
                .split("[ !?',;.]"))
                .map((str) -> str.toLowerCase(Locale.ROOT))
                .filter((str) -> str.length() != 0)
                .collect(Collectors.toList());
        Set<String> bannedSet = new HashSet<>();
        bannedSet.addAll(Arrays
                .stream(banned)
                .collect(Collectors.toList()));
        Map<String, Integer> counter = new HashMap<>();
        for (String str : strs) {
            counter.put(str, counter.getOrDefault(str, 0) + 1);
        }
        List<Pair<String, Integer>> list = new ArrayList<>();
        for (String key : counter.keySet()) {
            list.add(new Pair<>(key, counter.get(key)));
        }
        list.sort((a, b) -> b.getSecond() - a.getSecond());
        for (Pair<String, Integer> entry : list) {
            if (!bannedSet.contains(entry.getFirst())) {
                return entry.getFirst();
            }
        }
        return "error";
    }
}
