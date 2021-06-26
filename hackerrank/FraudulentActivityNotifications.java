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

class Result {

    /*
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY expenditure
     *  2. INTEGER d
     */

/*
* @source https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem
* @author xiaoque55
* @date 2021.06.13
*
* Tried to solve this using counting sort approach but got time out problem for the test cases
* so I changed to a tricky one by using priority queue.
* the prevQ stores the first half of numbers in desc order (when d is odd, it stores the extra one)
* the lastQ stores another half of the numbers in asc order
* so 2 * mid will always be sum of the top of the two queues when d is even, and 2 * top of prevQ when d is odd
*/
   public static int activityNotifications(List<Integer> expenditure, int d) {
    // Write your code here
        int count = 0;
        int current = 0;
        int mid = 0;
        boolean odd = d % 2 == 1;
        PriorityQueue<Integer> prevQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> lastQ = new PriorityQueue<>();
        
        for (int i = 0; i < d; ++i) {
            prevQ.add(expenditure.get(i));
        }
        // lastQ always keeps d/2 size
        for (int i = 0; i < d/2; ++i) {
            lastQ.add(prevQ.poll());
        }
       
        for (int i = d; i < expenditure.size(); ++i) {
            // compute middle value
            current = expenditure.get(i);
            if (!odd)
                mid = prevQ.peek() + lastQ.peek();
            else mid = prevQ.peek() * 2;
            
            if(current >= mid) ++count;
            
            // update two queues
            if (prevQ.contains(expenditure.get(i - d)))
                prevQ.remove(expenditure.get(i - d));
            else lastQ.remove(expenditure.get(i - d));
            
            if (current <= prevQ.peek())
                prevQ.add(current);
            else lastQ.add(current);
            
            // update the size of two queues
            if (lastQ.size() <= d/2) {
                while (lastQ.size() < d/2) {
                    lastQ.add(prevQ.poll());                    
                }
            } else {
                while (lastQ.size() > d/2) {
                    prevQ.add(lastQ.poll());                    
                }
            }
        }
        return count;
    }

}
