package _20181019.linkedList;

import java.util.Scanner;

/**
 * 模拟链表（单向）
 *
 * 题目描述 ：模拟一个单向链表，按序（从小到大）输入一串数字，然后向里面插入一个数
 *
 * 基本思路 ：
 *      1、单向链表就分成两部分嘛，存数据的和指向下一个对象的
 *      2、然后我们就创建一个 Node 类，这个类有一个 data 用来存数据，有一个 next 用来保存下一个对象
 *      3、首先我们需要一个首节点，一个临时节点，还有一个当前节点
 *      4、首节点在最开始就初始化好，然后让临时节点指向它，而各个当前节点则在输入数据的时候初始化
 *      5、整体思路是这样的，令当前节点为 current，临时节点为 temp，则 ：
 *      6、始终保证 temp 是上一轮的 current，然后让 temp.next 指向 current，最后令这一轮的 current 成为下一轮的 temp
 *      7、当然，若首节点中没有数据，则让当前节点成为首节点，再让临时节点指向它；若首节点已有数组，就直接令临时节点的 next 指向它就好啦
 *
 * 注意事项 ：
 *      假设我们有两个节点，A 和 B，要注意区分 A 指向 B 和 A.next 指向 B
 *      （A 指向 B 指的是将 B 赋给 A，即使 B 成为 A，是 " A = B; " 这个操作）
 *      （而 A.next 指向 B 则是在使 B 成为 A 的下一个节点，即 " A.next = B "）
 *
 * 注意事项 plus ：
 *      链表没有什么问题，但这个插入操作其实是有超级大缺陷的
 *      就比如说，这里输入的是只能往中间插的数，直接强行不准往首和尾插，while里面也是直接丢了个true
 *      但其实首部和尾部是要单独考虑的！循环的退出条件也是要加上的！各种空指针也是要处理的！
 *      但是写起来好麻烦的样子 就就就就就就给咕掉了（躺
 *
 * 输出示例 ：
 *      请按序输入十个数字 ：
 *      1 2 3 4 5 6 7 8 9 10
 *      请输入要插入的数 ：
 *      4
 *      插入后，输出如下 ：
 *      1 2 3 4 4 5 6 7 8 9 10
 *
 */

public class DemoLinkedList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 首节点、临时节点、当前节点
        MyNode head = new MyNode();
        MyNode temp = head;
        MyNode current;

        System.out.println("请按序输入十个数字 ：");
        /************** 链表的构建逻辑从这里开始 **************/

        // 按序（从小到大）输入十个数字
        int n = 10;
        for(int i = 0; i < n; i++) {
            // 创建一个当前对象
            current = new MyNode();
            // 将输入的数字保存到节点数据
            current.data = scanner.nextInt();

            // 若链表首部未保存数据
            if(head.data == 0) {
                // 就将当前节点作为首部
                head = current;
            } else {
                // 否则，就令上一个节点的next它
                temp.next = current;
            }

            // 最后让 temp 指向 current
            temp = current;
        }

        /*********** 到这里结束（其实也还是蛮温柔的啦） **********/


        System.out.println("请输入要插入的数 ：");
        /**************** 下面是进行的插入操作 ：***************/

        // 输入一个能插到里面而不是前面也不是后面的数字
        int num = scanner.nextInt();
        // 从链表头部开始遍历
        temp = head;
        // 强行while(true)
        while (true) {
            // 若要该数字是插入到链表中间的，即是令 A -> B 变为 A -> C -> B
            if(temp.next.data > num) {
                // 创建一个节点 C
                current = new MyNode();
                // 将要插入的数的值交由该节点保存
                current.data = num;
                // 先让 C 的 next 指向 B（此时，B 还是 A.next）
                current.next = temp.next;
                // 再让 A 的 next 指向 C
                temp.next = current;
                // 然后就插完辣，直接跳出循环
                break;
            }

            // 这个操作 就相当于 i++
            temp = temp.next;
        }

        /********* 然后到这里我们就把它强行插入到中间去了 **********/

        System.out.println("插入后，输出如下 ：");
        // 最后遍历输出它
        temp = head;
        while(temp != null) {
            System.out.print(temp.data +" ");
            temp = temp.next;
        }
    }
}
