package alivestill.Q355;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Q355 {

    public static void main(String[] args) {
        // ["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed","unfollow","getNewsFeed"]
        // [[],[1,5],[1],[1,2],[2,6],[1],[1,2],[1]]
        // expected:
        // [null,null,[5],null,null,[6,5],null,[5]]
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }

    @Test
    public void testcase1() {
        // ["Twitter","postTweet","postTweet","getNewsFeed"]
        // [[],[1,5],[1,3],[1]]
        // expected:
        // [3,5]
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        System.out.println(twitter.getNewsFeed(1)); // [3, 5]
    }

    @Test
    public void makeup() {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 2);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 4);
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 6);
        twitter.postTweet(1, 7);
        twitter.postTweet(1, 8);
        twitter.postTweet(1, 9);
        twitter.postTweet(1, 10);
        twitter.postTweet(1, 11);
        twitter.postTweet(1, 12);
        twitter.postTweet(1, 13);
        System.out.println(twitter.getNewsFeed(1));
    }

    @Test
    public void testcase2() {
        // ["Twitter","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","getNewsFeed","follow","getNewsFeed","unfollow","getNewsFeed"]
        // [[],[1,5],[2,3],[1,101],[2,13],[2,10],[1,2],[1,94],[2,505],[1,333],[2,22],[1,11],[1,205],[2,203],[1,201],[2,213],[1,200],[2,202],[1,204],[2,208],[2,233],[1,222],[2,211],[1],[1,2],[1],[1,2],[1]]
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.postTweet(2, 3);
        twitter.postTweet(1, 101);
        twitter.postTweet(2, 13);
        twitter.postTweet(2, 10);
        twitter.postTweet(1, 2);
        twitter.postTweet(1, 94);
        twitter.postTweet(2, 505);
        twitter.postTweet(1, 333);
        twitter.postTweet(2, 22);
        twitter.postTweet(1, 11);
        twitter.postTweet(1, 205);
        twitter.postTweet(2, 203);
        twitter.postTweet(1, 201);
        twitter.postTweet(2, 213);
        twitter.postTweet(1, 200);
        twitter.postTweet(2, 202);
        twitter.postTweet(1, 204);
        twitter.postTweet(2, 208);
        twitter.postTweet(2, 233);
        twitter.postTweet(1, 222);
        twitter.postTweet(2, 211);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));

    }
}

// merge
class Twitter {

    class Data {
        Integer tweetId;
        Long timeStamp;

        public Data(Integer tweetId, Long timeStamp) {
            this.tweetId = tweetId;
            this.timeStamp = timeStamp;
        }

        public Integer getTweetId() {
            return tweetId;
        }

        public void setTweetId(Integer tweetId) {
            this.tweetId = tweetId;
        }

        public Long getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(Long timeStamp) {
            this.timeStamp = timeStamp;
        }
    }

    private Map<Integer, Set<Integer>> friendRelation = new HashMap<>();
    private Map<Integer, List<Data>>  userNewsTweet = new HashMap<>();   // should add a time stamp
    AtomicLong counter = new AtomicLong();

    /** Initialize your data structure here. */
    public Twitter() {
        // left empty
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        List<Data> list = userNewsTweet.get(userId);
        if (list == null) {
            list = new ArrayList<>();
            list.add(new Data(tweetId, counter.incrementAndGet()));
            userNewsTweet.put(userId, list);
        } else {
            list.add(new Data(tweetId, counter.incrementAndGet()));
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        // merge sort
        Set<Integer> friendList = (friendRelation.get(userId) == null) ?
                new HashSet<>() : friendRelation.get(userId);
        List<List<Data>> list = new ArrayList<>();
        // add tweet of himself
        if (userNewsTweet.get(userId) != null) {
            list.add(userNewsTweet.get(userId));
        }
        // add tweet of followers
        for (Integer ele : friendList) {
            if (userNewsTweet.get(ele) != null) {
                list.add(userNewsTweet.get(ele));
            }
        }
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        // sentinel
        List<Data> base = new ArrayList<>() {{
            Data data = new Data(-1, Long.MAX_VALUE);
            this.add(data);
        }};
        for (List<Data> ld : list) {
            Deque<Data> merge = new ArrayDeque<>();
            int pos1 = base.size() - 1, pos2 = ld.size() - 1;
            while (pos1 >= 0 && pos2 >= 0 && merge.size() <= 11) {
                if (base.get(pos1).getTimeStamp() > ld.get(pos2).getTimeStamp()) {
                    merge.addFirst(base.get(pos1));
                    --pos1;
                } else {
                    merge.addFirst(ld.get(pos2));
                    --pos2;
                }
            }
            while (pos1 >= 0 && merge.size() <= 10) {
                merge.addFirst(base.get(pos1));
                --pos1;
            }
            while (pos2 >= 0 && merge.size() <= 10) {
                merge.addFirst(ld.get(pos2));
                --pos2;
            }
            base.clear();
            base.addAll(merge);
            // at most 12 elements
            if (merge.size() > 11) {
                // FIXME, use list.subList(begin, end).clear(), begin inclusive, end exclusive
                base.subList(0, 1).clear();
            }
        }
        // descending order, however sort failed
        Collections.sort(base, new Comparator<Data>() {
            @Override
            /*  FIXED, pay attention to the type conversion */
            public int compare(Data o1, Data o2) {
                if (o1.getTimeStamp().equals(o2.getTimeStamp())) {
                    return 0;
                } else if (o1.getTimeStamp() < o2.getTimeStamp()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        List<Integer> ret = new ArrayList<>();
        for (int i = 1; i < base.size() && i <= 10; ++ i) {
            ret.add(base.get(i).getTweetId());
        }
        return ret;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return ;
        }
        Set<Integer> set = friendRelation.get(followerId);
        if (set == null) {
            set = new HashSet<>();
            set.add(followeeId);
            friendRelation.put(followerId, set);
        } else {
            set.add(followeeId);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (friendRelation.get(followerId) != null) {
            friendRelation.get(followerId).remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

// this problem is frustrating