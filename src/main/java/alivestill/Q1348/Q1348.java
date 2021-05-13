package alivestill.Q1348;

import java.util.*;

public class Q1348 {
}


// naive
class TweetCounts {

    Map<String, List<Integer>> map = new HashMap<>();
    Map<String, Integer> incFactor = new HashMap<>() {{
        this.put("minute", 60);
        this.put("hour", 3600);
        this.put("day", 86400);
    }};

    public TweetCounts() {
        // left empty
    }

    public void recordTweet(String tweetName, int time) {
        if (map.containsKey(tweetName)) {
            List<Integer> list = map.get(tweetName);
            list.add(time);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(time);
            map.put(tweetName, list);
        }
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> ret = new ArrayList<>();
        List<Integer> list = map.containsKey(tweetName) ? map.get(tweetName) : new ArrayList<>();
        Collections.sort(list);     // ascending order
        int gap = incFactor.get(freq);
        int pos = 0;
        // seek first time point that's no smaller than startTime
        while (pos < list.size() && startTime > list.get(pos)) {
            ++ pos;
        }
        for (int time = startTime; time <= endTime; time += gap) {
            int nextTime = Math.min(time + gap, endTime + 1);   // because endTime is included
            int count = 0;
            while (pos < list.size() && nextTime > list.get(pos)) {
                ++ count;
                ++ pos;
            }
            ret.add(count);
        }
        return ret;
    }
}

/**
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts obj = new TweetCounts();
 * obj.recordTweet(tweetName,time);
 * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */
