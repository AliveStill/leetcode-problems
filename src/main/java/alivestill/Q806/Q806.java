package alivestill.Q806;

import java.util.HashSet;
import java.util.Set;

public class Q806 {
}

class Solution {

    private static String[] alphabet = new String[] {
            ".-","-...","-.-.","-..",".","..-.","--.",
            "....","..",".---","-.-",".-..","--","-.",
            "---",".--.","--.-",".-.","...","-","..-",
            "...-",".--","-..-","-.--","--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); ++ i) {
                sb.append(alphabet[(word.charAt(i) - 'a')]);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
