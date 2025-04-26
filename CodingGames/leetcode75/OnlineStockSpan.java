import java.util.Stack;

/*
 * @source https://leetcode.com/problems/online-stock-span/
 * @author xiaoque
 * @date 2025.04.24
 */
public class OnlineStockSpan {
    class StockSpanner {
        private Stack<int[]> stack;

        public StockSpanner() {
            stack = new Stack<>();
        }

        public int next(int price) {
            int span = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                span += stack.pop()[1];
            }
            stack.add(new int[] { price, span });
            return span;
        }
    }
}
