package _20181014.easyBucketSort;

import java.util.Scanner;

/**
 * 简易版桶排序
 *
 * 题目描述 ：输入 m 以内的 n 个非负整数，将它们从大到小输出
 *
 * 基本思路 ：
 *      1、初始化 (m+1) 个桶，将输入的 n 个数看作 n 个标有相应数值的球
 *      2、将球投入对应的桶中（比如说球上的数字为 20，则投入下标为 20 的桶中）
 *      3、按照要求（从大到小 or 从小到大）遍历桶，桶中有几个球就输出几次对应下标
 *      4、然后我们的排序就完成啦
 *
 * 注意事项 ：
 *      适用于。。算了不太好描述总之你懂的啦
 *
 * 时间复杂度 ：O(M+N)
 *      共循环 m（初始化桶） + n（输入n个数） + (m + n)（遍历输出），即 2*(m + n) 次嘛
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

        // 令 n = 10
        int n = 10;

        // 输入要比较的数字
        System.out.println("请输入0-" + (bucket.length-1) + "之间的" + n + "个数字 ：");
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
