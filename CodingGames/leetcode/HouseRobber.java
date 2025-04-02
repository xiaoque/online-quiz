
import java.util.Map;

/*
 * @source https://leetcode.com/problems/house-robber/
 *          https://leetcode.com/problems/house-robber-iii/
 * @author xiaoque
 * @date 2025.03.29
 */
public class HouseRobber {
    public int rob(int[] nums) {
        // for memo[i]: either it's not robbed memo[i-1], or it's robbed
        // memo[i-2]+nums[i]
        // [2,1,1,2], memo[0] = 2 memo[1] = max(2, 0+1)
        // memo[2] = (2, 2+1), memo[3] = (3, 2+2)

        // for memo[0]: robbed memo[n-2] + nums[0] not robbed means + nums[n-1]
        // for the rest memo[1]: robbed memo[n-1]+ nums[1], not memo[0]
        // same way but with different memo[0]
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        return Math.max(dp(nums, 0, nums.length - 1), dp(nums, 1, nums.length));
    }

    public int dp(int[] nums, int first, int last) {
        int prev = 0, prev2 = 0;
        for (int i = first; i < last; i++) {
            int curr = Math.max(nums[i] + prev2, prev);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    // for robber III start from current node, rob it or rob its subnodes
    public int dp(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null)
            return 0;
        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        int curr = root.val;
        // get sum if rob curr
        if (root.left != null)
            curr += dp(root.left.left, memo) + dp(root.left.right, memo);
        if (root.right != null)
            curr += dp(root.right.left, memo) + dp(root.right.right, memo);

        // compare with if skip curr and rob its child
        curr = Math.max(curr, dp(root.left, memo) + dp(root.right, memo));
        memo.put(root, curr);
        return curr;
    }
}
