package _20181023.breadthFirstSearch;

import java.util.Scanner;

/**
 * 广度优先搜索
 *
 * 题目描述 ：小哼要去迷宫里营救小哈，快用深度优先搜索找出最近的路吧！（强行bfs.jpg
 *
 * 基本思路 ：
 *      1、就是建立一个队列，head 指向第 step 步，tail 指向第 step+1 步后面一格
 *      2、让我们按右、下、左、上的顺序尝试进行下一步，注意 ：以（0,0）为原点的话，x轴沿下，y轴沿右，
 *      3、比如说我们现在从（0,0）出发啦，先尝试往右走一步，step = head.step+1 = 1，可以到达（1,0）
 *                head      -     tail
 *          x       0       0
 *          y       0       1
 *          step    0       1
 *      4、但是还要继续尝试往其他方向走，从（0,0）向下，可以到达（1,0）
 *                head      -       -     tail
 *          x       0       0       1
 *          y       0       1       0
 *          step    0       1       1
 *      5、从（0,0）出发，往前走一步的全部可能性都探索完之后，就可以把它丢掉了 ：
 *         令 head++，现在我们站到了（1,0）上，尝试向右走一步，发现那里强行有座山；
 *         那就再向下走，step = head.step+1 = 2，可以到达（1,1）
 *                  -     head      -       -     tail
 *          x       0       0       1       1
 *          y       0       1       0       1
 *          step    0       1       1       2
 *      6、然后（1,0）就探索完啦，丢掉它，令head++，现在我们到达了（0,1）这个位置
 *         尝试向右探索，发现（1,1）我们已经探索过了；
 *         那就不管它，再向下探索，step = head.step+1 = 2，可以到达（0,2）
 *                  -       -     head      -       -     tail
 *          x       0       0       1       1       2
 *          y       0       1       0       1       0
 *          step    0       1       1       2       2
 *      7、总之就这样一直走一直走，然后某个时候，我们到达目标坐标点了，就可以就到小哈啦！
 *      8、这个时候最后那个step就是最小步数啦，输出它，这题就结束了。。。。？
 *      9、嘿呀！我们还可以给队列中加一个 father 属性，然后就可以在最后结束的时候打印出整条路呢！
 *
 * 注意事项 ：
 *      一定要分清 x 轴和 y 轴啊啊啊啊啊啊啊啊！！！
 *
 * 输出示例 ；
 *      请分别输入地图的最大横纵坐标 ：
 *      5 4
 *      请输入地图（0表示空地，1表示障碍物） ：
 *      0 0 1 0
 *      0 0 0 0
 *      0 0 1 0
 *      0 1 0 0
 *      0 0 0 1
 *      请输入小哼的横纵坐标 ：
 *      0 0
 *      请输入小哈的横纵坐标 ：
 *      3 2
 *      走出迷宫所需的最小步数为 ：
 *      7
 *      具体路径如下 ：
 *      （3, 2）  <-- （3, 3）  <-- （2, 3）  <-- （1, 3）  <-- （1, 2）  <-- （1, 1）  <-- （0, 1）  <-- （0, 0）
 *
 */

public class DemoBreadthFirstSearch {
    // 地图的最大横坐标（即地图长度）
    private static int X_MAX;
    // 地图的最大纵坐标（即地图宽度）
    private static int Y_MAX;
    // 用来记录当前是到的地图哪一格
    private static int[][] map;
    // 用来记录当前这一格有没有被探索过
    private static int[][] book;
    // 因为把 dfs 拿出来了嘛，就让那个队列也变成静态对象
    private static MyQueue q;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请分别输入地图的最大横纵坐标 ：");
        X_MAX = scanner.nextInt();
        Y_MAX = scanner.nextInt();

        // 初始化 map 和 book
        map = new int[X_MAX][Y_MAX];
        book = new int[X_MAX][Y_MAX];

        System.out.println("请输入地图（0表示空地，1表示障碍物） ：");
        for(int i = 0; i < X_MAX; i++) {
            for(int j = 0; j < Y_MAX; j++) {
                map[i][j] = scanner.nextInt();
            }
        }

        System.out.println("请输入小哼的横纵坐标 ：");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        System.out.println("请输入小哈的横纵坐标 ：");
        int endX = scanner.nextInt();
        int endY = scanner.nextInt();

        // 初始化一个最长 X_MAX * Y_MAX 的队列
        q = new MyQueue(X_MAX * Y_MAX);
        // 初始化它的头和尾巴
        q.head = 0;
        q.tail = 0;

        // 在头和尾巴还相遇着的时候，初始化尾巴对应的那个队列节点
        q.queue[q.tail].x = startX;
        q.queue[q.tail].y = startY;
        q.queue[q.tail].father = 0;
        q.queue[q.tail].step = 0;

        // 标记一下，表示小哈最初的那个位置已经被探索过了
        book[startX][startY] = 1;
        // 然后把尾巴往后移一个，我们的初始化工作就完成啦
        q.tail++;

        // 调用 dfs 函数，传入目标点的横纵坐标
        dfs(endX, endY);
    }

    /**
     * 前面main函数里面初始化的过程太长一串了，还是把 dfs 单独拿出来好了
     * @param endX 目标点的横坐标
     * @param endY 目标点的纵坐标
     */
    private static void dfs(int endX, int endY) {
        // 定义一个表示方向的数组（分别为向 右、下、左、上 走）
        int[][] next = {
                {0, 1}, {1, 0}, {0, -1}, {1, 0}
        };

        // 用来标记是否到达目标点（0表示还没，1表示到达了）
        int flag = 0;
        // 首尾未相遇（即队列不为空）时，循环
        while(q.head < q.tail) {

            // 枚举四个方向
            for(int i = 0; i < 4; i++) {

                // 计算下一个点的坐标
                int nextX = q.queue[q.head].x + next[i][0];
                int nextY = q.queue[q.head].y + next[i][1];

                // 判断下一个点是否越界
                if((nextX < 0) || (nextX >= X_MAX) || (nextY < 0) || (nextY >= Y_MAX)) {
                    // 若越界，跳出当前循环
                    continue;
                }

                // 若下一个坐标不为障碍物且未探索过
                if(map[nextX][nextY] == 0 && book[nextX][nextY] == 0) {
                    // 将它标记为已探索
                    book[nextX][nextY] = 1;

                    // 将这个新的点插入到队列末尾
                    q.queue[q.tail].x = nextX;
                    q.queue[q.tail].y = nextY;
                    // 因为这个点是从当前首部那个点扩展出来的，所以它的 father 就是 q.head
                    q.queue[q.tail].father = q.head;
                    // 同理，它的步数也是在父步数的基础上加个一
                    q.queue[q.tail].step = q.queue[q.head].step + 1;

                    // 然后让小尾巴也往后移一个就好啦
                    q.tail++;
                }

                // 若下一个坐标就是目标坐标！
                if(nextX == endX && nextY == endY) {
                    // 首先把 flag 立上，表示已经到达目标点了
                    flag = 1;
                    // 然后跳出for循环
                    break;
                }
            }

            // 若flag已经为一了
            if(flag == 1) {
                // 跳出while循环
                break;
            } else {
                // 否则，head++，继续探索下一个点
                q.head++;
            }
        }

        // 打印最后一个节点对应的步数，即为最小步数（注意tail要减1）
        System.out.println("走出迷宫所需的最小步数为 ：");
        System.out.println(q.queue[q.tail-1].step);

        // 打印出具体路径
        System.out.println("具体路径如下 ：");
        // 将索引指向最后一个节点
        int index = q.tail-1;
        while (index != 0) {
            // 输出当前坐标
            System.out.print("（" + q.queue[index].x + ", " + q.queue[index].y + "）  <-- ");

            // 然后依次指向它的父节点
            index = q.queue[index].father;
        }
        // 这里输出的就是初始坐标啦，单独拿出来，让输出格式好看一些
        System.out.print("（" + q.queue[index].x + ", " + q.queue[index].y + "）");
    }
}
