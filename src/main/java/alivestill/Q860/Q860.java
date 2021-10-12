package alivestill.Q860;

public class Q860 {
}

class Solution {
    /**
     * naive logic
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int co5 = 0;    // count of 5
        int co10 = 0;   // count of 10
        for (int bill : bills) {
            switch (bill) {
                case 5: ++co5; break;
                case 10:  {
                    if (co5 == 0) {
                        return false;
                    } else {
                        -- co5;
                        ++ co10;
                    }
                } break;
                case 20: {
                    if (co10 > 0) {
                        -- co10;
                        if (co5 == 0) {
                            return false;
                        } else {
                            -- co5;
                        }
                    } else {
                        if (co5 < 3) {
                            return false;
                        } else {
                            co5 -= 3;
                        }
                    }
                } break;
            }
        }
        return true;
    }
}
