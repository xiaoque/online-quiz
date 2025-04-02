
import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/coin-change/
 * @author xiaoque
 * @date 2025.03.22
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        // for each coins, try amount - coins_i
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -666);
        return dp(coins, amount, memo);

    }

    private int dp(int[] coins, int amount, int[] memo) {
        if (amount == 0)
            return 0;
        if (amount < 0)
            return -1;
        if (memo[amount] != -666)
            return memo[amount];
        int res = Integer.MAX_VALUE;
        for (int i : coins) {
            int tmp = dp(coins, amount - i, memo);
            if (tmp == -1)
                continue;
            res = Math.min(res, 1 + tmp);
        }
        memo[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[amount];
    }
}
