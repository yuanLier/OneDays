package _20181104.biuldHeap;

import _20181102.siftDown.DemoSiftDown;
import _20181103.siftUp.DemoSiftUp;

import java.util.Scanner;

/**
 * 如何优雅地建一个最小堆
 *
 * 题目描述 ：分别用向上调整和向下调整建一个最小堆
 *
 * 基本思路 ：
 *      向上调整得到最小堆 ：
 *          其实就是向一个空堆里插入数据，每插一个就向上调整一下，所以它其实一直都是一个最小堆
 *      向下调整得到最小堆 ：
 *          这个就是直接对一个已经生成好的堆进行操作了
 *          因为如果这棵树中的每个小分支树都满足最小堆的要求，那么这棵树也一定满足最小堆的要求
 *          所以就从它的最后一个非叶节点开始遍历，到根节点遍历结束，
 *              对以每个父节点为小根节点的这棵小分支树的首部进行向下调整
 *
 * 注意事项 ：
 *      对同一个堆进行向上调整和向下调整得到的最小堆不一定是一样的！
 *      但一定都符合最小堆的要求 比如说这个输出示例
 *
 * 注意事项 PLUS ：
 *      完全二叉树的特性 plus ：最后一个非叶节点的编号一定是 总节点数/2
 *
 * 输出示例 ：
 *      请输入树的节点个数 ：
 *      10
 *      请输入第一个堆 ：
 *      9 4 2 6 12 5 10 23 14 1
 *      向上调整建立一个最小堆如下 ：
 *      1 2 4 9 6 5 10 23 14 12
 *      请输入第二个堆 ：
 *      9 4 2 6 12 5 10 23 14 1
 *      向下调整建立一个最小堆如下 ：
 *      1 4 2 6 9 5 10 23 14 12
 */

public class DemoBuildHeap {
    private static int SIZE_OF_NODE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入树的节点个数 ：");
        SIZE_OF_NODE = scanner.nextInt();
        int[] heap = new int[SIZE_OF_NODE+1];

        System.out.println("请输入第一个堆 ：");
        useSiftUp(heap);
        System.out.println("向上调整建立一个最小堆如下 ：");
        tell(heap);

        System.out.println("\n请输入第二个堆 ：");
        useSiftDown(heap);
        System.out.println("向下调整建立一个最小堆如下 ：");
        tell(heap);
    }

    /**
     * 向上调整建一个最小堆
     * (基本思路就是向空堆中一次插入一个元素并调整好各个位置)
     */
    private static void useSiftUp(int[] heap) {
        Scanner scanner = new Scanner(System.in);

        for(int i = 1; i < SIZE_OF_NODE+1; i++) {
            // 从第一个节点开始插入
            heap[i] = scanner.nextInt();
            // 将新插入的节点向上调整
            DemoSiftUp.siftUp(i, heap);
        }
    }

    /**
     * 向下调整建立一个最小堆
     */
    private static void useSiftDown(int[] heap) {
        // 其实这一坨都可以不要，直接传一个需要变成最小堆的堆进来就可以了
        Scanner scanner = new Scanner(System.in);
        for(int i = 1; i < SIZE_OF_NODE+1; i++) {
            heap[i] = scanner.nextInt();
        }

        for(int i = SIZE_OF_NODE/2; i > 0; i--) {
            // 对于每一个父节点，向下调整
            DemoSiftDown.siftDown(i, heap, SIZE_OF_NODE);
        }
    }

    /**
     * 遍历堆并输出
     * @param arr
     */
    private static void tell(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            System.out.print(arr[i] +" ");
        }
    }
}
