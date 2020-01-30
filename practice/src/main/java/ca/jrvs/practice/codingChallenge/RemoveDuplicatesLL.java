package ca.jrvs.practice.codingChallenge;


import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * ticket: https://www.notion.so/Duplicate-LinkedList-Node-9e06eea40d814d7abe231a1a2a9c7a7c
 */
public class RemoveDuplicatesLL {

    /**
     * Time complexity : O(n)
     * Justification: the whole linkedlist is travered with length n
     *
     * Space Complexity: O(n)
     * Justification: Extra space of length n is used while creating Set
     **/
    public ListNode removeDuplicates(
            ListNode head){
        HashSet<Integer> set = new HashSet<>();
        ListNode previous = null;
        ListNode temp = head;
        while(temp!=null){
            if(set.contains(temp.data)){
                previous.next = temp.next;
            } else {
                set.add(temp.data);
                previous = temp;
            }
            temp = temp.next;
        }
        return head;
    }
}
