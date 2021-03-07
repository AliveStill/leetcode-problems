package alivestill.Q468;

public class Q468 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(so.validIPAddress("172.16.254.1"));  // IPv4
        System.out.println(so.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334")); // IPv6
        System.out.println(so.validIPAddress("256.256.256.256"));   // Neither
        System.out.println(so.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));    // Neither
        System.out.println(so.validIPAddress("1e1.4.5.6")); // Neither
//        System.out.println(so.validIPAddress());
    }
}

class Solution {
    public String validIPAddress(String IP) {
        if (isValidIPv4(IP)) {
            return "IPv4";
        }
        if (isValidIPv6(IP)) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean isValidIPv4(String ip) {
        if (countOccurrence(ip, '.') != 4 - 1) {
            return false;
        }
        String[] chars = ip.split("\\.");
        if (chars.length != 4) {
            return false;
        }
        for (int i = 0; i < 4; ++ i) {
            int lens = chars[i].length();
            if (lens == 0 || lens > 3) {
                return false;
            }
            if (chars[i].charAt(0) == '0' && lens != 1) {
                return false;
            }
            for (int j = 0; j < lens; ++ j) {
                if (!Character.isDigit(chars[i].charAt(j))) {
                    return false;
                }
            }
            int digit = Integer.parseInt(chars[i]);
            if (digit > 255) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidIPv6(String ip) {
        if (countOccurrence(ip, ':') != 8 - 1) {
            return false;
        }
        String[] chars = ip.split(":");
        if (chars.length != 8) {
            return false;
        }
        for (int i = 0; i < 8; ++ i) {
            int lens = chars[i].length();
            if (lens < 1 || lens > 4) {
                return false;
            }
            for (int j = 0; j < lens; ++ j) {
                char ch = chars[i].charAt(j);
                if (Character.isDigit(ch) ||
                        ((Character.isLowerCase(ch)
                                || Character.isUpperCase(ch))
                                && Character.toLowerCase(ch) >= 'a'
                                && Character.toLowerCase(ch) <= 'f')) {
                    // pass
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private int countOccurrence(String str, char ch) {
        int lens = str.length();
        int occur = 0;
        for (int i = 0; i < lens; ++ i) {
            if (str.charAt(i) == ch) {
                ++ occur;
            }
        }
        return occur;
    }
}
