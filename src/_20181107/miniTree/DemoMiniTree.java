package _20181107.miniTree;

import java.util.Scanner;

/**
 * 图的最小生成树
 *
 * 题目描述 ：找出能把一张图中各个点连通的且距离最短的最小边们
 *
 * 基本思路 ：
 *      1、首先按照边的权值进行从小到大的排序
 *      2、每次从剩余边中选择权值较小且不会对已有树造成回路的边加入到生成树中去
 *      3、就这样加呀加呀一直加到有 n+1 条边了，我们的算法就完成啦
 *
 * 注意事项 ：
 *      1、为了降低时间复杂度，选用快速排序
 *      2、判断是否会造成回路的时候，用并查集就好啦 ：
 *          若这条边的两个顶点有共同的老大，就说明会造成回路，需要舍弃
 *
 * 时间复杂度 ：O（MlogM）
 *      对边进行快排的时间复杂度为 O（MlogM），在 m 条边中找出 n-1 条边是 O（MlogN）
 *      故该算法的时间复杂度为 O（MlogM + MlogN）；
 *      又 m 一般比 n 大很多，故最终的时间复杂度为 O（MlogM）
 *
 * 输出示例 ：
 *      请输入图中的节点个数 ：
 *      6
 *      请输入节点间的连线个数 ：
 *      9
 *      请输入节点间的连线情况 ：
 *      1 3 11
 *      2 4 13
 *      3 5 3
 *      4 5 4
 *      1 2 6
 *      3 4 7
 *      0 1 1
 *      2 3 9
 *      0 2 2
 *      实现最短连通后，总路径为 ：19
 */


public class DemoMiniTree {
    // 节点数量
    private static int SIZE_OF_NODE;
    // 节点间连线关系的数量
    private static int SIZE_OF_HINT;
    // 记录节点属于哪一个老大
    private static int[] boss;
    // 记录间的连线
    private static Connect[] connects;
    // 记录已经选中的连线数量
    private static int count = 0;
    // 记录连通后的总路程
    private static int total = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入图中的节点个数 ：");
        SIZE_OF_NODE = scanner.nextInt();
        System.out.println("请输入节点间的连线个数 ：");
        SIZE_OF_HINT = scanner.nextInt();

        // 初始化连线组并读入连线情况
        System.out.println("请输入节点间的连线情况 ：");
        connects = new Connect[SIZE_OF_HINT];
        for(int i = 0; i < SIZE_OF_HINT; i++) {
            connects[i] = new Connect();
            connects[i].start = scanner.nextInt();
            connects[i].end = scanner.nextInt();
            connects[i].distance = scanner.nextInt();
        }

        // 将连线情况按路径从小到大排序
        quickSort(0, SIZE_OF_HINT-1);

        boss = new int[SIZE_OF_NODE];
        for(int i = 0; i < SIZE_OF_NODE; i++) {
            boss[i] = i;
        }

        // 选择路线
        kruskal();

        // 打印
        System.out.println("实现最短连通后，总路径为 ：" + total);
    }

    /**
     * 从上往下选择 总节点数-1 条不会造成回路的线路
     */
    private static void kruskal() {
        // 从小到大枚举每条边
        for(int i = 0; i < SIZE_OF_HINT; i++) {
            // 若两个节点不再同一集合中，可以连线
            if(merge(connects[i].start, connects[i].end)) {
                // 连线数量+1
                count++;
                // 将这条连线的距离加到总路程里面去
                total += connects[i].distance;
            }

            // 当已经选中的线路数量达到总结点数-1
            if(count == SIZE_OF_NODE-1) {
                // 跳出循环
                break;
            }
        }
    }

    /**
     * 快排（从小到大）
     * @param left
     * @param right
     */
    private static void quickSort(int left, int right) {
        if(right <= left) {
            return;
        }

        // 将最左边线路设为基准线路，再设置左右小人
        Connect baseConn = connects[left];
        int i = left, j = right;

        // 若 i 与 j 还没相遇，持续循环
        while(i != j) {
            // 基准线路设为最左的话，就让右边小人先走
            while(connects[j].distance >= baseConn.distance && i < j) {
                // 若右边小人还没有找到比基准线路短的线路，且仍然在右边小人左边，就让它持续向左走
                j--;
            }

            // 跳出上面那个while了就说明此时右边小人已经找到比基准线路小的了
            while(connects[i].distance <= baseConn.distance && i < j) {
                // 然后同理，我们就可以让左边小人往右走啦
                i++;
            }

            // 跳出刚刚那个while循环的时候i是有可能等于j的，这里判断一下就可以少操作一次
            if (i < j) {
                // 如果这两个小人还没有碰面，就把它们所在的线路交换
                Connect temp = connects[i];
                connects[i] = connects[j];
                connects[j] = temp;
            }
        }

        // 跳出while循环说明现在两个小人已经站到一起去了
        // 将这个数和基准线路（要注意基准线路其实就是connects[left]）交换
        connects[left] = connects[i];
        connects[i] = baseConn;

        // 进行下一轮迭代
        quickSort(left, i-1);
        quickSort(i+1, right);
    }

    /**
     * 合并节点；判断节点能否合并
     * @param x
     * @param y
     * @return 若两个点在同一个集合中（有同样的老大），不可连线，返回false
     */
    private static boolean merge(int x, int y) {
        int boss1, boss2;
        boss1 = getBoss(x);
        boss2 = getBoss(y);
        if(boss1 != boss2) {
            boss[boss2] = boss1;
            // 若两点不再同一个集合，可以连线，返回true
            return true;
        } else {
            // 若两个点已经在同一个集合中了，返回false
            return false;
        }
    }

    /**
     * 寻找最终老大
     * @param i
     * @return
     */
    private static int getBoss(int i) {
        if(boss[i] == i) {
            return i;
        } else {
            boss[i] = getBoss(boss[i]);
            return boss[i];
        }
    }
}
