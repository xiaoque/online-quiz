
import java.util.Stack;

/*
 * @source https://leetcode.com/problems/add-two-numbers-ii/
 * @author xiaoque
 * @date 2025.03.22
 */

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class AddTwoNumbers {
    public ListNode reverse(ListNode l1) {
        ListNode prev = null;
        ListNode curr = l1;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> list1 = new Stack<ListNode>();
        Stack<ListNode> list2 = new Stack<ListNode>();
        Stack<ListNode> result = new Stack<ListNode>();

        while (l1 != null) {
            list1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            list2.push(l2);
            l2 = l2.next;
        }

        int plusOne = 0;
        while (!list1.empty() && !list2.empty()) {
            ListNode curr = new ListNode(list1.pop().val + list2.pop().val + plusOne);
            plusOne = curr.val / 10;
            curr.val = curr.val % 10;
            result.push(curr);
        }

        while (!list1.empty()) {
            ListNode curr = new ListNode(list1.pop().val + plusOne);
            plusOne = curr.val / 10;
            curr.val = curr.val % 10;
            result.push(curr);
        }

        while (!list2.empty()) {
            ListNode curr = new ListNode(list2.pop().val + plusOne);
            plusOne = curr.val / 10;
            curr.val = curr.val % 10;
            result.push(curr);
        }

        if (plusOne == 1) {
            ListNode curr = new ListNode(plusOne);
            result.push(curr);
        }

        ListNode newBegin = result.pop();
        ListNode curr = newBegin;
        while (!result.empty()) {
            curr.next = result.pop();
            curr = curr.next;
        }
        return newBegin;
    }
}
