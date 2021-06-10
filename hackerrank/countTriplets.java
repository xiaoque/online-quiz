import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/*
* @source https://www.hackerrank.com/challenges/count-triplets-1/problem
* @author xiaoque55
* @date 2021.06.11
*/
public class Solution {

    static long countTriplets(List<Long> arr, long r) {
    	// the count in the map should also be Long
        Map<Long, Long> midMap = new HashMap<>();
        Map<Long, Long> lastMap = new HashMap<>();
        long count = 0;
        for(Long num : arr){
            if(lastMap.containsKey(num))
                count += lastMap.get(num);

            // when midMap has $num, it means we already have a pair (num/r, num), then add the 3rd num*r to lastMap 
            if(midMap.containsKey(num))
                lastMap.put(num * r, lastMap.getOrDefault(num * r, 0L) + midMap.get(num));

            // consider every num as the first in the triplet, and add num*r to midMap
            midMap.put(num * r, midMap.getOrDefault(num * r, 0L) + 1);
        }  
        return count;
    }

    // provided by hackerrank
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
