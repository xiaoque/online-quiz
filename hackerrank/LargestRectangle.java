import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class LargestRectangle {

    /*
     * Complete the 'largestRectangle' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY h as parameter.
     */

    /*
     * @source https://www.hackerrank.com/challenges/largest-rectangle/problem?
     * 
     * @author xiaoque55
     * 
     * @date 2021.06.22
     *
     */

    public static long largestRectangle(List<Integer> h) {
        // stack stores index of each height
        Deque<Integer> stack = new ArrayDeque<>();
        long max = h.get(0);
        long area = h.get(0);
        int tmp, length;
        int i = 0;

        while (i < h.size()) {
            if (stack.isEmpty() || h.get(stack.peek()) <= h.get(i)) {
                // if two values are equal, replace the index with the bigger one
                if (!stack.isEmpty() && h.get(stack.peek()) == h.get(i)) {
                    stack.pop();
                }
                stack.push(i++);
            } else {
                /*
                 * if the last one in stack is bigger than h.get(i),
                 * it means the rectangle of its height has a right border of value i-1
                 * and the left boder will be the former index in the stack
                 * when the stack is empty, it means the rectangle start from 0 then we have
                 * length i-1+1 = i
                 * remove it from stack and compute the area
                 */
                tmp = h.get(stack.pop());
                length = stack.isEmpty() ? i : (i - stack.peek() - 1);
                area = tmp * length;
                if (area > max)
                    max = area;
            }
        }

        while (!stack.isEmpty()) {
            tmp = h.get(stack.pop());
            length = stack.isEmpty() ? i : (i - stack.peek() - 1);
            area = tmp * length;
            if (area > max)
                max = area;
        }
        return max;
    }

}
