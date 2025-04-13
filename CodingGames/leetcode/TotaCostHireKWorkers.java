import java.util.PriorityQueue;

/*
 * @source 
 * @author xiaoque
 * @date 2025.04.13
 */
public class TotaCostHireKWorkers {
    public long totalCost(int[] costs, int k, int candidates) {
        // use 2 queues, easier to add new values
        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();

        int low = 0, high = costs.length - 1;
        long result = 0;
        while (k-- > 0) {
            // update 2 queues
            while (low <= high && left.size() < candidates) {
                left.offer(costs[low++]);
            }
            while (low <= high && right.size() < candidates) {
                right.offer(costs[high--]);
            }
            // compare 2 min values from 2 queue
            int val1 = left.size() > 0 ? left.peek() : Integer.MAX_VALUE;
            int val2 = right.size() > 0 ? right.peek() : Integer.MAX_VALUE;

            if (val1 <= val2) {
                result += left.poll();
            } else {
                result += right.poll();
            }
        }

        return result;
    }
}
