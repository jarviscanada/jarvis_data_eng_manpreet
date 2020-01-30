package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class RemoveDuplicatesLLTest {

    @Test
    public void removeDuplicates() {
        RemoveDuplicatesLL obj = new RemoveDuplicatesLL();
        ListNode head = new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);

        ListNode actual = obj.removeDuplicates(head);
        assertEquals(2, actual.data);
        assertEquals(3, actual.next.data);
        assertEquals(4, actual.next.next.data);
        assertNull(actual.next.next.next);
    }
}