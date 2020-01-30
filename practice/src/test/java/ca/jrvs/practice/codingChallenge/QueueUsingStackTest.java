package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueUsingStackTest {

    @Test
    public void queueUsingStack() {
        QueueUsingStack queue = new QueueUsingStack();


        queue.push(2);
        assertFalse(queue.empty());

        queue.push(5);
        queue.push(10);
        queue.push(1);

        assertEquals(2, queue.peek());
        assertEquals(2, queue.pop());
        assertEquals(5, queue.pop());
        assertEquals(10, queue.pop());
        assertEquals(1, queue.pop());

        assertTrue(queue.empty());

    }

}