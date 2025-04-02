
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @source https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
 * @author xiaoque
 * @date 2025.03.22
 */
public class AllElementsInTwoBinarySearchTrees {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        // left < root < right
        // visit and store 2 trees as in order, then merge the two arraylist
        List<Integer> result = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();

        inOrder(root1, queue);
        inOrder(root2, queue, result);
        result.addAll(queue);
        return result;
    }

    // normal dp to visit tree
    private void inOrder(TreeNode root, LinkedList<Integer> queue) {
        if (root == null)
            return;
        inOrder(root.left, queue);
        queue.offer(root.val);
        inOrder(root.right, queue);
    }

    // visit tree and store prev value into result
    private void inOrder(TreeNode root, LinkedList<Integer> queue, List<Integer> list) {
        if (root == null)
            return;
        inOrder(root.left, queue, list);
        while (!queue.isEmpty() && queue.peek() <= root.val) {
            list.add(queue.poll());
        }
        list.add(root.val);
        inOrder(root.right, queue, list);
    }
}
