package _20181121.bestDistribution;

import java.util.Scanner;

/**
 * 二分图的最大分配
 *
 * 题目描述 ：找到二分图的最大匹配方案叭！
 *
 * 基本思路 ：
 *      1、首先还是把图映射为一张二维数组 graph[][]
 *      2、然后要用一个一维数组 match[] 来储存配对情况
 *      3、然后的然后用一个 book[] 记录每个节点是否有被访问到
 *      4、然后就就随便挑个节点再随便挑一条相连的线就好了鸭
 *      5、若这个节点为被匹配过，就匹配他俩；若这个节点已经有匹配的了，
 *          就去问问它的匹配对象有没有其他候选人（也就是我们的迭代啦）
 *      6、如果刚才那条边匹配失败，就换一条边继续尝试，直到这个节点找到匹配对象；
 *          （如果尝试完所有边都没法匹配，就不管它了啦，直接进行下一个节点的探索就好啦）
 *
 * 注意事项 ：
 *      1、二分图 ：
 *          就是能满足把一个图中的节点分成两坨，并且每坨内部的节点互不相连，这样的一个图
 *      2、增广路 ：
 *          其实简单来说，指的就是二分图的一个当前有效分配
 *
 * 输出示例 ：
 *      请输入二分图中的节点个数 ：
 *      6
 *      请输入节点间的连线个数 ：
 *      5
 *      请输入各节点之间的连线关系 ：
 *      0 3
 *      0 4
 *      1 4
 *      1 5
 *      2 3
 *      最大匹配数为 ：3
 */

public class DemoBestDistribution {
    // 节点个数
    private static int SIZE_OF_NODE;
    // 连线个数
    private static int SIZE_OF_EDGE;
    // 储存这个图
    private static int[][] graph;
    // 储存匹配情况
    private static int[] match;
    // 储存节点的访问情况
    private static int[] book;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入二分图中的节点个数 ：");
        SIZE_OF_NODE = scanner.nextInt();

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

        // 开始匹配
        int sum = 0;
        match = new int[SIZE_OF_NODE];
        book = new int[SIZE_OF_NODE];
        for(int i = 0; i < SIZE_OF_NODE; i++) {
            // 情况上一次搜索时做的标记（就相当于把用二维存储标记压缩成用一维存储了嘛）
            for(int j = 0; j < SIZE_OF_NODE; j++) {
                book[j] = 0;
            }
            // 若匹配成功（即找到新的增广路）
            if(dfs(i)) {
                // 匹配总数加一
                sum++;
            }
        }

        // 输出结果
        System.out.println("最大匹配数为 ：" + sum);
    }

    /**
     * 匹配
     * @param u 被选中的节点
     * @return 若找到与之匹配的节点，返回true；反之，返回false
     */
    private static boolean dfs(int u) {
        // 遍历各个节点
        for(int i = 0; i < SIZE_OF_NODE; i++) {
            // 若节点i未访问过，且与被选中的节点有连线
            if(book[i] == 0 && graph[u][i] == 1) {
                // 将节点i标记为已访问
                book[i] = 1;
                // 若i未被配对或找到了新的配对
                if(match[i] == 0 || dfs(match[i])) {
                    // 更新配对关系
                    match[i] = u;
                    match[u] = i;
                    return true;
                }
            }
        }
        return false;
    }
}
