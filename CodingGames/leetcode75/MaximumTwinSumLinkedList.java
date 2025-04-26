/*
 * @source https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list
 * @author xiaoque
 * @date 2025.04.25
 */

public class MaximumTwinSumLinkedList {
    public int pairSum(ListNode head) {

        // use 2 pointers to find mid node
        ListNode mid = findMiddleNode(head);

        // reverted half list head is prev
        mid = revertList(mid);

        // restart from begin, compute sum
        int result = 0;
        ListNode curr = head;
        while (mid != null) {
            result = Math.max(result, curr.val + mid.val);
            curr = curr.next;
            mid = mid.next;
        }
        return result;
    }

    private ListNode findMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // revert linked list from slow
    // prev -> head -> next -> next2 => prev <- head next -> next2
    private ListNode revertList(ListNode head) {
        ListNode prev = null;
        ListNode next = null;
        ListNode curr = head;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
