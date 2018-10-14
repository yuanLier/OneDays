package _20181014.easyBucketSort;

import java.util.Scanner;

/**
 * 简易版桶排序
 *
 * 题目描述 ：输入 m 以内的 n 个非负数，将它们从大到小输出
 *
 * 时间复杂度 ：O(M+N)
 *
 * 解析 ：共循环 m（初始化桶） + n（输入n个数） + (m + n)（遍历输出），即 2*(m + n) 次
 *
 * 输出示例 ：
 *      请输入0-1000之间的10个数字 ：
 *      1000 32 545 777 345 987 53 25 89 0
 *      进行桶排序后，得到的数组如下 ：
 *      1000 987 777 545 345 89 53 32 25 0
 */

public class DemoEasyBucketSort {
    public static void main(String[] args) {

        // 令 m = 1000, 则我们需要初始化 1001 个桶
        int bucket[] = new int[1001];
        // 将每个桶的初始值设为 0
        for(int i = 0; i < bucket.length; i++) {
            bucket[i] = 0;
        }

        // 令 n = 10, 这里需要在控制台手动输入 10 个数字
        int n = 10;
        System.out.println("请输入0-" + (bucket.length-1) + "之间的" + n + "个数字 ：");
        // 获取Scanner
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < n; i++) {
            // 得到控制台输入的数字
            int num = scanner.nextInt();
            // 将与该数字对应的桶加一
            bucket[num]++;
        }

        // 然后直接遍历输出就好啦
        System.out.println("进行桶排序后，得到的数组如下 ：");
        // 外层遍历所有桶
        for(int i = bucket.length-1; i >= 0 ; i--) {
            // 内层遍历桶中数据
            for(int j = 0; j < bucket[i]; j++) {
                // 有几个数就将该桶对应的数字打印几遍
                System.out.print(i + " ");
            }
        }
    }
}
