package alivestill.Q306;

import java.math.BigInteger;

public class Q306 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(so.isAdditiveNumber("11238"));
        System.out.println(so.isAdditiveNumber("01010"));
        System.out.println(so.isAdditiveNumber("112358"));
        System.out.println(so.isAdditiveNumber("199100199"));
    }
}

/// FIXMED, can not handle overflow of very large numbers, see newer version
class SolutionV1 {
    /// @brief recur add
    public boolean isAdditiveNumber(String num) {
        int lens = num.length();
        if (lens < 3) {
            return false;
        }
        boolean leadingZero = false;
        long num1 = 0;
        int pos = 0;
        if (num.charAt(0) == '0') {
            leadingZero = true;
        }
        do {
            num1 += charToInt(num.charAt(pos));
            int pos2 = ++ pos;
            boolean leadingZero2 = false;
            if (pos2 == lens) {
                break;
            }
            long num2 = 0;
            if (num.charAt(pos2) == '0') {
                leadingZero2 = true;
            }
            do {
                num2 += charToInt(num.charAt(pos2));
                if (num1 + num2 > Integer.MAX_VALUE) {
                    break;
                }
                if (recurAddValid(num, pos2 + 1, (int)(num1 + num2), (int)num2)) {
                    return true;
                }
                num2 *= 10;
                ++ pos2;
            } while(!leadingZero2 && num2 <= Integer.MAX_VALUE && pos2 != lens - 1);
            num1 *= 10;
        } while (!leadingZero && pos != lens - 2 && num1 <= Integer.MAX_VALUE);
        return false;
    }

    /// @brief check if sum1 + sum2 = any num on head of
    ///     str and str fulfill the requirements of
    ///     isAddictiveNumber() and num is the first number
    ///     of the sequence
    private boolean recurAddValid(String str, int pos, int checkSum, int preNum) {
        if (pos == str.length()) {
            return true;
        }
        // TODONE, check leading zero
        if (str.charAt(pos) == '0') {
            if (checkSum == 0) {
                return recurAddValid(str, pos + 1, 0, 0);
            } else {
                return false;
            }
        }
        int lens = 0;
        int cp = checkSum;
        while (cp != 0) {
            cp /= 10;
            ++ lens;
        }
        if (lens > str.length() - pos) {
            return false;
        }
        if (!str.substring(pos, pos + lens).equals(String.valueOf(checkSum))) {
            return false;
        }
        return recurAddValid(str, pos + lens, preNum + checkSum, checkSum);
    }

    public static int charToInt(Character ch) {
        return (int)ch & 0xf;
    }
}

class Solution {
    /// @brief recur add
    public boolean isAdditiveNumber(String num) {
        int lens = num.length();
        if (lens < 3) {
            return false;
        }
        boolean leadingZero = false;
        BigInteger num1 = BigInteger.valueOf(0L);
        int pos = 0;
        if (num.charAt(0) == '0') {
            leadingZero = true;
        }
        do {
            num1 = num1.add(BigInteger.valueOf(charToInt(num.charAt(pos))));
            int pos2 = ++ pos;
            boolean leadingZero2 = false;
            if (pos2 == lens) {
                break;
            }
            BigInteger num2 = BigInteger.valueOf(0L);
            if (num.charAt(pos2) == '0') {
                leadingZero2 = true;
            }
            do {
                num2 = num2.add(BigInteger.valueOf(charToInt(num.charAt(pos2))));
                if (recurAddValid(num, pos2 + 1, num1.add(num2), num2)) {
                    return true;
                }
                num2 = num2.multiply(BigInteger.valueOf(10L));
                ++ pos2;
            } while(!leadingZero2 && pos2 != lens - 1);
            num1 = num1.multiply(BigInteger.valueOf(10L));
        } while (!leadingZero && pos != lens - 2);
        return false;
    }

    /// @brief check if sum1 + sum2 = any num on head of
    ///     str and str fulfill the requirements of
    ///     isAddictiveNumber() and num is the first number
    ///     of the sequence
    private boolean recurAddValid(String str, int pos, BigInteger checkSum, BigInteger preNum) {
        if (pos == str.length()) {
            return true;
        }
        // TODONE, check leading zero
        if (str.charAt(pos) == '0') {
            if (checkSum.equals(BigInteger.valueOf(0L))) {
                return recurAddValid(str, pos + 1, BigInteger.valueOf(0L), BigInteger.valueOf(0L));
            } else {
                return false;
            }
        }
        int lens = 0;
        BigInteger cp = new BigInteger(checkSum.toByteArray());
        while (!cp.equals(BigInteger.valueOf(0L))) {
            cp = cp.divide(BigInteger.valueOf(10L));
            ++ lens;
        }
        if (lens > str.length() - pos) {
            return false;
        }
        if (!str.substring(pos, pos + lens).equals(String.valueOf(checkSum))) {
            return false;
        }
        return recurAddValid(str, pos + lens, preNum.add(checkSum), checkSum);
    }

    public static int charToInt(Character ch) {
        return (int)ch & 0xf;
    }
}
