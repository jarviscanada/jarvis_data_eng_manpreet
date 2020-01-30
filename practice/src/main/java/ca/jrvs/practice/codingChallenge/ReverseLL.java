package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Reverse-Linked-List-8c94d55849c745b3b435f81f6d8a4b78
 */
public class ReverseLL {

    /**
     * Time Complexity: O(n)
     * Justification: n is the number of nodes in the given list.
     *
     * Space Complexity: O(1)     *
     */
    public ListNode reverseIterativeApproach(ListNode head){
        ListNode curr = head;
        ListNode prev = null;

        while(curr!=null){
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * Time Complexity: O(n)
     * Justification: n is the number of nodes in the given list.
     *
     * Space Complexity: O(n)
     * Justification: The extra space comes from implicit stack space due to recursion.
     * The recursion could go up to nn levels deep.
     */
    public ListNode reverseRecursiceApproach(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode node = reverseRecursiceApproach(head.next);
        head.next.next = head;
        head.next = null;
        return node;

    }
}
