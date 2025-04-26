/*
 * @source https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * @author xiaoque
 * @date 2025.04.26
 */
public class BestTimeBuySellStockTransactionFee {
    /*
     * Given any day i, max profix is either hold[i] a stock or not hold[i]
     * hold[i] = max( hold[i - 1], notHold[i - 1] - price) buy stock today
     * notHold[i] = max( notHold[i - 1], hold[i - 1] + price - fee) sell stock today
     * initial case, notHold = 0, it's impossible to hold at begin, hold = MIN_VALUE
     */
    public int maxProfit(int[] prices, int fee) {
        int hold = Integer.MIN_VALUE, notHold = 0;
        for (int price : prices) {
            /*
             * Case when hold value is updated : price is low enough
             * Case when notHold value is updated : price is high enough to cover fee
             * It's not possible that 2 values change for same price
             * So we dont neet to use a 3rd value to store previous hold value
             */
            hold = Math.max(hold, notHold - price);
            notHold = Math.max(notHold, hold + price - fee);
        }
        return notHold;
    }
}
