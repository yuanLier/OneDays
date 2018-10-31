package _20181031.bellmanFord;

import java.util.Scanner;

/**
 * Bellman-Ford —— 解决负权边
 *
 * 题目描述 ：求出指定点到其余各点的最短距离（注 ：含有负权边）
 *
 * 基本思路 ：
 *      就是对每条边反复进行松弛，最多循环 SIZE_OF_GRAPH-1 轮就能出结果啦
 *      核心代码如下 ：
 *      for(int i = 0; i < SIZE_OF_GRAPH-1; i++) {
 *          for(int j = 0; j < SIZE_OF_SIDE; j++) {
 *              if(dis[v[j]] > dis[u[j]] + w[j]) {
 *                  dis[v[j]] = dis[u[j]] + w[j];
 *              }
 *          }
 *      }
 *
 * 注意事项 ：
 *      1、不把边所代表的数看作两地间的距离，而是看作从某地去往某地的代价（权值），负权边就好理解多了 ：
 *          就比如说 cost(a, b)可以理解成开车从 a 到 b 的油耗，那么如果路上有个加油站的话，油耗就可以为负的
 *      2、不需要把图映射成二位数组了，直接用 u[i]、v[i]、w[i] 三个数组来存储节点之间的权值，就比如说 ：
 *          第 i 条边存储在 u[i]、v[i]、w[i] 中，表示从 u[i] 到 v[i] 的权值为 w[i]
 *      3、dis 数组就还是用来存放指定点到其余各点的距离（初始均为无穷大）
 *
 * 输出示例 ：
 *      请输入图中节点数量 ：
 *      5
 *      请输入节点间的连线个数：
 *      5
 *      请输入相连城市及其距离 ：
 *      1 2 2
 *      0 1 -3
 *      0 4 5
 *      3 4 2
 *      2 3 3
 *      请输入指定城市的编号 ：
 *      0
 *      整理出来是这样的 ：
 *      该城市到1号城市的最短距离为 ：-3
 *      该城市到2号城市的最短距离为 ：-1
 *      该城市到3号城市的最短距离为 ：2
 *      该城市到4号城市的最短距离为 ：4
 */

public class DemoBellmanFord {
    // 图中节点个数
    private static int SIZE_OF_GRAPH;
    // 节点之间连线个数
    private static int SIZE_OF_SIDE;
    // 用 u、v、w 三个数组共同存储节点之间的权值信息
    private static int[] u;
    private static int[] v;
    private static int[] w;
    // 存放指定顶点到其他各顶点的路程
    private static int[] dis;
    // 无穷大
    private static final int BIIIIIIG_NUM = 99999999;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入图中节点数量 ：");
        SIZE_OF_GRAPH = scanner.nextInt();


        System.out.println("请输入节点间的连线个数：");
        SIZE_OF_SIDE = scanner.nextInt();
        // 初始化三个数组
        u = new int[SIZE_OF_SIDE];
        v = new int[SIZE_OF_SIDE];
        w = new int[SIZE_OF_SIDE];
        System.out.println("请输入相连城市及其距离 ：");
        for(int i = 0; i < SIZE_OF_SIDE; i++) {
            u[i] = scanner.nextInt();
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }

        System.out.println("请输入指定城市的编号 ：");
        int startNum = scanner.nextInt();
        // 初始化 dis 数组
        dis = new int[SIZE_OF_GRAPH];
        for(int i = 0; i < SIZE_OF_GRAPH; i++) {
            dis[i] = BIIIIIIG_NUM;
        }
        dis[startNum] = 0;

        // 调用
        bellmanFord();

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
     * 拎出来拎出来
     */
    private static void bellmanFord() {
        for(int i = 0; i < SIZE_OF_GRAPH-1; i++) {
            for(int j = 0; j < SIZE_OF_SIDE; j++) {
                if(dis[v[j]] > dis[u[j]] + w[j]) {
                    dis[v[j]] = dis[u[j]] + w[j];
                }
            }
        }
    }
}
