package alivestill.Q720;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Q720 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        String[] strs = new String[] {
                "a","banana","app","appl","ap","apply","apple"
        };
        System.out.println(so.longestWord(strs));
        strs = new String[] {
                "wo","wor","worl","world"
        };
        System.out.println(so.longestWord(strs));
    }
}

class Solution {
    /**
     * naive
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        if (words.length == 0) {
            return "";
        }
        Arrays.sort(words, (word1, word2) -> {
            if (word1.length() > word2.length()) {
                return 1;
            } else if (word1.length() < word2.length()) {
                return -1;
            } else {
                return word1.compareTo(word2);
            }
        });
        Set<String> valid = new HashSet<>();
        int maxLens = 0;
        String ret = "";
        for (String word : words) {
            if (word.length() == 1) {
                valid.add(word);
            } else {
                if (valid.contains(word.substring(0, word.length() - 1))) {
                    valid.add(word);
                    if (maxLens < word.length()) {
                        maxLens = word.length();
                        ret = word;
                    }
                }
            }
        }
        return ret;
    }
}