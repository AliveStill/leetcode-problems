package alivestill.Q284;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Q284 {

    public static PeekingIterator peekingIterator;

    public static void main(String[] args) {
        // test case 1:
        // ["PeekingIterator","next","peek","next","next","hasNext"]
        List<Integer> list = Arrays.asList(new Integer[]{1, 2, 3});
//        Iterator<Integer> iterator = Arrays.asList(new Integer[]{1, 2, 3}).iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        for (int i = 0; i < 3; ++ i) {
//            System.out.println(iterator.next());
//        }

        peekingIterator = new PeekingIterator(list.iterator());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.peek());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.hasNext());
    }
}

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html


/// @brief cache the result of next() for next provocation of next() or peek
///     additional flags are needed
/// @details consider the following provoke pattern:
///     1. continuous provocation of next()
///     2. continuous provocation of peek()
///     3. twisted provocation of next() and peek()
class PeekingIterator implements Iterator<Integer> {

    Iterator<Integer> iterator;
    Integer storedData;      // old value
    boolean already;         // if cached a value

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        already = false;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!already) {
            storedData = iterator.next();
            already = true;
        }
        return storedData;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (!already) {
            storedData = iterator.next();
        }
        already = false;
        return storedData;
    }

    @Override
    public boolean hasNext() {
        return already || iterator.hasNext();
    }
}
