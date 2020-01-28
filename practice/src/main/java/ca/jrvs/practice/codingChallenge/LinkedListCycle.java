package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/LinkedList-Cycle-c909098bebcd4cbb9bc650b69264b8a9
 */
class ListNode {

    int data;
    ListNode next;

    ListNode(int data) {
        this.data = data;
        next = null;
    }
}

public class LinkedListCycle {


    /**
     * Time Complexity: O(n)
     * Justification:  Let us denote n as the total number of nodes in the linked list. To analyze its time complexity, we consider the following two cases separately.
     *
     * List has no cycle:
     * The fast pointer reaches the end first and the run time depends on the list's length, which is O(n)O(n).
     *
     * List has a cycle:
     * We break down the movement of the slow pointer into two steps, the non-cyclic part and the cyclic part:
     *
     *          The slow pointer takes "non-cyclic length" steps to enter the cycle. At this point, the fast pointer has already reached the cycle. \text{Number of iterations} = \text{non-cyclic length} = NNumber of iterations=non-cyclic length=N
     *
     *          Both pointers are now in the cycle. Consider two runners running in a cycle - the fast runner moves 2 steps while the slow runner moves 1 steps at a time. Since the speed difference is 1, it takes {distance between the 2 runners}/{difference of speed}
     *         loops for the fast runner to catch up with the slow runner. As the distance is at most "\text{cyclic length K}cyclic length K" and the speed difference is 1, we conclude that
     *         {Number of iterations} = {almost}Number of iterations=almost "cyclic length K}cyclic length K".
     *
     * Therefore, the worst case time complexity is O(N+K)O(N+K), which is O(n)O(n).
     *
     *
     * Space Complexity: O(1)
     * Justification: We only use two nodes (slow and fast) so the space complexity is O(1)O(1).
     */
    public boolean checkLinkedListCycle(ListNode head){
        if(head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(slow!=fast){
            if(fast==null || fast.next == null){
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

}
