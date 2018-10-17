package _20181017.queue;

import java.util.Scanner;

/**
 * 模拟队列
 *
 * 题目描述 ：
 *      将一串数字的第一个数删除，第二个数添加到末尾，第三个数删除，第四个数添加到末尾...
 *      直到把最后一个数也删除了，就把它们按刚刚删掉的按顺序打印出来
 *
 * 基本思路 ：
 *      1、将这串数字看作一个队列，用数组 queue 表示
 *      2、引入两个整型变量 head（指向队首），tail（指向队尾）
 *      3、然后删除队首的数就是 head++ 嘛
 *      4、向队尾添加数就是给 queue[tail] 赋值然后 tail++ 嘛
 *      5、当 head 与 tail 相遇的时候，这个队列就为空了
 *
 * 注意事项 ：
 *      1、head 指向的是队列的第一个数，但 tail 指的是当前队列的最后一个数的后面一个位置哦
 *      2、就比如说 ：向 arr[100] 中存入十个数字，那么在最初始的状态下，head 指向的是 arr[0]，tail 指向的则是 arr[10]
 *      3、可以理解为你目前有十个人排队买小面包嘛，买完就走 --> 删除队首，又有人来 --> 添加队尾
 *      4、而这里的100则是指一个可用空间（不是说一共可以同时有100个人排队，而是说这个店里只有100个小面包，一人只能买一个，卖完就没有了）
 *
 * 输出示例 ：
 *      请输入加密过的数字 ：
 *      6 3 1 7 5 8 9 2 4
 *      解密后，得到的号码为 ：
 *      615947283
 */

public class DemoQueue {
    public static void main(String[] args) {
        // 首先创建一个队列（初始值设长一点点）
        MyQueue q = new MyQueue(100);

        // 初始化队列（头和尾均指向同一个元素，表示这个队列目前为空）
        q.head = 0;
        q.tail = 0;

        // 向队列中加入九个数
        System.out.println("请输入加密过的数字 ：");
        int n = 9;
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < n; i++) {
            // 依次添加到队尾
            q.queue[q.tail] = scanner.nextInt();
            q.tail++;
        }

        // 在队首队尾还没有相遇的时候，循环执行下列步骤 ：
        // 输出并删除队首（head++） --> 将当前队首添加到队尾（head、tail都++） --> 输出并删除队首 --> ...
        System.out.println("解密后，得到的号码为 ：");
        while(q.head != q.tail) {
            // 输出并删除队首
            System.out.print(q.queue[q.head++]);

            // 将当前队首添加到队尾，再head++，tail++
            q.queue[q.tail++] = q.queue[q.head++];
        }

    }
}
