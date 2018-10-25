package _20181025.dfsOfGraph;

import java.util.Scanner;

/**
 * 图的深度优先遍历
 *
 * 题目描述 ：深度优先遍历一下图
 *
 * 基本思路 ：
 *      1、将图看作一个二维数组（graph[SIZE_OF_GRAPH]），有连线的地方标为 1，没有连线的地方标为无穷大，自己和自己标为 0
 *      2、然后就dfs开始遍历啦！还是分为三部分 ：
 *          判断边界 ：
 *              用一个初始为 0 的 sum 来记录总数，每访问一个点就令 sum++
 *              当 sum == SIZE_OF_GRAPH 的时候，就说明已经遍历完啦，直接 return
 *          依次尝试每个点 ：
 *              站在当前点上，从 0 到 n-1 开始遍历每个点，就记为 i 好啦
 *              判断当前点是否与点 i 有连线(graph[current][i] == 1)，以及点 i 是否已经访问过（book[i] == 0）
 *              若点 i 与其有连线且未被访问过，就访问它（令 book[i] = 1），然后将它看作当前点继续遍历（dfs(i)）
 *          最后返回就好啦；
 *
 * 注意事项 ：
 *      注意无向图的话，初始化的时候要标记两下哦！
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
 *      图的深度遍历顺序如下
 *      0 1 3 2 4
 *
 */

public class DemoTraversal {
    // 其实是图中的节点个数啦，也可以看作二位数组的行列数
    private static int SIZE_OF_GRAPH;
    // 二维数组化的图
    private static int[][] graph;
    // 记录该点是否已经访问过
    private static int[] book;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入图中节点数量 ：");
        SIZE_OF_GRAPH = scanner.nextInt();

        // 初始化二维数组
        graph = new int[SIZE_OF_GRAPH][SIZE_OF_GRAPH];
        for(int i = 0; i < SIZE_OF_GRAPH; i++) {
            for(int j = 0; j < SIZE_OF_GRAPH; j++) {
                if(i == j) {
                    // 自己跟自己设为 0
                    graph[i][j] = 0;
                } else {
                    // 其他的都暂且设为无穷大（就假装9999是无穷大叭）
                    graph[i][j] = 9999;
                }
            }
        }

        System.out.println("请输入节点间的连线个数：");
        int size = scanner.nextInt();
        System.out.println("请输入各对相连节点 ：");
        int node1, node2;
        for(int i = 0; i < size; i++) {
            node1 = scanner.nextInt();
            node2 = scanner.nextInt();
            // 因为是无向图嘛（就是连线间没有箭头的那种），所以要改变顺序再来一次
            graph[node1][node2] = 1;
            graph[node2][node1] = 1;
        }

        // 初始化一下 book 数组，并将零号节点标记为1
        book = new int[SIZE_OF_GRAPH];
        book[0] = 1;
        // 然后就可以开始啦！
        System.out.println("图的深度遍历顺序如下");
        dfs(0);
    }

    private static void dfs(int current) {
        System.out.print(current + " ");

        // 从 0 开始计数已访问过的节点数
        int sum = 0;
        // 当 sum 达到节点总数，即所有节点都访问过时
        if(sum == SIZE_OF_GRAPH) {
            // 直接退出
            return;
        }

        // 遍历图中每个节点
        for(int i = 0; i < SIZE_OF_GRAPH; i++) {
            // 若当前节点与节点i相连，且节点i被访问过
            if(graph[current][i] == 1 && book[i] == 0) {
                // 访问节点i，并将其标记为1
                book[i] = 1;
                // 将 i 视为当前节点，递归遍历
                dfs(i);
            }
        }
    }
}
