import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @source https://leetcode.com/problems/asteroid-collision/
 * @author xiaoque
 * @date 2025.04.02
 */
public class AsteroidCollision {
    // use stack or 2 pointers, when there are explode, move backwards until explode
    // stops
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int curr : asteroids) {
            // only when stack is > 0, curr < 0 there will be explode
            if (!stack.isEmpty() && stack.peek() > 0 && curr < 0) {
                while (!stack.isEmpty() && stack.peek() > 0 && Math.abs(stack.peek()) < Math.abs(curr)) {
                    stack.pop();
                }
                /*
                 * Loop stops for condition:
                 * 1. curr explodes, then no action needed
                 * 2. stack.peek explodes => either stack is empty or next stack.peek also < 0,
                 * add curr into stack
                 * 3. both curr and stack explode, then remove first item at stack
                 */
                if (stack.isEmpty() || stack.peek() < 0)
                    stack.push(curr);
                else if (stack.peek() == curr * -1)
                    stack.pop();
            } else {
                stack.push(curr);
            }
        }
        // construct the result top down
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }
}
