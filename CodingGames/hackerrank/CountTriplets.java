
/*
* @source https://www.hackerrank.com/challenges/count-triplets-1/problem
* @author xiaoque55
* @date 2021.06.11
*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountTriplets {

    static long countTriplets(List<Long> arr, long r) {
        // the count in the map should also be Long
        Map<Long, Long> midMap = new HashMap<>();
        Map<Long, Long> lastMap = new HashMap<>();
        long count = 0;
        for (Long num : arr) {
            if (lastMap.containsKey(num))
                count += lastMap.get(num);

            // when midMap has $num, it means we already have a pair (num/r, num), then add
            // the 3rd num*r to lastMap
            if (midMap.containsKey(num))
                lastMap.put(num * r, lastMap.getOrDefault(num * r, 0L) + midMap.get(num));

            // consider every num as the first in the triplet, and add num*r to midMap
            midMap.put(num * r, midMap.getOrDefault(num * r, 0L) + 1);
        }
        return count;
    }
}
