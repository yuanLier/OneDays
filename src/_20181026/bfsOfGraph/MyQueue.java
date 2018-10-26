package _20181026.bfsOfGraph;

/**
 * 这是用来装图的节点的队列
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
