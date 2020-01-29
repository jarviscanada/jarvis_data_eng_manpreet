package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;


/**
 * ticket: https://www.notion.so/Implement-Stack-using-Queue-52f8304f6c1848d58bbce6d2f651d103
 **/
public class StackUsingQueue <T>{
    /*
    *Time complexity : O(n). The algorithm removes n elements and inserts n + 1 elements to q1 , where n is the stack size.
    * This gives 2n + 1 operations. The operations add and remove in linked lists has O(1) complexity.
    *
    * Space complexity : O(1)
    */
    private Queue<T> queue = new LinkedList<>();

    public void push(T element) {
        queue.add(element);
        int size = queue.size();
        while(size>1){
            queue.add(queue.remove());
            size--;
        }
    }

    public T pop() {
        return queue.remove();
    }

    public T top(){
        return queue.peek();
    }
}
