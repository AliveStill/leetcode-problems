package alivestill.Q393;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Q393 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(so.validUtf8(new int[] { 197, 130, 1 }));
        System.out.println(so.validUtf8(new int[] {235, 140, 4}));
        System.out.println(so.validUtf8(new int[] {145}));
    }

    @Test
    @DisplayName("test1")
    public void test1() {
        System.out.println(Integer.toBinaryString(145));
    }

    @Test
    @DisplayName("test2")
    public void test2() {
        int [] array = new int[] {
                250,145,145,145,145
        };
        for (Integer ele : array) {
            System.out.print(Integer.toBinaryString(ele) + ",");
        }
        System.out.println(so.validUtf8(array));
    }
}

class Solution {
    public boolean validUtf8(int[] data) {
        int lens = data.length;
        int pos = 0;
        while (pos < lens) {
            // 1 byte character
            if ((data[pos] & 0x80) == 0) {
                ++ pos;
            } else {    // multiple bytes
                if ((data[pos] & 0xff) == 0xff) {    // b(1111 1111) never occur
                    return false;
                }
                int count = 0;
                int num = data[pos];
                while ((num & 0x80) == 0x80) {
                    ++ count;
                    num <<= 1;
                }
                if (count == 1 || count > 4) {   // at least 2 bits, at most 4 bits
                    return false;
                }
                if (count + pos > lens) {
                    return false;
                }
                // check prefix 01
                for (int i = pos + 1; i < pos + count; ++ i) {
                    if ((data[i] & 0xc0) != 0x80) {
                        return false;
                    }
                }
                pos += count;
            }
        }
        return true;
    }
}
