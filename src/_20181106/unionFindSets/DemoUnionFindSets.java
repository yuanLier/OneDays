package _20181106.unionFindSets;

import java.util.Scanner;

/**
 * 树的并查集
 *
 * 题目描述 ：根据犯罪团伙之间的关系确定有几个犯罪集团
 *
 * 基本思路 ：
 *      1、用一个一维数组存储各个犯罪团伙（boss[]）
 *      2、其数值就代表这个团伙的老大是哪个团伙（初始为自己，即boss[i] = i）
 *      3、根据给出的团伙之间的关系，更新老大信息 ：
 *          就比如说，给出关系 团伙2和团伙6有勾结，则将6的老大改为2（boss[6] = 2）
 *          备注 ：这里要有一个固定的顺序（好确定是让 boss[6] = 2 还是让 boss[2] = 6）
 *          这里就人为规定后一个团伙的老大是前一个团伙好啦，其实都一样的
 *      4、然后在归顺过程中要注意的一点是，如果它的老大是自己，就让它直接归顺
 *          若它的老大不是它自己，就让它的老大（又或许是老大的老大的老大）归顺
 *      5、就这样依照团伙之间关系更新完之后，重新遍历整个数组，
 *          若某个数的老大依然是它自己，那么它就成功升级为一个犯罪集团的老大了
 *          然后只有数出共有几个集团老大，就知道共有几个犯罪集团啦
 *
 * 注意事项 ：
 *      getBoss(int i) 找到的直接就是 i 的顶头上司啦，这样也好直接判断是不是在一个集团内的嘛
 *      然后 boss[i] = getBoss(boss[i]) 不仅是找出最大的boss，还顺带把沿路团伙的数值改为他的老大了
 *      这样就减少了后期顺藤摸瓜的次数，加快了找出最大boss的速度
 *
 * 输出示例 ：
 *      请输入团伙总数 ：
 *      10
 *      请输入团伙间关系的个数 ：
 *      9
 *      请输入团伙之间的关系 ：
 *      0 1
 *      2 3
 *      4 1
 *      3 5
 *      1 5
 *      7 6
 *      8 6
 *      0 5
 *      1 3
 *      经统计，共有3个犯罪集团
 *
 */
public class DemoUnionFindSets {
    // 团伙数量
    private static int SIZE_OF_NODE;
    // 团伙间关系的数量
    private static int SIZE_OF_HINT;
    // 下标表示团伙，数值表示团伙的老大（初始均为自己）
    private static int[] boss;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入团伙总数 ：");
        SIZE_OF_NODE = scanner.nextInt();
        // 初始化团伙
        boss = new int[SIZE_OF_NODE];
        for(int i = 0; i < SIZE_OF_NODE; i++) {
            boss[i] = i;
        }

        System.out.println("请输入团伙间关系的个数 ：");
        SIZE_OF_HINT = scanner.nextInt();
        System.out.println("请输入团伙之间的关系 ：");
        int node1, node2;
        for(int i = 0; i < SIZE_OF_HINT; i++) {
            node1 = scanner.nextInt();
            node2 = scanner.nextInt();
            // 然后就开始合并啦
            merge(node1, node2);
        }

        // 判断犯罪集团数量
        int sum = 0;
        for(int i = 0; i < SIZE_OF_NODE; i++) {
            if(boss[i] == i) {
                sum++;
            }
        }
        System.out.println("经统计，共有" + sum + "个犯罪集团");

    }

    /**
     * 合并两个团伙
     * @param x 左边团伙
     * @param y 右边团伙
     */
    private static void merge(int x, int y) {
        int boss1, boss2;
        // 分别找出两个团伙的老大
        // 注 ：因为getBoss()里面有个递归，所以这找出的其实是顶头上司
        boss1 = getBoss(x);
        boss2 = getBoss(y);
        // 若两者的老大不为同一个，即两者不在同一个团伙
        if(boss1 != boss2) {
            // 让右边所在的团伙的归顺左边所在的团伙
            boss[boss2] = boss1;
        }
    }

    /**
     * 找出团伙的老大
     * 注 ：因为getBoss()里面有个递归，所以这找出的其实是顶头上司
     * @param i
     * @return 返回顶头上司的编号
     */
    private static int getBoss(int i) {
        // 若它的老大就是它自己
        if(boss[i] == i) {
            // 直接返回它自己
            return i;
        } else {
            // 若它的老大不是自己，就继续沿着寻找，直到找到一个老大是自己的，即它的顶头上司；返回这个顶头上司
            boss[i] = getBoss(boss[i]);
            return boss[i];
        }

    }




}
