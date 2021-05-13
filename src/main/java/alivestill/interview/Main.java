package alivestill.interview;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println("Hello World!");
        System.out.println(getMaxLenSubStr("abacdef"));
    }

    public boolean lucky(int number) {
        if (number <= 0) {
            return false;
        }
        if (number == 1) {
            return true;
        }
        int bit = number % 10;
        if (bit == 1) {
            return lucky((number - 1)/ 10);
        } else if (bit == 4) {
            if (number == 14) {
                return true;
            } else if (number == 144) {
                return true;
            }
            if (number < 14) {
                return false;
            }
            int twoBit = number % 100;
            boolean ret = false;
            if (twoBit == 14) {
                ret =  lucky((number - 14)/ 100);
            }
            boolean res = false;
            if (number < 144) {
                res = false;
            } else {
                res = lucky((number - 144)/1000);
            }
            return ret || res;
        }
        return false;
    }

    /// @brief brute-force
    public void lucky(int number, List<Integer> list) {
        for (int i = 1; i < number; ++ i) {
            if (lucky(number)) {
                list.add(number);
            }
        }
    }

    public static int getMaxLenSubStr(final String str) {
        // slide window
        Set<Character> set = new HashSet<>();
        int lens = str.length();
        int pos = 0;
        int ret = 0;
        while (pos < lens) {
            int currentPos = pos;
            while (pos < lens && (set.size() == 4 && set.contains(str.charAt(pos))
                    || (set.size() <= 3 ))) {
                set.add(str.charAt(pos));
                ++ pos;
            }
            // TODO
            ret = Math.max(ret, pos - currentPos);
            set.remove(str.charAt(currentPos));
        }
        return ret;
    }

    public void lucky2(int number, List<Integer> list) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(14);
        set.add(144);
        int count = 10000; // 10 thousand
        while (count-- != 0) {
            int num = set.first();
            set.remove(num);
            if (num >= number) {
                break;
            }
            list.add(num);
            set.add(num * 10 + 1);
            set.add(num * 100 + 14);
            set.add(num * 1000 + 144);
            /// list.add(1 , num);
            /// list.add(14, num);
            /// list.add(144, num);
        }
    }

    private int getBit(int number) {
        int base;
        return 0;
    }
}

