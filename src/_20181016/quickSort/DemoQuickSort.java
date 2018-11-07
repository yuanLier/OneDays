package _20181016.quickSort;

import java.util.Scanner;

/**
 * 快速排序
 *
 * 题目描述 ：请给 n 个数又快又好从大到小排完序（手动狗头
 *
 * 基本思路 ：
 *      1、设置一个基准数（比如说这里设为最左边那个数
 *      2、设置一个左边小人，一个右边小人
 *      3、右边小人往左走，找一个比基准数大的
 *      4、左边小人往右走，找一个比基准数小的
 *      5、如果两个小人还没碰面，就交换！
 *      6、如果碰面了，就把基准数跟碰面时候那个位置的数交换
 *      7、然后分为左边右边继续迭代
 *      8、到最后所有子代都只剩一个数的话就排完啦
 *
 * 注意事项 ：
 *      如果基准数在在左边，一定要让右边的小哨兵先出发
 *      （这样想嘛，既然基准数都在左边了，再让左边的先走，对右边的多不公平（雾））
 *      （或者这样想，最优情况就是每次都恰好平分嘛，你都占了一个了又先走一步，还怎么平分嘛）
 *
 * 时间复杂度 ：
 *      平均 ：O(NlogN)
 *      最坏 ：O(N^2)
 *      （最坏情况就是每次恰好只能相邻两个比较嘛，相当于变成了冒泡排序）
 *
 * 输出示例 ：
 *      请输入要比较的10个数 ：
 *      92 43 52 74 61 59 451 524 897 55
 *      进行快速排序后，输出如下 ：
 *      897 524 451 92 74 61 59 55 52 43
 */

public class DemoQuickSort {
    // 令 N = 10
    private static final int N = 10;

    public static void main(String[] args) {
        // 初始化一个数组来装我们的 N 个数
        int[] arr = new int[N];

        // 输入要比较的数字
        System.out.println("请输入要比较的" + N + "个数 ：");
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        // 快速排序（是直接对数组进行操作的）
        quickSort(arr,0,N-1);

        System.out.println("进行快速排序后，输出如下 ：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 通过这个方法来进行快排操作
     * @param arr 传入数组（是直接操作的）
     * @param left 传入数组拆分的起始索引（即左下标）
     * @param right 传入数组拆分的结束索引（即右下标）
     */
    public static void quickSort(int[] arr, int left, int right) {

        // 首先判断一下，如果在最开始已经 right <= left 了，就直接结束
        if(right <= left) {
            return;
        }

        // 首先把最左边的数设为基准数
        int baseNum = arr[left];
        // 然后设置一个左边小人i，再设置一个右边小人j
        int i = left, j = right;

        // 若 i 与 j 还没相遇，持续循环
        while(i != j) {
            // 基准数设为最左的话，就让右边小人先走
            while(arr[j] <= baseNum && i < j) {
                // 若右边小人还没有找到比基准数大的数，且仍然在右边小人左边，就让它持续向左走
                j--;
            }

            // 跳出上面那个while了就说明此时右边小人已经找到比基准数大的了
            while(arr[i] >= baseNum && i < j) {
                // 然后同理，我们就可以让左边小人往右走啦
                i++;
            }

            // 注 ：跳出刚刚那个while循环的时候i是有可能等于j的，这里判断一下就可以少操作一次
            if (i < j) {
                // 如果这两个小人还没有碰面，就把它们所在的数交换！
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // 好！跳出while循环说明现在两个小人已经站到一起去了
        // 这个时候我们就应该把这个数和基准数（要注意基准数其实就是arr[left]）交换啦
        arr[left] = arr[i];
        arr[i] = baseNum;

        // 然后就可以进行下一轮的迭代了！
        quickSort(arr, left, i-1);
        quickSort(arr, i+1, right);
    }
}
