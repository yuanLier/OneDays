package _20181029.floydWarshall;

import java.util.Scanner;

/**
 * 只有五行的算法 —— FloydWarshall(???)
 *
 * 题目描述 ：在对时间复杂度要求不高的情况下，求任意两点间的最短距离
 *
 * 基本思路 ：
 *      1、如果 A 与 B 之间只有直达距离，
 *          最短路径就没得挑，即为 A->B 的这个距离
 *      2、如果 A 与 B 之间还能经过 C，
 *          最短距离就可以在 1 的基础上，即 A->B直达 和 A->中转C->B 中挑一个
 *          for(int i = 0; i < SIZE_OF_GRAPH; i++) {
 *              for(int j = 0; j < SIZE_OF_GRAPH; j++) {
 *                  // 若直达距离大于中转距离
 *                  if(graph[i][j] > graph[i][C.num] + graph[C.num][j]) {
 *                      // 则将最短距离由直达距离替换为中转距离之和
 *                      graph[i][j] = graph[i][C.num] + graph[C.num][j];
 *                  }
 *              }
 *          }
 *      3、如果 A 与 B 之间还能经过 C 和 D，
 *          最短距离就能在 2 的基础上，再加入一个中转点 D
 *          for(int i = 0; i < SIZE_OF_GRAPH; i++) {
 *              for(int j = 0; j < SIZE_OF_GRAPH; j++) {
 *                  // 若当前最小距离大于中转距离
 *                  if(graph[i][j] > graph[i][D.num] + graph[D.num][j]) {
 *                      // 则将最短距离由直达距离替换为中转距离之和
 *                      graph[i][j] = graph[i][D.num] + graph[D.num][j];
 *                  }
 *              }
 *          }
 *      4、同理，再陆续加上其他点。。就直接在最外层套个for循环啦！
 *          核心代码如下（只有五行五行五行！但是时间复杂度不算低就是了） ：
 *          for(int k = 0; k < SIZE_OF_GRAPH; k++) {
 *              for(int i = 0; i < SIZE_OF_GRAPH; i++) {
 *                  for(int j = 0; j < SIZE_OF_GRAPH; j++) {
 *                      if(graph[i][j] > graph[i][k] + graph[k][j]) {
 *                          graph[i][j] = graph[j][k] + graph[k][j];
 *                      }
 *                  }
 *              }
 *          }
 *
 * 注意事项 ：
 *      graph[i][j] 中存放的就直接是当前最短距离
 *
 * 时间复杂度 ：O（N^3）
 *      毕竟嵌了三层循环嘛
 *
 * 输出示例 ：
 *      请输入图中节点数量 ：
 *      4
 *      请输入节点间的连线个数：
 *      8
 *      请输入相连城市及其距离 ：
 *      0 1 2
 *      0 2 6
 *      0 3 4
 *      1 2 3
 *      2 0 7
 *      2 3 1
 *      3 0 5
 *      3 2 12
 *      整理出来是这样的 ：
 *      0号城市到1号城市的最短距离为 ：2
 *      0号城市到2号城市的最短距离为 ：5
 *      0号城市到3号城市的最短距离为 ：4
 *      1号城市到0号城市的最短距离为 ：9
 *      1号城市到2号城市的最短距离为 ：3
 *      1号城市到3号城市的最短距离为 ：4
 *      2号城市到0号城市的最短距离为 ：6
 *      2号城市到1号城市的最短距离为 ：8
 *      2号城市到3号城市的最短距离为 ：1
 *      3号城市到0号城市的最短距离为 ：5
 *      3号城市到1号城市的最短距离为 ：7
 *      3号城市到2号城市的最短距离为 ：10
 */

public class DemoFloydWarshall {
    private static int SIZE_OF_GRAPH;
    private static int[][] graph;
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
            // 注意有向图的话，只用设置一次就好了
            // 这里就直接设为两地之间的距离
            graph[node1][node2] = distance;
        }

        // 调用一下这个算法
        floydWarshall();

        // 输出
        System.out.println("整理出来是这样的 ：");
        for(int i = 0; i < SIZE_OF_GRAPH; i++) {
            for(int j = 0; j < SIZE_OF_GRAPH; j++) {
                // 排除自己到自己
                if (i != j) {
                    System.out.println(i + "号城市到" + j + "号城市的最短距离为 ：" + graph[i][j]);
                }
            }
        }
    }

    /**
     * 虽然它只有五行，但还是单独拿出来叭
     */
    private static void floydWarshall() {
        for(int k = 0; k < SIZE_OF_GRAPH; k++) {
            for(int i = 0; i < SIZE_OF_GRAPH; i++) {
                for(int j = 0; j < SIZE_OF_GRAPH; j++) {
                    if(graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }
}
