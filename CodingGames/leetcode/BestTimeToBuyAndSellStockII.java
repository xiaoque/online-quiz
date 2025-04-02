
/*
 * @source https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * @author xiaoque
 * @date 2025.03.22
 */
public class BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        // for the first price drop, by stock
        // for every following price drop, sell to get the profit
        // if price keep go up, then by default bought at first day and no need to sell

        int profit = 0, buy = 0;
        boolean bought = false;
        for (int i = 1; i < prices.length; i++) {
            if (bought && prices[i - 1] > prices[i]) {
                profit += prices[i - 1] - prices[buy];
                bought = false;
            } else if (!bought && prices[i - 1] < prices[i]) {
                bought = true;
                buy = i - 1;
            }
        }
        if (bought) {
            profit += prices[prices.length - 1] - prices[buy];
        }
        return profit;
    }
}
