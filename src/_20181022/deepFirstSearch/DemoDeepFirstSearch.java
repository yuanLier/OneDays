package _20181022.deepFirstSearch;

import java.util.Scanner;

/**
 * 深度优先搜索
 *
 * 题目描述 ：请用深度优先搜索的方式，写一个全排列（强行dfs 嘿嘿嘿
 *
 * 基本思路 ：
 *      1、就把全排列看作往小盒子里丢扑克牌嘛，对每个盒子都先丢手里最小那张牌，然后求出所有的丢法
 *      2、首先要进行边界判断 ：
 *          如果要对 N 求全排列的话，就假设我们有 N+1 个盒子，那么每次站到 N+1 号小盒子面前的时候，就说明这一个排列结束了
 *      3、然后对于每一次选择（当前站到的是哪个盒子(box[step])、往这个盒子里丢哪张卡(book[i])）：
 *          a) 从小到大遍历所有牌，若扑克牌 i 还在手上，就将它放到当前盒子里，即 box[step] = i;
 *          b) 然后将 book[i] 标记为 1，表示手上已经没有 i 这张牌了
 *          c) 然后继续处理下一个盒子，就是用刚刚那个函数递归一下，直接调用dfs(step+1);
 *          d) 最后（即递归结束后）记得再让 book[i] = 0，毕竟还要把扑克牌收回去进行下一轮排列嘛
 *
 *      其实主要就在于这个dfs函数啦！
 *      想好每一步要做什么，然后递归到下一步，最后判断一下边界条件就好啦 比如这样 ：
 *
 *      void dfs(int step) {
 *          判断边界
 *          尝试每种可能 for(int i = 0; i < n; i++) {
 *              继续下一步 dfs(step + 1);
 *          }
 *          返回
 *      }
 *
 * 注意事项 ：
 *      1、扑克牌上的数字只有 1-9 哦！毕竟扑克牌和盒子只是个类比，全排列是从 1 到 N 的嘛
 *      2、box[] 和 book[] 不能直接定义在 dfs 函数里面，最好写成静态变量，不然每次迭代都会重新初始化，就前功尽弃了嘤
 *
 * 输出示例 ：
 *      请输入全排列里边儿那个数字 ：
 *      3
 *      全排列得到的结果为 ：
 *      123
 *      132
 *      213
 *      231
 *      312
 *      321
 */

public class DemoDeepFirstSearch {
    // 盒子的个数定为 N
    private static int N;
    // box[step] 就表示执行这一步时，面前的是哪个盒子
    private static int[] box;
    // book[i] 就表示，这张扑克牌（扑克牌i）还在不在手上
    private static int[] book;

    public static void main(String[] args) {
        System.out.println("请输入全排列里边儿那个数字 ：");
        Scanner scanner = new Scanner(System.in);

        // 给静态变量初始化一下
        N = scanner.nextInt();
        box = new int[N+1];
        book = new int[N+1];

        System.out.println("全排列得到的结果为 ：");
        // 然后从第一个小盒子开始递归就好啦
        dfs(1);
    }

    /**
     * 这就是传说中的深度优先搜索辣！
     * @param step 传入当前步骤
     */
    private static void dfs(int step) {

        // 如果已经到了最后一个盒子的后面那个盒子面前，结束循环
        if(step == N+1) {
            // 输出该轮得到的那种排列
            for(int i = 1; i <= N; i++) {
                System.out.print(box[i]);
            }
            System.out.println();

            // 返回之前的一步（即上一个调用 dfs 函数的地方）
            return;
        }

        // i 表示扑克牌上的数字（要注意只有 1-9 哦）
        for(int i = 1; i <= N; i++) {
            // 如果扑克牌 i 还在手上
            if(book[i] == 0) {
                // 就把这张牌丢到面前的盒子里面去
                box[step] = i;
                // 然后把它的值设为 1，表示这张牌已经不在手上了
                book[i] = 1;

                // 然后就走到下一个盒子面前重复之前的步骤啦！让我们递归一下
                dfs(step+1);

                // 走到这里就说明已经从递归中崩出来了，这时候该回收扑克牌然后往回走了
                // 所以就再把标志值设为 0，表示这张牌又在手上了
                book[i] = 0;
            }
        }

    }

}
