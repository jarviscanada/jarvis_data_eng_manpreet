package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackUsingQueueTest {

    @Test
    public void tester(){
        StackUsingQueue<Integer> obj = new StackUsingQueue<>();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        obj.push(4);

        int expected = obj.pop();
        int actual = 4;
        assertEquals(expected,actual);

        expected = obj.pop();
        actual = 3;
        assertEquals(expected,actual);
    }
}