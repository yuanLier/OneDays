package _20181020.linkedList2;

import java.util.Scanner;

/**
 * 模拟链表(双向)
 *
 * 题目描述 ：模拟一个双向链表，按序（从小到大）输入一串数字，然后分别输出其中某个数前面的数和后面的数
 *
 * 基本思路 ：
 *      其实基本上跟单向差不多啦，只需要多添加一个一个 perv 指向之前那个节点就好啦
 *
 * 注意事项 ：
 *      这里是没写插入和删除才那么简单的啦 如果要写的话也是得分情况讨论的 (｀・ω・´)
 *
 * 稍微plus一下 ：
 *      环形链表其实就是把头和尾巴也连起来了啦 超级简单的 就不写了嘿嘿
 *
 * 输出示例 ：
 *      请输入十个数字 ：
 *      1 2 3 4 5 6 7 8 9 10
 *      请指定以上某个数字 ：
 *      4
 *      prev ：3 2 1
 *      next ：5 6 7 8 9 10
 *
 */

public class DemoLinkedList {
    private static final int N = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 首节点、临时节点、当前节点
        MyNode head = new MyNode();
        MyNode temp = head;
        MyNode current;

        System.out.println("请输入" + N +"个数字 ：");
        for(int i = 0; i < N; i++) {
            current = new MyNode();
            current.data = scanner.nextInt();

            if(head.data == 0) {
                head = current;
            } else {
                temp.next = current;
                current.prev = temp;
            }

            temp = current;
        }

        System.out.println("请指定以上某个数字 ：");
        int num = scanner.nextInt();
        // 找到那个指定的数字
        temp = head;
        while(temp.data != num) {
            temp = temp.next;
        }

        // 记录一下这个temp的位置
        MyNode t = temp;

        // 输出位于它之前的数 ：
        System.out.print("prev ：");
        while(temp.prev != null) {
            System.out.print(temp.prev.data + " ");
            temp = temp.prev;
        }
        System.out.println();
        //输出位于它之后的数 ：
        System.out.print("next ：");
        while (t.next != null) {
            System.out.print(t.next.data + " ");
            t = t.next;
        }
    }
}
