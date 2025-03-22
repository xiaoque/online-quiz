
/*
* @source https://www.hackerrank.com/challenges/min-max-riddle/problem?
* @author xiaoque55
* @date 2021.06.26
*
*/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MinMaxRiddle {

    // Complete the riddle function below.
    static long[] riddle(long[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();

        // Get two array as the next minimum element and the previous minimum element
        int[] nextMin = new int[arr.length + 1];
        int[] prevMin = new int[arr.length + 1];

        for (int i = 0; i < nextMin.length; ++i) {
            nextMin[i] = arr.length;
            prevMin[i] = -1;
        }

        for (int i = 0; i < arr.length; ++i) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty())
                prevMin[i] = stack.peek();
            stack.push(i);
        }

        // reuse the stack to get the next min element
        while (!stack.isEmpty())
            stack.pop();

        for (int i = arr.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty())
                nextMin[i] = stack.peek();
            stack.push(i);
        }

        // Compute the result
        long[] resTmp = new long[arr.length + 1];
        for (int i = 0; i < arr.length; ++i) {
            int index = nextMin[i] - prevMin[i] - 1;
            resTmp[index] = Math.max(resTmp[index], arr[i]);
        }
        for (int i = arr.length - 1; i >= 1; --i)
            resTmp[i] = Math.max(resTmp[i], resTmp[i + 1]);

        long[] res = new long[arr.length];
        res = Arrays.copyOfRange(resTmp, 1, arr.length + 1);
        return res;
    }
}
