package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class LinkedListCycleTest {

    @Test
    public void checkLinkedListCycle() {
     ListNode node1 = new ListNode(1);
     ListNode node2 = new ListNode(2);
     ListNode node3 = new ListNode(3);
     ListNode node4 = new ListNode(4);
     LinkedListCycle obj = new LinkedListCycle();

     node1.next = node2;
     node2.next = node3;
     node3.next = node4;
     node4.next = node1;

     boolean actual = obj.checkLinkedListCycle(node1);
     boolean expected = true;
     assertEquals(expected,actual);
    }
}