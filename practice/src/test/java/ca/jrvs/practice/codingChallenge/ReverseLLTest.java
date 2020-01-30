package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseLLTest {
    ReverseLL obj = new ReverseLL();
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);


    @Test
    public void reverseIterativeApproach() {

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int actual = obj.reverseIterativeApproach(node1).data;
        int expected = new ListNode(5).data;
        assertEquals(expected,actual);
    }

    @Test
    public void reverseRecursiceApproach() {
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int actual = obj.reverseIterativeApproach(node1).data;
        int expected = new ListNode(5).data;
        assertEquals(expected,actual);
    }
}