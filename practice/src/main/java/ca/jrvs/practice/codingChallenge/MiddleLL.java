package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Middle-of-the-Linked-List-46712afb255246898a1c9b0a651a9c03
 */

public class MiddleLL {

    /**
     * Time Complexity: O(n)
     * Justification: n is the number of nodes in the given list.
     *
     * Space Complexity: O(1)
     * Justification: The space used by slow and fast.
     */
    public ListNode findMiddleLinkedlist(ListNode head){
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
