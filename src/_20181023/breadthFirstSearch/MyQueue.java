package _20181023.breadthFirstSearch;

/**
 * 小小地封装一下这个队列
 */

class MyQueue {
    // 这是一个队列节点组
    QueueNode[] queue;
    int head;
    int tail;

    // 构造队列的时候就给它个初始数组大小
    MyQueue(int n) {
        queue = new QueueNode[n];

        // 注意这里要把队列中的每个节点都实例化一遍
        for(int i = 0; i < n; i++) {
            queue[i] = new QueueNode();
        }
    }
}
