package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class NthNodeLLTest {

    @Test
    public void findNthNodeLL() {
        NthNodeLL obj = new NthNodeLL();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int actual = obj.findNthNodeLL(node1,2).data;
        int expected = 1;
        assertEquals(expected,actual);
    }
}