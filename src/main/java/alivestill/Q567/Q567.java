package alivestill.Q567;

import java.util.HashMap;
import java.util.Map;

public class Q567 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(so.checkInclusion("ab", "eidboaoo"));
        System.out.println(so.checkInclusion("ab", "eidbaooo"));
        System.out.println(so.checkInclusion("aob", "aoddbodaob"));
    }
}

class Solution {
    /// @brief slide window plus hashmap
    public boolean checkInclusion(String s1, String s2) {
        int lens1 = s1.length();
        int lens2 = s2.length();
        if (lens1 > lens2) {
            return false;
        }
        Map<Character, Integer> required = new HashMap<>();
        Map<Character, Integer> current = new HashMap<>();
        for (int i = 0; i < lens1; ++ i) {
            required.put(s1.charAt(i), required.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int size = 0;
        for (int i = 0; i < lens2 && size < lens1; ++ i) {
            char ch = s2.charAt(i);
            int get = required.getOrDefault(ch, 0);
            int count = current.getOrDefault(ch, 0);
            if (count < get) {
                ++ size;
            }
            current.put(ch, count + 1);

            if (i >= lens1) {
                ch = s2.charAt(i - lens1);
                count = current.get(ch);    // never get null
                if (required.getOrDefault(ch, 0) >= count) {
                    -- size;
                }
                current.put(ch, count - 1);
            }
        }
        return size == lens1;
    }
}