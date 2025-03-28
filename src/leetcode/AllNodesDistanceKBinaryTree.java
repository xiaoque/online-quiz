package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * @source https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * @author xiaoque
 * @date 2025.03.25
 */
public class AllNodesDistanceKBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // then start from target node, there are 2 ways
        // 1. target child nodes (visit using bfs)
        // 2. target to root, then another branch of root (need add parent-child
        // relationship)
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        // construct the parent-child relationship
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.left != null) {
                parent.put(curr.left, curr);
                queue.add(curr.left);
            }
            if (curr.right != null) {
                parent.put(curr.right, curr);
                queue.add(curr.right);
            }
        }

        // start visit the tree from target
        queue.add(target);
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        int depth = 0;
        while (!queue.isEmpty()) {
            if (depth == k)
                break;
            depth++;
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null && !visited.contains(curr.left.val)) {
                    queue.add(curr.left);
                    visited.add(curr.left.val);
                }
                if (curr.right != null && !visited.contains(curr.right.val)) {
                    queue.add(curr.right);
                    visited.add(curr.right.val);
                }

                if (parent.containsKey(curr) && !visited.contains(parent.get(curr).val)) {
                    queue.add(parent.get(curr));
                    visited.add(parent.get(curr).val);
                }
            }
        }
        while (!queue.isEmpty()) {
            result.add(queue.poll().val);
        }
        return result;
    }
}
