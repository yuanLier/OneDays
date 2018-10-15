package _20181015.bubbleSort;

import java.util.Scanner;

/**
 * 冒泡排序
 *
 * 题目描述 ：任意输入 n 个小数，并将它们从大到小排序输出
 *
 * 基本思路 ：
 *      1、比较第一个数和第二个数，把小的往后搁
 *      2、比较第二个数和第三个数，把小的往后搁
 *      3、比较第三个数和第四个数，把小的往后搁
 *      4、repeeeeeeeeeeeeeat，直到最小的数被搁在了最后一个
 *      5、比较第一个数和第二个数，把小的往后搁
 *      6、比较第二个数和第三个数，把小的往后搁
 *      7、比较第三个数和第四个数，把小的往后搁
 *      8、repeeeeeeeeeeeeeat，直到倒数第二小的数被搁在了倒数第二个
 *      9、repeeeeeeeeeeeeeat，直到每个数都被搁在了它们应该待的地方
 *      10、我们的排序就完成啦
 *
 * 注意事项 ：
 *      每一次重复称为一轮的话，就每轮确定一个数的位置，然后下一轮就不用比较它啦
 *      （比如说第一轮确定了最后一个数的位置，那么第二轮比较到倒数第二个数和倒数第三个数就OK了）
 *
 * 时间复杂度 ：O(N^2)
 *      毕竟有个循环了 n 次的内层，时间复杂度一下就上去了 嘤
 *
 * 输出示例 ：
 *      请输入要比较的10个小数 ：
 *      10.2 3.5 7.8 9.4 2.2 1.1 7.3 8.8 3.0 15.1
 *      进行冒泡排序后，得到的数组如下 ：
 *      15.1 10.2 9.4 8.8 7.8 7.3 3.5 3.0 2.2 1.1
 */

public class DemoBubbleSort {
    public static void main(String[] args) {

        // 令 n = 10
        int n = 10;
        // 初始化一个数组来装我们的 n 个数
        float[] nums = new float[n];

        // 输入要比较的数字
        System.out.println("请输入要比较的" + n + "个小数 ：");
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < n; i++) {
            nums[i] = scanner.nextFloat();
        }

        // 比较大小
        // 外层循环记录的是轮数
        for(int i = 0; i < n; i++) {
            // 内层循环就开始比较相邻两个数了
            for(int j = 0; j < (n-i)-1; j++) {
                // 若这个数比它的下个数要小
                if(nums[j] < nums[j+1]) {
                    // 交换两数的位置
                    float temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }

        // 遍历输出
        System.out.println("进行冒泡排序后，得到的数组如下 ：");
        for(int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
