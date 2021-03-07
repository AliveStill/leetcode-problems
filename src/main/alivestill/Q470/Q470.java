package alivestill.Q470;

import java.util.Random;

public class Q470 {
}

class SolBase {
    public int rand7() {
        Random random = new Random();
        return random.nextInt(7) + 1;
    }
}

class Solution extends SolBase {
    /// @brief sample rejection
    public int rand10() {
        int x, y;
        do {
            x = rand7();
            y = rand7();
        } while (x + 7 * y - 7 >= 41);
        return (x + 7 * y - 7) % 10 + 1;
    }
}

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
