package _20181103.siftUp;

import java.util.Scanner;

/**
 * 树的向上调整
 *
 * 题目描述 ：向一个最小堆里插入一个数
 *
 * 基本思路 ：
 *      1、依然用一个一维数组来存储最小堆，插入就直接插在最后
 *      2、然后将这个插入的数（i）和它的父节点（i/2）比较 ：
 *          若该数小于父节点所表示的数，说明需要向上调整，交换两者，使 i/2 成为 i，继续向上比较
 *          等到该数不小于父节点所表示的数，说明不需要向上比较了，于是新的最小堆就出来啦
 *
 * 注意事项 ：
 *      还是那句话，注意它是从 1 开始的！从 1 开始的！从 1 开始的！
 *      还有就是实例化 heap 的时候记得给那个要插入的数多分配一个空间
 *
 * 输出示例 ：
 *      请输入树的节点个数 ；
 *      10
 *      请输入你的最小堆 ：
 *      2 6 4 10 8 12 14 16 18 10
 *      请输入要插入的数字 ：
 *      3
 *      插入后，当前最小堆为 ：
 *      2 3 4 10 6 12 14 16 18 10 8
 */

public class DemoSiftUp {
    private static int SIZE_OF_NODE;
    private static int[] heap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入树的节点个数 ；");
        SIZE_OF_NODE = scanner.nextInt();
        System.out.println("请输入你的最小堆 ：");
        // 记得这里给它多分配一个数的空间
        heap = new int[SIZE_OF_NODE + 2];
        for(int i = 1; i <= SIZE_OF_NODE; i++) {
            heap[i] = scanner.nextInt();
        }

        System.out.println("请输入要插入的数字 ：");
        // 要插入的数字编号即为当前节点数+1
        int node = SIZE_OF_NODE + 1;
        heap[node] = scanner.nextInt();

        // 将刚插入的数向上调整
        siftUp(node);

        // 输出
        System.out.println("插入后，当前最小堆为 ：");
        for(int i = 1; i < heap.length; i++) {
            System.out.print(heap[i] + " ");
        }
    }

    /**
     * 将插入到末尾的数向上调整
     * @param i 新添加的数的节点编号（理论上讲其实就是添加前的节点个数+1）
     */
    private static void siftUp(int i) {
        boolean needSift = true;

        // 当新添加的数未到达顶点且需要调整
        while (i != 1 && needSift) {
            // 若该数小于其父节点所代表的数
            if(heap[i] < heap[i/2]) {
                // 交换两者的数值
                swap(i, i/2);
                // 然后继续向上比较
                i = i/2;
            } else {
                needSift = false;
            }
        }
    }

    /**
     * 交换数组中的两元素
     * @param i 第一个元素的下标
     * @param j 第二个元素的下标
     */
    private static void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
