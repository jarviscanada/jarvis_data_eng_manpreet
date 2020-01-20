package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {

    @Test
    public void recursiveFibonacci() {
        test();
    }

    @Test
    public void dynamicFibonacci() {
        test();
    }

    public void test(){
        Fibonacci fib = new Fibonacci();
        int expected = 8;
        int actual = fib.dynamicFibonacci(6);
        assertEquals(expected,actual);
    }


}