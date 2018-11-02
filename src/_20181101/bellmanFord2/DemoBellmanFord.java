package _20181101.bellmanFord2;

import java.util.Scanner;

/**
 * Bellman-Ford 的简单优化
 *
 * 题目描述 ：
 *      1、检测负权回路
 *      2、简单优化一下，让它能提前跳出松弛循环
 *
 * 基本思路 ：
 *      1、检测负权回路 ：
 *          若在进行了 SIZE_OF_GRAPH-1 轮松弛之后，仍可继续成功松弛，说明存在负权回路
 *      2、让它能够提前跳出松弛循环 ：
 *          用一个 bak 数组来备份 dis，然后每次松弛后比较一下当前 dis 和之前那个 bak
 *          如果比较发现两个数组相等，就说明在刚才的松弛中，数组 dis 没有发生变化
 *          就说明全部最短路径已经计算出来啦，这时候我们就可以放心大胆地跳出循环 φ(>ω<*)
 *
 * 注意事项 ：
 *      如果存在负权回路，就一定会进行 SIZE_OF_GRAPH-1 轮松弛
 *      如果不需要进行 SIZE_OF_GRAPH-1 轮松弛，就可以提前结束循环
 *      所以说到底这个优化只是针对没有负权回路的啦 (〃'▽'〃)
 *
 * 注意事项plus ：
 *      负权边 和 负权回路 是不一样的！
 *      负权回路是指，不仅含有负权边，而且负权边还组成了回路
 *      所以它的每轮松弛最小距离都会再变小，就永远没有最小距离啦
 *
 * 输出示例 ：
 *      1、含有负权回路 ：
 *          请输入图中节点数量 ：
 *          3
 *          请输入节点间的连线个数：
 *          3
 *          请输入相连城市及其距离 ：
 *          0 1 2
 *          1 2 3
 *          2 0 -6
 *          请输入指定城市的编号 ：
 *          0
 *          该图中含有负权回路，无最短距离
 *      2、不含负权回路 ：
 *          请输入图中节点数量 ：
 *          5
 *          请输入节点间的连线个数：
 *          5
 *          请输入相连城市及其距离 ：
 *          1 2 2
 *          0 1 -3
 *          0 4 5
 *          3 4 2
 *          2 3 3
 *          请输入指定城市的编号 ：
 *          0
 *          共进行了3轮循环 ：
 *          该城市到1号城市的最短距离为 ：-3
 *          该城市到2号城市的最短距离为 ：-1
 *          该城市到3号城市的最短距离为 ：2
 *          该城市到4号城市的最短距离为 ：4
 *
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
    // 备份 dis，用来判断是否已经松弛完毕
    private static int[] bak;
    // 无穷大
    private static final int BIIIIIIG_NUM = 99999999;

    // 判断是否含有负权回路（默认为false）
    private static boolean minusLooping = false;
    // 记录松弛循环的次数
    private static int times = 0;

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
        // 初始化 bak 和 dis
        bak = new int[SIZE_OF_GRAPH];
        dis = new int[SIZE_OF_GRAPH];
        for(int i = 0; i < SIZE_OF_GRAPH; i++) {
            dis[i] = BIIIIIIG_NUM;
        }
        dis[startNum] = 0;

        // 调用
        bellmanFord();

        // 判断负权回路
        minusLoop();

        // 若不含负权回路
        if (!minusLooping) {
            // 输出
            System.out.println("共进行了" + times + "轮循环 ：");
            for(int i = 0; i < SIZE_OF_GRAPH; i++) {
                // 排除自己到自己
                if (i != startNum) {
                    System.out.println("该城市到" + i + "号城市的最短距离为 ：" + dis[i]);
                }
            }
        }
    }

    /**
     * 带一点优化的 Bellman-Ford
     */
    private static void bellmanFord() {
        // 松弛循环
        for(int i = 0; i < SIZE_OF_GRAPH-1; i++) {

            // 备份 dis
            for(int j = 0; j < SIZE_OF_SIDE; j++) {
                bak[j] = dis[j];
            }

            // 进行新一轮的松弛
            for(int j = 0; j < SIZE_OF_SIDE; j++) {
                if(dis[v[j]] > dis[u[j]] + w[j]) {
                    dis[v[j]] = dis[u[j]] + w[j];
                }
            }

            // 判断松弛后 dis 是否发生改变
            boolean isEqual = true;
            for(int j = 0; j < SIZE_OF_GRAPH; j++) {
                // 若 bak 和 dis 存在不相等
                if(bak[j] != dis[j]) {
                    // 标记为不相等，结束判断
                    isEqual = false;
                    break;
                }
            }

            // 若未发生改变
            if(isEqual) {
                // 直接跳出松弛循环
                break;
            }

            // 松弛循环轮数+1
            times++;
        }
    }

    /**
     * 检测负权回路
     */
    private static void minusLoop() {
        for(int i = 0; i < SIZE_OF_SIDE; i++) {
            if(dis[v[i]] > dis[u[i]] + w[i]) {
                minusLooping = true;
            }
        }

        if(minusLooping) {
            System.out.println("该图中含有负权回路，无最短距离");
        }
    }
}