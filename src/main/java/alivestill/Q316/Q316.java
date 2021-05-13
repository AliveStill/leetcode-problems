package alivestill.Q316;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.*;

public class Q316 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(so.removeDuplicateLetters("cbcbacbcb"));
        System.out.println(so.removeDuplicateLetters("bcabc")); // test case 1
    }

    @Test(timeout = 200)    /* 200ms test passed */
    @DisplayName("pressure test, length = 10000")
    public void test1() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int TOTAL_COUNT = 10000;
        for (int i = 0; i < TOTAL_COUNT; ++ i) {
            sb.append((char)(random.nextInt(3) + 'a'));
        }
        System.out.println(so.removeDuplicateLetters(sb.toString()));
    }
}

class Solution {
    /// FIXME, wrong answer, see test case 1
    /// @brief slide window
    public String removeDuplicateLetters(String s) {
        int lens = s.length();
        if (lens == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < lens; ++ i) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), sb.length());
                sb.append(s.charAt(i));
            } else {
                StringBuilder newSb = new StringBuilder(sb.toString());
                int index = map.get(s.charAt(i));
                for (Character key : map.keySet()) {
                    Integer value = map.get(key);
                    if (value > index) {
                        newSb.setCharAt(value - 1, key);
                    } else if (value < index) {
                        newSb.setCharAt(value, key);
                    }
                }
                newSb.setCharAt(newSb.length() - 1, s.charAt(i));
                if (sb.compareTo(newSb) >= 0) {
                    sb = newSb;
                    map = new HashMap<Character, Integer>() {
                        {
                            for (int i = 0; i < newSb.length(); ++i) {
                                this.put(newSb.charAt(i), i);
                            }
                        }
                    };
                }
            }
        }
        return sb.toString();
    }
}
