package _20181108.cutPoint;

import java.util.Scanner;

/**
 * 图的割点
 *
 * 题目描述 ：找出能把一张图分为互不相连的几段图的节点叭
 *
 * 基本思路 ：
 *      1、从图中某一节点开始对图进行遍历，用一个数 num 记录每一个节点的时间戳
 *      2、用另一个数组 low 来记录每个节点所能直接或间接到达的时间戳最小的那个节点的时间戳
 *      3、对于某一个当前节点 current，找到跟它能相连的节点 i ：
 *           如果 low[current] < num[i]，就更新时间戳，令 low[current] = num[i]
 *           若当前节点不是根节点且满足 low[i] >= num[current]，则当前节点为割点
 *           若当前节点是根节点且有两个儿子，则当前根节点为割点
 *
 * 注意事项 ：
 *      时间戳指的是这个节点是第几个被访问的
 *
 * 注意事项 plus ：
 *      这里是用邻接矩阵来存储图的，时间复杂度最少都有O（N^2）了，其实是不应该的；
 *      实际应用中需要改为使用邻接表来存储，这样时间复杂度会降到 O（N+M）
 *      那么为什么这里不用呢？当然是因为我邻接表不会啦！（划掉 是为了突出割点的算法部分啦！
 *
 * 输出示例 ：
 *      请输入图中的节点个数 ：
 *      6
 *      请输入节点间的连线个数 ：
 *      7
 *      请输入各节点之间的连线关系 ：
 *      0 3
 *      0 2
 *      3 1
 *      2 1
 *      1 4
 *      1 5
 *      4 5
 *      则该图的割点有 ：
 *      1
 */

public class DemoCutPoint {
    // 节点个数
    private static int SIZE_OF_NODE;
    // 节点间连线个数
    private static int SIZE_OF_EDGE;
    // 用一个index来进行时间戳的递增
    private static int index = 0;
    // 储存每一个节点的时间戳
    private static int[] num;
    // 储存能够到达的最小时间戳
    private static int[] low;
    // 存储这个图的邻接矩阵
    private static int[][] graph;
    // 人为规定的根节点
    private static int root;
    // 用来记录是否为割点
    private static int[] flag;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入图中的节点个数 ：");
        SIZE_OF_NODE = scanner.nextInt();
        // 初始化 num 和 low 和 flag
        num = new int[SIZE_OF_NODE];
        low = new int[SIZE_OF_NODE];
        flag = new int[SIZE_OF_NODE];

        // 初始化邻接矩阵
        graph = new int[SIZE_OF_NODE][SIZE_OF_NODE];
        for(int i = 0; i < SIZE_OF_NODE; i++) {
            for(int j = 0; j < SIZE_OF_NODE; j++) {
                graph[i][j] = 0;
            }
        }

        System.out.println("请输入节点间的连线个数 ：");
        SIZE_OF_EDGE = scanner.nextInt();
        System.out.println("请输入各节点之间的连线关系 ：");
        int node1, node2;
        for(int i = 0; i < SIZE_OF_EDGE; i++) {
            node1 = scanner.nextInt();
            node2 = scanner.nextInt();
            graph[node1][node2] = (graph[node2][node1] = 1);
        }

        // 指定根节点
        root = 0;
        // 然后就可以开始我们的dfs啦
        dfs(0, root);

        // 输出
        System.out.println("则该图的割点有 ：");
        for(int i = 0; i < SIZE_OF_NODE; i++) {
            if(flag[i] == 1) {
                System.out.println(i + " ");
            }
        }
    }

    /**
     * 割点算法的核心部分
     * @param current 当前节点编号
     * @param father 其父节点的编号
     */
    public static void dfs(int current, int father) {
        // child 用来记录生成树中当前节点的儿子个数
        int child = 0;

        // 时间戳+1
        index++;
        // 当前节点的时间戳
        num[current] = index;
        // 当前节点能到达的最小时间戳（初始当然为自己啦）
        low[current] = index;

        // 遍历所有节点
        for(int i = 0; i < SIZE_OF_NODE; i++) {
            // 找到和current相连的节点
            if (graph[current][i] == 1) {
                // 若这个节的时间戳为0，说明还没被访问过
                if (num[i] == 0) {
                    // 从当前节点出发，遍历到的跟它相连且未被访问过的节点都是它儿子
                    child++;

                    // 继续沿着这个没被访问过的节点往下递归
                    dfs(i, current);

                    // 更新当前节点能访问到的最小时间戳
                    low[current] = low[current] < low[i] ? low[current] : low[i];

                    // 若当前节点不是根节点且满足 low[i] >= num[current]
                    if(current != root && low[i] >= num[current]) {
                        // 说明它是一个割点
                        flag[current] = 1;

                    // 若当前节点是根节点且有两个儿子
                    } else if(current == root && child == 2) {
                        // 说明它是一个割点
                        flag[current] = 1;
                    }

                // 若这个节点i已经被访问过且不是当前节点的爸爸
                } else if(i != father){
                    // 更新当前节点能访问到的最小时间戳
                    low[current] = low[current] < num[i] ? low[current] : num[i];
                }
            }
        }

    }
}
