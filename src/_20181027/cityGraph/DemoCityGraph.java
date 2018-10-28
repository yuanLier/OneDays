package _20181027.cityGraph;

import java.util.Scanner;

/**
 *  城市地图 —— 图的深度优先遍历
 *
 *  题目描述 ：深度优先遍历求一下指定城市的最短距离
 *
 *  基本思路 ：
 *      1、就是在深度优先遍历的思想上加上了一个两地之间的距离 distance，和用来储存最短路径的全局变量 min
 *      2、graph[node1][node2]的值不再设为 1，而是直接设置为两地之间的 distance，然后探索节点的时候顺便把距离相加（sum）
 *      3、使每一次dfs接收一个 sum 参数，表示探索到此节点时已经走过的路（当然如果 sum 已经大于当前 min 了，就可以直接退出）
 *      4、然后基本上就和之前差不多了啦（还有还有一点！要注意到这是一个有向图）
 *
 *  注意事项 ：
 *      为什么这个和全排列那个都要把节点再取出来，而之前那个图的遍历就没有取呢？
 *          -- 因为那个图的遍历遍历的其实是节点啦！它的目的只是找出无数条路中的第一条来
 *          而全排列和这个的需求其实是找出所有路来，是遍历的整个图，考虑出所有的可能情况
 *          所以这两个的节点是需要重复遍历的，所以递归完之后一定要记得取出来呀 ( • ̀ω•́ )✧
 *
 *  输出示例 ：
 *      请输入图中节点数量 ：
 *      5
 *      请输入节点间的连线个数：
 *      8
 *      请输入相连城市及其距离 ：
 *      0 1 2
 *      0 4 10
 *      1 2 3
 *      1 4 7
 *      2 0 4
 *      2 3 4
 *      3 4 5
 *      4 2 3
 *      请分别输入出发城市与目标城市的编号 ：
 *      0 4
 *      两座城市之间的最小路径为 ：9
 */

public class DemoCityGraph {
    private static int SIZE_OF_GRAPH;
    private static int[][] graph;
    private static int[] book;
    // 最小路径
    private static int min;
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

        System.out.println("请分别输入出发城市与目标城市的编号 ：");
        int start = scanner.nextInt();
        int end = scanner.nextInt();

        // 初始化一下 book 数组，并将start节点标记为1
        book = new int[SIZE_OF_GRAPH];
        book[start] = 1;

        // 最后开始递归
        min = BIIIIIIG_NUM;
        dfs(start, 0, end);

        System.out.println("两座城市之间的最小路径为 ：" + min);
    }

    /**
     * dfs
     * @param current 当前节点
     * @param sum 已经走过的路程
     * @param end 目标城市的编号
     */
    private static void dfs(int current, int sum, int end) {
        // 如果已走过的路程已经大于当前最小值了，直接返回
        if(sum > min) {
            return;
        }

        // 若已经到达目标城市
        if(current == end) {
            // 若总距离小于当前最小距离
            if(sum < min) {
                // 更新最小距离
                min = sum;
            }
            // 然后直接返回
            return;
        }

        // 对于每个当前城市，从 0 到 end 开始遍历
        for(int i = 0; i <= end; i++) {
            // 若当前城市与城市i相连，且i未被探索过
            if(graph[current][i] != BIIIIIIG_NUM && book[i] == 0) {
                // 探索它
                book[i] = 1;
                // 然后从它出发，继续寻找目标城市
                dfs(i, sum + graph[current][i], end);
                // 最后要记得把城市取出来
                book[i] = 0;
            }
        }
    }
}
