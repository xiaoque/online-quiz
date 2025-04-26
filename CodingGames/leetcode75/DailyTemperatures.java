import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @source https://leetcode.com/problems/daily-temperatures/
 * @author xiaoque
 * @date 2025.04.26
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        // use stack to store previous value
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            // pop up until current > peek() value and write the answer
            while (!stack.isEmpty() && temperatures[stack.peekFirst()] < temperatures[i]) {
                result[stack.peekFirst()] = i - stack.removeFirst();
            }
            // add index to stack
            stack.addFirst(i);
        }
        // for all left values, put 0
        return result;
    }
}
