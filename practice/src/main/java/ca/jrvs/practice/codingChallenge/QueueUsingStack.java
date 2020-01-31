package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * ticket: https://www.notion.so/Implement-Queue-using-Stack-1e0a5b5bbf9f4fa396a138adc7ea6c20
 */
public class QueueUsingStack {
    Stack<Integer> s1;
    Stack<Integer> s2;

    /** Initialize your data structure here. */
    public QueueUsingStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
       if (s1.empty()) {
           s1.push(x);
           return;
       }
       while (!s1.empty()) {
           s2.push(s1.pop());
       }
       s2.push(x);
       while (!s2.empty()) {
           s1.push(s2.pop());
       }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return s1.pop();
    }

    /** Get the front element. */
    public int peek() {
        return s1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.empty();
    }
}
