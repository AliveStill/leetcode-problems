package alivestill.Q1797;

import alivestill.utils.Pair.Pair;

import java.util.*;

public class Q1797 {
}


class AuthenticationManager {

    // table
    Map<String, Pair<String, Integer>> map = new HashMap<>();

    // self organized heap structure
    PriorityQueue<Pair<String, Integer>> pQueue = new PriorityQueue<>(new Comparator<Pair<String, Integer>>() {
        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o1.getSecond() - o2.getSecond();
        }
    });

    int timeToLive;

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        if (map.containsKey(tokenId)) {
            doRenew(tokenId, currentTime);
        } else {
            Pair<String, Integer> pair = new Pair<>(tokenId, currentTime + timeToLive);
            map.put(tokenId, pair);
            pQueue.offer(pair);
        }
    }

    public void renew(String tokenId, int currentTime) {
        if (map.containsKey(tokenId) && currentTime < map.get(tokenId).getSecond()) {
            doRenew(tokenId, currentTime);
        }
    }

    // use it before check existence
    private void doRenew(String tokenId, int currentTime) {
        Pair<String, Integer> pair = map.get(tokenId);
        pQueue.remove(pair);
        pair.setSecond(currentTime + timeToLive);
        pQueue.offer(pair);
    }

    public int countUnexpiredTokens(int currentTime) {
        while (!pQueue.isEmpty() && currentTime >= pQueue.peek().getSecond()) {
            map.remove(pQueue.poll().getFirst());
        }
        return pQueue.size();
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */