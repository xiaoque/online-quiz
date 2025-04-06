import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @source https://leetcode.com/problems/binary-tree-right-side-view/
 * @author xiaoque
 * @date 2025.04.03
 */
public class BinaryTreeRightSideView {
    // node i can see on the right => last node of each level
    // => visit by level => bfs
    public List<Integer> rightSideView(TreeNode root) {
        // use 2-layer loop do bfs
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // size of current level
            int size = queue.size();
            TreeNode curr = null;
            while (size > 0) {
                curr = queue.poll();
                if (curr.left != null)
                    queue.offer(curr.left);
                if (curr.right != null)
                    queue.offer(curr.right);
                size--;
            }
            // curr is last node of the level
            result.add(curr.val);
        }
        return result;
    }

}
