package _20181028.minTurnaround;

import java.util.Scanner;

/**
 * 最少转机 —— 图的广度优先遍历
 *
 * 题目描述 ：广度优先遍历求一下两城之间的最小转机次数
 *
 * 基本思路 ：
 *      1、相当于之前那两个bfs的结合
 *      2、这个和迷宫那个要在进入while前立flag，是因为这并不是遍历完全的，而是找到目标点就可以结束了，
 *          不需要向中间那个图的遍历一样，等到所有节点都访问过了，队列的首和尾相遇了，才能够结束循环
 *      3、整理一下广度优先遍历的套路好啦 ：
 *          while(首尾不相遇 q.head < q.tail) {
 *              int current = q.head;
 *              遍历全部节点 for(int i = 0; i < 节点总数; i++) {
 *                  找到既能从current出发进行探索又没被探索过的 if(探索满足的条件 && book[i] == 0) {
 *                      将该节点加入队列，并标记为已访问
 *                      q.tail++;
 *                  }
 *
 *                  判断是否满足退出条件 （if-flag-if 或 if）
 *              }
 *              继续探索下一个节点 q.head++;
 *          }
 *
 *
 * 注意事项 ：
 *      为什么广度优先遍历只要一扩展到目标城市就结束了，深度优先遍历却不行呢？
 *          -- 因为bfs相当于是找出每一步能到达的所有点，然后dfs是找出从A到B所有可能路径嘛
 *          所以当bfs第一次拓展到目标城市的时候就已经是最短步数了，而dfs必须遍历完才知道哪条路是最短的
 *
 * 输出示例 ：
 *      请输入图中节点数量 ：
 *      5
 *      请输入节点间的连线个数：
 *      7
 *      请输入相连城市 ：
 *      0 1
 *      0 2
 *      1 2
 *      1 3
 *      2 3
 *      2 4
 *      3 4
 *      请分别输入出发城市与目标城市的编号 ：
 *      0 4
 *      则最少转机次数为 ：2
 *
 */

public class DemoMinTurnaround {
    private static int SIZE_OF_GRAPH;
    private static int[][] graph;
    private static int[] book;
    // 队列
    private static MyQueue q;
    private static final int BIIIIIIG_NUM = 99999999;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入图中节点数量 ：");
        SIZE_OF_GRAPH = scanner.nextInt();

        // 初始化二维数组
        graph = new int[SIZE_OF_GRAPH][SIZE_OF_GRAPH];
        for(int i = 0; i < SIZE_OF_GRAPH; i++) {
            for(int j = 0; j < SIZE_OF_GRAPH; j++) {
                // 自己跟自己设为 0，其他的都暂且设为无穷大
                graph[i][j] = (i == j) ? 0 : BIIIIIIG_NUM;
            }
        }

        System.out.println("请输入节点间的连线个数：");
        int size = scanner.nextInt();
        System.out.println("请输入相连城市 ：");
        int node1, node2;
        for(int i = 0; i < size; i++) {
            node1 = scanner.nextInt();
            node2 = scanner.nextInt();
            // 这里就还是无向图，设置为1
            graph[node1][node2] = 1;
        }

        System.out.println("请分别输入出发城市与目标城市的编号 ：");
        int startNum = scanner.nextInt();
        int endNum = scanner.nextInt();

        // 初始化队列
        q = new MyQueue(SIZE_OF_GRAPH);
        q.head = 0;
        q.tail = 0;
        // 将 0 号城市放入队列节点，并将初始步数设置为 0
        q.queue[q.tail].num = 0;
        q.queue[q.tail].step = 0;
        // 把尾巴向后移一位
        q.tail ++;

        // 然后初始化一下 book 数组，并将start节点标记为1
        book = new int[SIZE_OF_GRAPH];
        book[startNum] = 1;

        // 最后开始bfs
        bfs(endNum);

        System.out.println("则最少转机次数为 ：" + q.queue[q.tail-1].step);
    }

    /**
     * bfs
     * @param endNum 目标城市编号
     */
    private static void bfs(int endNum) {
        // 设置一个结束标志
        boolean hasArrived = false;
        // 队列不为空的时候，循环
        while (q.head < q.tail) {
            // head指向的节点即是当前访问的节点，即节点current
            int current = q.head;
            for(int i = 0; i < SIZE_OF_GRAPH; i++) {
                // 从头到尾遍历一遍，找到即跟当前点相连又没被探索过的城市
                if(graph[current][i] == 1 && book[i] == 0) {
                    // 将这个城市加入队列节点，然后标记为已访问
                    q.queue[q.tail].num = i;
                    book[i] = 1;
                    // 此时，转机次数等于当前首节点的转机次数+1
                    q.queue[q.tail].step = q.queue[q.head].step + 1;
                    // 然后把小尾巴往后移一位
                    q.tail++;
                }

                // 当目标城市已经被扩展到时
                if(q.queue[q.tail-1].num == endNum) {
                    // 将结束标志置为true
                    hasArrived = true;
                    // 直接结束for循环
                    break;
                }
            }

            // 若已经到达目的地
            if(hasArrived) {
                // 结束while循环
                break;
            }

            // 继续探索与下一个节点相连的节点
            q.head++;
        }
    }
}
