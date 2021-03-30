package alivestill.Q537;

public class Q537 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(so.complexNumberMultiply("1+1i", "1+1i"));
        System.out.println(so.complexNumberMultiply("1+-1i", "1+-1i"));
    }
}

class Solution {
    /// @brief naive
    public String complexNumberMultiply(String a, String b) {
        String[] sa = a.split("\\+");
        String[] sb = b.split("\\+");
        int realPartOfA = Integer.parseInt(sa[0]);
        int unrealPartOfA = Integer.parseInt(sa[1].split("i")[0]);
        int realPartOfB = Integer.parseInt(sb[0]);
        int unrealPartOfB = Integer.parseInt(sb[1].split("i")[0]);
        Integer realPart = realPartOfA * realPartOfB - unrealPartOfA * unrealPartOfB;
        Integer unrealPart = realPartOfA * unrealPartOfB + realPartOfB * unrealPartOfA;
        return realPart.toString() + "+" + unrealPart.toString() + "i";
    }
}
