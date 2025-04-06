import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @source 
 * @author xiaoque
 * @date 2025.04.03
 */
public class MaximumLevelSumBinaryTree {
    // level starts at 1, compute sum of nodes at each level
    public int maxLevelSum(TreeNode root) {
        int currentLevel = 0;
        int minLvl = 0, maxSum = Integer.MIN_VALUE;

        // start dfs
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            TreeNode curr;
            while (size > 0) {
                curr = queue.poll();
                sum += curr.val;
                if (curr.left != null)
                    queue.offer(curr.left);
                if (curr.right != null)
                    queue.offer(curr.right);
                size--;
            }
            // update maxSum and minLvl
            currentLevel++;
            if (sum > maxSum) {
                maxSum = sum;
                minLvl = currentLevel;
            }
        }
        return minLvl;
    }
}