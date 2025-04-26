import java.util.LinkedList;
import java.util.Queue;

/*
 * @source https://leetcode.com/problems/number-of-recent-calls/
 * @author xiaoque
 * @date 2025.04.26
 */
public class NumberRecentCalls {
    class RecentCounter {
        Queue<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            // add curr to queue
            queue.offer(t);
            // pop until peek >= t- 3000
            while (!queue.isEmpty() && queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }
}
