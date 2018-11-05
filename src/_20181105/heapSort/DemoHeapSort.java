package _20181105.heapSort;

import java.util.Scanner;

/**
 * 堆排序
 *
 * 题目描述 ：堆排一个从小到大的序
 *
 * 基本思路 ：
 *      思路一 ：建立最小堆 --> 输出并删除顶部元素 --> 将堆的最后一个元素丢到堆顶 -->
 *                  向下调整以维持最小堆 --> 输出并删除顶部元素 --> 重复，直到堆中元素为空
 *      思路二 ：建立最大堆 --> 将最后一个元素(heap[n])与顶部元素互换位置，然后令 n = n-1 --> 向下调整以维持最大堆 -->
 *                  将倒数第二个元素（即当前heap[n]）与顶部元素互换位置，n-- --> 向下调整以维持最大堆 --> 重复，直到 n == 1
 *      这里就使用第二种思路啦，也不用前面的方法了，从头到尾写一遍好啦
 *
 * 注意事项 ：
 *      为什么不选用第一种思路呢？你想嘛，
 *          不管是把顶部元素删除，还是把它取出来放到别的数组里面去，都没有直接在当前数组排好序方便鸭
 *
 * 输出示例 ：
 *      请输入树的节点个数 ：
 *      10
 *      请依次输入要排序（从小到大）的数 ：
 *      34 21 78 65 46 98 70 57 37 25
 *      排序后，输出如下 ：
 *      21 25 34 37 46 57 65 70 78 98
 */
public class DemoHeapSort {
    // 总节点数
    private static int n;
    // 堆
    private static int[] heap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入树的节点个数 ：");
        n = scanner.nextInt();

        System.out.println("请依次输入要排序（从小到大）的数 ：");
        heap = new int[n+1];
        for(int i = 1; i < heap.length; i++) {
            heap[i] = scanner.nextInt();
        }

        // 首先要建立最大堆
        buildHeap();
        // 然后再开始排序
        heapSort();

        System.out.println("排序后，输出如下 ：");
        for(int i = 1; i < heap.length; i++) {
            System.out.print(heap[i] + " ");
        }
    }

    /**
     * 堆排序
     */
    private static void heapSort() {
        // 当堆中元素不为 1
        while (n > 1) {
            // 交换顶部元素与当前 heap[n]
            swap(n, 1);
            // n--
            n--;
            // 将刚换过去的顶部元素向下调整，以维持最大堆
            siftDown(1);
        }
    }

    /**
     * 建堆
     */
    private static void buildHeap() {
        // 从最后一个非叶节点逐个调整
        for(int i = n/2; i > 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 最大堆的向下调整
     * @param i 传入节点编号
     */
    private static void siftDown(int i) {
        boolean needSift = true;
        // 当有左儿子并且需要调整（注意是当(while)不是若(if)）
        while (i*2 <= n && needSift) {
            // temp保存该节点与其左儿子间较大的那个的编号
            int temp = heap[i*2] > heap[i] ? i*2 : i;

            // 若有右儿子
            if(i*2+1 <= n) {
                // temp 保存该节点与其左右儿子中最大的那个的编号
                if(heap[i*2+1] > heap[temp]) {
                    temp = i*2+1;
                }
            }

            if(temp == i) {
                // 若temp即为该节点自己的编号，说明不需要调整了
                needSift = false;
            } else {
                // 若不是，交换两者
                swap(i, temp);
                // 并令 i = temp，进行下一轮调整
                i = temp;
            }
        }
    }

    /**
     * 根据节点编号交换两节点数值
     * @param i
     * @param j
     */
    private static void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
