package alivestill.Q1154;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Q1154 {

    public static final Solution so = new Solution();
    public static void main(String[] args) {
        int ret = new Solution().dayOfYear("2019-01-09");
        assertEquals(9, ret);
        assertEquals(41, so.dayOfYear("2019-02-10"));
        assertEquals(61, so.dayOfYear("2004-03-01"));
    }
}

class Solution {
    public final int[] CommonYearDaysOfMonths  = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public final int[] LeapYearDaysOfMonths = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int dayOfYear(String date) {
        String[] strs = date.split("-");
        int year = Integer.parseInt(strs[0]);
        int month = Integer.parseInt(strs[1]);
        int day = Integer.parseInt(strs[2]);
        int[] ref = CommonYearDaysOfMonths;
        if (isLeapYear(year)) {
            ref = LeapYearDaysOfMonths;
        }
        int ret = 0;
        for (int i = 0; i < month - 1; ++ i) {
            ret += ref[i];
        }
        return ret + day;
    }

    private boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }
}
