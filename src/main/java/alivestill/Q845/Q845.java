package alivestill.Q845;

public class Q845 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        int[] array = new int[] {
                2,1,4,7,3,2,5
        };  // 5
        System.out.println(so.longestMountain(array));
    }
}

class Solution {
    /**
     * logic
     * @param arr
     * @return
     */
    public int longestMountain(int[] arr) {
        int lens = arr.length;
        if (lens < 3) {
            return 0;
        }
        int ret = 0;
        int left = 0, top, cur;
        while (left < lens) {
            // upward edge
            cur = left + 1;
            while (cur < lens && arr[cur] > arr[cur - 1]) {
                ++ cur;
            }
            if (cur == lens) {
                break;
            }
            if (arr[cur] == arr[cur - 1]) {
                left = cur;
                continue;
            }
            if (cur == left + 1) {
                ++ left;
                continue;
            }


            top = cur;
            // downward edge
            while (top < lens && arr[top] < arr[top - 1]) {
                ++ top;
            }
            if (top == lens) {
                ret = Math.max(ret, lens - left);
                break;
            }
            if (arr[top] == arr[top - 1]) {
                ret = Math.max(ret, top - left);
                left = top;
            }
            // else
            ret = Math.max(ret, top - left);
            left = top - 1;
        }
        return ret;
    }
}
