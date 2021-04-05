package alivestill.Q1472;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q1472 {
}

// two stacks
class BrowserHistory {

    // store history
    Deque<String> history = new ArrayDeque<>();

    // store forward history
    Deque<String> forward = new ArrayDeque<>();

    // current url
    String current;

    public BrowserHistory(String homepage) {
        current = homepage;
    }

    public void visit(String url) {
        history.addLast(current);
        current = url;
        forward.clear();
    }

    public String back(int steps) {
        while (!history.isEmpty() && steps-- != 0) {
            forward.addLast(current);
            current = history.removeLast();
        }
        return current;
    }

    public String forward(int steps) {
        while (!forward.isEmpty() && steps-- != 0) {
            history.addLast(current);
            current = forward.removeLast();
        }
        return current;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
