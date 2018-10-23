package _20181023.breadthFirstSearch;

/**
 * 记录队列中每个节点
 */

class QueueNode {
    // 横纵坐标
    int x;
    int y;
    // 记录父坐标在队列中的位置
    int father;
    // 记录步数
    int step;
}
