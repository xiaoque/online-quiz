package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
 * @source  https:// leetcode.com/problems/average-of-levels-in-binary-tree/description/
 * @author xiaoque
 * @date 2025.03.22
 */

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class AverageLevelsBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> average = new ArrayList<>();

        // traverse the node using bfs
        TreeNode curr;
        int totSize = 0;
        double sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            sum = 0;
            totSize = queue.size();
            // 2 layer of loop to cacul avg
            for (int i = 0; i < totSize; i++) {
                curr = queue.poll();
                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null)
                    queue.add(curr.right);
                sum += curr.val;
            }
            average.add(Double.valueOf(sum / totSize));
        }
        return average;
    }
}
