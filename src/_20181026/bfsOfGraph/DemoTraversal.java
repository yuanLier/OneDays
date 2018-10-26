package _20181026.bfsOfGraph;

import java.util.Scanner;

/**
 * 图的广度优先遍历
 *
 * 题目描述 ：广度优先遍历一下图
 *
 * 基本思路 ：
 *      1、还是把这个图看作一个二维数组 graph[SIZE_OF_GRAPH][SIZE_OF_GRAPH]，处理方法和之前一样
 *      2、建立一个队列，用来存储这个图的各个节点，然后就开始我们的bfs叭！
 *          首先从 0 号节点（current）出发开始遍历这个数组
 *          每找到一个与当前点相连（graph[current][i] == 1），且未被探索过的点（book[i] == 0）
 *          就把它加入队列（q.queue[q.tail] = i），并标记为已探索（book[i] = 1）
 *          与当前点相连的点全部探索完之后，就可以把这个点丢掉啦！然后 q.head++，继续探索下一个点
 *      3、因为 q.head++ 遍历的是图的节点嘛，所以当 q.tail == SIZE_OF_GRAPH 的时候，就可以结束了
 *
 * 注意事项 ：
 *      这里的队列就是直接存储图中节点的，所以不需要有step，直接顺序存进去就好了
 *
 * 输出示例 ：
 *      请输入图中节点数量 ：
 *      5
 *      请输入节点间的连线个数：
 *      5
 *      请输入各对相连节点 ：
 *      0 1
 *      0 2
 *      0 4
 *      1 3
 *      2 4
 *      图的广度遍历顺序如下 ：
 *      0 1 2 4 3
 *
 */

public class DemoTraversal {
    // 图中的节点个数啦，也可以看作二位数组的行列数
    private static int SIZE_OF_GRAPH;
    // 二维数组化的图
    private static int[][] graph;
    // 记录该点是否已经访问过
    private static int[] book;
    // 同样，队列也设为静态的
    private static MyQueue q;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入图中节点数量 ：");
        SIZE_OF_GRAPH = scanner.nextInt();

        // 初始化二维数组
        graph = new int[SIZE_OF_GRAPH][SIZE_OF_GRAPH];
        for(int i = 0; i < SIZE_OF_GRAPH; i++) {
            for(int j = 0; j < SIZE_OF_GRAPH; j++) {
                graph[i][j] = (i == j) ? 0 : 9999;
            }
        }

        System.out.println("请输入节点间的连线个数：");
        int size = scanner.nextInt();
        System.out.println("请输入各对相连节点 ：");
        int node1, node2;
        for(int i = 0; i < size; i++) {
            node1 = scanner.nextInt();
            node2 = scanner.nextInt();
            graph[node1][node2] = 1;
            graph[node2][node1] = graph[node1][node2];
        }

        // 初始化一个队列
        q = new MyQueue(SIZE_OF_GRAPH);
        q.head = 0;
        q.tail = 0;
        // 将 0 号节点放入队列，并把尾巴向后移一位
        q.queue[q.tail] = 0;
        q.tail ++;

        // 然后初始化一下 book 数组，并将 0 号节点标记为 1
        book = new int[SIZE_OF_GRAPH];
        book[0] = 1;

        // 然后就可以直接 bfs 啦！
        System.out.println("图的广度遍历顺序如下 ：");
        bfs();
    }

    private static void bfs() {
        // 队列不为空的时候，循环
        while (q.head < q.tail) {
            // head指向的节点即是当前访问的节点，即节点current
            int current = q.head;
            for(int i = 0; i < SIZE_OF_GRAPH; i++) {
                // 从头到尾遍历一遍，找到即跟当前点相连又没被探索过的节点 i 们
                if(graph[current][i] == 1 && book[i] == 0) {
                    // 将这个节点 i 加入队列，然后标记为已访问
                    q.queue[q.tail] = i;
                    book[i] = 1;
                    // 然后把小尾巴往后移一位
                    q.tail++;
                }

                // 当尾巴增大到 SIZE_OF_GRAPH 这么大的时候，就说明各个节点都已经访问过了
                if(q.tail == SIZE_OF_GRAPH) {
                    // 直接结束循环
                    break;
                }
            }
            // 走到这里说明与当前节点相连的节点已经全部探索完了；head++，继续探索与下一个节点相连的节点
            q.head++;
        }

        // 最后，遍历这个队列，打印输出
        for(int i = 0; i < SIZE_OF_GRAPH; i++) {
            System.out.print(q.queue[i] + " ");
        }
    }
}
