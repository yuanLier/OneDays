package _20181030.dijkstra;

import java.util.Scanner;

/**
 * Dijkstra算法 —— 通过边实现松弛
 *
 * 题目描述 ：求出指定点到其余各顶点的最短距离
 *
 * 基本思路 ：
 *      1、首先需要一个数组 dis[]，用来存储指定点（A）到其余各定顶点的直达距离
 *      2、然后找到与其距离最小的顶点（B），则该直达路径即为 A 到 B 的最短路径
 *          （因为从 A 出发去往其他顶点都比从 A 出发去往 B 要远嘛，就没必要再中转了）
 *      3、将它记录下来（book[B.num] = 1），然后对它的的所有出边进行松弛 ：
 *          a）首先看看从它出发能直达哪些顶点（就比如说能直达的是 C 和 D 吧）
 *          b）然后判断 A->中转B->C 的距离是否小于 A->C直达（graph[A][B] + graph[B][C] < dis[C] ?）
 *          c）如果小于的话，就更新 dis（if => dis[C.num] = graph[A.num][B.num] + graph[B.num][C.num]）
 *          d）再对 D 进行如上操作，我们对 B 点所有出边的松弛就结束啦
 *      4、然后找出剩余点（即 book[X] == 0 的点）中，与指定点（A）距离最小的，重复第三步
 *      5、最后，当所有点都被 book 为 1 了，dis[] 里面存的就都是最短距离啦
 *
 * 注意事项 ：
 *      1、出边指的是从某一顶点出发到达与它直连的各个顶点，这么一个过程
 *      2、松弛指的是将 dis[X] 由直达距离更新为中转距离的过程（相当于拉松了嘛）
 *      3、对于边数 M 小于 N^2 的稀疏图来说，其实最好选用邻接表来代替邻接矩阵，
 *          使得时间复杂度优化到 O(M+N)logN；但是我没看懂XD 就算了啦 诶嘿嘿嘿
 *
 * 时间复杂度 ：O（N^2）
 *      可以用 "堆" 优化到 O（logN），也可以用邻接表优化到 O(M+N)logN
 *      （不过用邻接表的话，最坏情况即是 M == N^2，这样 (M+N)logN 是要比 N^2 还大的）
 *
 * 输出示例 ：
 *      请输入图中节点数量 ：
 *      6
 *      请输入节点间的连线个数：
 *      9
 *      请输入相连城市及其距离 ：
 *      0 1 1
 *      0 2 12
 *      1 2 9
 *      1 3 3
 *      2 4 5
 *      3 2 4
 *      3 4 13
 *      3 5 15
 *      4 5 4
 *      请输入指定城市的编号 ：
 *      0
 *      整理出来是这样的 ：
 *      该城市到1号城市的最短距离为 ：1
 *      该城市到2号城市的最短距离为 ：8
 *      该城市到3号城市的最短距离为 ：4
 *      该城市到4号城市的最短距离为 ：13
 *      该城市到5号城市的最短距离为 ：17
 */

public class DemoDijkstra {
    private static int SIZE_OF_GRAPH;
    private static int[][] graph;
    private static int[] book;
    // 存放指定顶点到其他各顶点的初始路程（直达路程）
    private static int[] dis;
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
        System.out.println("请输入相连城市及其距离 ：");
        int node1, node2, distance;
        for(int i = 0; i < size; i++) {
            node1 = scanner.nextInt();
            node2 = scanner.nextInt();
            distance = scanner.nextInt();
            // 这里就直接设为两地之间的距离
            graph[node1][node2] = distance;
        }

        System.out.println("请输入指定城市的编号 ：");
        int startNum = scanner.nextInt();
        // 初始化 dis 数组（即存入start节点到各地的直达距离）
        dis = new int[SIZE_OF_GRAPH];
        for(int i = 0; i < SIZE_OF_GRAPH; i++) {
            dis[i] = graph[startNum][i];
        }

        // 初始化 book 数组，并将start节点标记为1
        book = new int[SIZE_OF_GRAPH];
        book[startNum] = 1;

        // 调用一下这个算法
        dijkstra();

        // 输出
        System.out.println("整理出来是这样的 ：");
        for(int i = 0; i < SIZE_OF_GRAPH; i++) {
            // 排除自己到自己
            if (i != startNum) {
                System.out.println("该城市到" + i + "号城市的最短距离为 ：" + dis[i]);
            }
        }
    }

    /**
     * 就也还是单独拎出来
     */
    private static void dijkstra() {

        // 遍历各个顶点（-1 是因为最后一个顶点不用判断，直接随着倒数第二个顶点被确定了）
        for(int i = 0; i < SIZE_OF_GRAPH-1; i++) {

            // 找到离指定顶点最近的顶点，并用 current 标记其编号
            int min = BIIIIIIG_NUM;
            int current = 0;
            for(int j = 0; j < SIZE_OF_GRAPH; j++) {
                // 若顶点 j 未被标记过且指定顶点到 j 的距离小于当前最小距离
                if(book[j] == 0 && dis[j] < min) {
                    min = dis[j];
                    current = j;
                }
            }

            // 顶点current即是当前距离指定顶点最近的顶点，标记它
            book[current] = 1;

            // 遍历，找到与current相连的顶点
            for(int j = 0; j < SIZE_OF_GRAPH; j++) {
                // 对于每个与current有相连的顶点 j
                if(graph[current][j] != BIIIIIIG_NUM) {
                    // 若 start->中转current->j 的距离小于 start->j直达 的距离
                    if(dis[current] + graph[current][j] < dis[j]) {
                        // 则将最短距离由直达距离更新为中转距离之和
                        dis[j] = dis[current] + graph[current][j];
                    }
                }
            }
        }

    }
}
