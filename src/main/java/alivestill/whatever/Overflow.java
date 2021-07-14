package alivestill.whatever;

public class Overflow {

    public static void main(String[] args) {
        long base = Integer.MAX_VALUE;
        System.out.println(((Long)base << 32) + Integer.MAX_VALUE); // doesn't overflow
    }
}
