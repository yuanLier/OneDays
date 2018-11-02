package _20181102.siftDown;

import java.util.Scanner;

/**
 * 树的向下调整
 *
 * 题目描述 ：删除一个最小堆中最小的那个数，并插入一个新数，求当前最小数
 *
 * 基本思路 ：
 *      1、用一个一维数组来存储一个最小堆，那么最小的数就在顶点（根节点）处
 *      2、现在要删除最小那个数，插入一个新的数，就直接把要插入的数放到根节点，替换掉之前那个数
 *      3、然后将这个新的根节点依次与它的两个儿子比较 ：
 *          定义一个临时变量 temp,用来记录它与其两个儿子之间，数值最小的那个节点的节点编号
 *          若 temp 所记录的编号不是父节点，说明父亲比儿子的数值大，应该向下调整
 *          交换父亲和 temp 所记录的编号所代表的那个儿子，然后令这个儿子成为新一轮的父亲，继续下调
 *      4、当调整到父节点不再比它的任何一个儿子的数值大了，新的最小堆就完成啦
 *      5、然后直接输出根节点，它所代表的数值就是新一轮的最小值啦
 *
 * 注意事项 ：
 *      这个不是排序哦！
 *      最小堆的意思是，这棵树上每个父节点都比它的子节点的数值要小，但同层各个儿子之间的大小关系是不确定的
 *
 * 注意事项plus ：
 *      1、简单记录一下各种树好了 ：
 *          树 ：由一个父节点延伸出各个子节点，每个子节点又可以作为下一个父节点往下延伸
 *          根节点 ：最上面那个节点，没有父节点的
 *          叶节点 ：最下面各个节点，没有子节点的
 *          中间节点 ：就是既不是根节点也不是叶节点的中间的那些节点
 *          二叉树 ：就是指 一个父节点上要么没有子节点，要么有且仅有左右两个子节点的 树
 *          满二叉树 ；就是指所有中间节点都有一个父亲两个儿子的树
 *          完全二叉树 ：三角形缺了最下面一排的最右边一块
 *      2、完全二叉树的各种性质 ：
 *          若父节点为 i，则其左儿子节点为 i*2，右儿子节点为 i*2+1
 *          若节点总数为 N，则树的深度为 log(2)N+1 （注 ：log(2)N：以2为底N的对数）
 *
 * 注意事项 PLUUUUUUUUUS ：
 *      注意节点编号是从 1 开始的！从 1 开始的！ 从 1 开始的！
 *
 * 输出示例 ：
 *      请输入树的节点个数 ；
 *      10
 *      请按序（从小到大）输入各个数 ：
 *      2 6 4 10 8 12 14 16 18 10
 *      请输入要插入的数字 ：
 *      9
 *      删除最小数，插入指定数后，当前最小数为 ：
 *      4
 *
 */
public class DemoSiftDown {
    // 树的节点个数
    private static int SIZE_OF_NODE;
    // 用来模拟堆的数组
    private static int[] heap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入树的节点个数 ；");
        SIZE_OF_NODE = scanner.nextInt();
        System.out.println("请按序（从小到大）输入各个数 ：");
        heap = new int[SIZE_OF_NODE + 1];
        for(int i = 1; i <= SIZE_OF_NODE; i++) {
            heap[i] = scanner.nextInt();
        }

        // 删除最小的数，插入指定数字（其实也就是把堆的首位数替换为要插入的数字啦）
        System.out.println("请输入要插入的数字 ：");
        heap[1] = scanner.nextInt();

        // 将刚插入的首位数向下调整
        siftDown(1);

        // 输出
        System.out.println("删除最小数，插入指定数后，当前最小数为 ：\n" + heap[1]);
    }

    /**
     * 向下调整（最小堆）
     * @param i 传入一个需要向下调整的节点编号 i
     */
    private static void siftDown(int i) {
        // 记录 i 与其左右儿子中数值最小的那个节点的编号
        int temp;
        // 判断是否需要向下调整
        boolean needSift = true;

        // 当节点i有儿子并且需要向下调整的时候
        // 注意一轮循环结束后，再次进入循环时，i 已经变成先前那个 i 的某个儿子了
        while(i*2 <= SIZE_OF_NODE && needSift) {
            // 首先比较它与左儿子的大小，用temp记录数值较小的那个节点号
            temp = heap[i] < heap[i*2] ? i : i*2;

            // 然后如果它有右儿子，再对右儿子进行讨论
            if(i*2+1 <= SIZE_OF_NODE) {
                if(heap[i*2+1] < heap[temp]) {
                    temp = i*2+1;
                }
            }

            // 若发现 temp 不是它自己，说明儿子中有比父亲数值小的
            if(temp != i) {
                // 交换父亲和这个儿子的位置
                swap(i, temp);
                // 令 i = temp，方便进行下一轮调整
                i = temp;
            } else {
                // 若 temp 依然是它自己，说明不需要调整了
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
