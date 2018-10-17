package _20181017.queue;

/**
 * 小小地封装一下这个队列
 */

public class MyQueue {
    int[] queue;
    int head;
    int tail;

    // 构造队列的时候就给它个初始数组大小
    MyQueue(int n) {
        queue = new int[n];
    }
}
