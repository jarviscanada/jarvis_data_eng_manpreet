package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Nth-Node-From-End-of-List-4342074823934322860561a282d0cb99
 */

public class NthNodeLL {

    /*
    *Time complexity : O(L)
    *The algorithm makes two traversal of the list, first to calculate list length L and second to find the (L - n) th node.
    *There are 2L-n operations and time complexity is O(L).
    *
    *Space complexity : O(1)
    * We only used constant extra space.
    */
    public ListNode findNthNodeLL(ListNode head, int n){
        ListNode dummy = new ListNode(0);
        int length = 0;
        dummy.next= head;
        ListNode first = head;
        while(first!=null){
            length++;
            first = first.next;
        }
        length = length-n;
        first = dummy;

        while(length>0){
            length--;
            first=first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }
}
