
/*
 * @source https://leetcode.com/problems/reverse-linked-list/
 * @author xiaoque
 * @date 2025.03.22
 */

// Definition for singly-linked list.
class LinkedListNode {
    int val;
    LinkedListNode next;

    LinkedListNode() {
    }

    LinkedListNode(int val) {
        this.val = val;
    }

    LinkedListNode(int val, LinkedListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class ReverseLinkedList {
    public LinkedListNode reverseList(LinkedListNode head) {
        if (head == null || head.next == null)
            return head;
        LinkedListNode prev = head;
        LinkedListNode curr = head.next;
        LinkedListNode next;
        head.next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

}
