package _20181019.linkedList;

import java.util.Scanner;

/**
 * 模拟链表
 *
 * 题目描述 ：模拟一个链表，然后向里面插入一个数
 *
 * 基本思路 ：
 *      1、单向链表就分成两部分嘛，存数据的和指向下一个对象的
 *      2、然后我们就创建一个 Node 类，这个类有一个 data 用来存数据，有一个 next 用来保存下一个对象
 *      3、比如说我们有一个链表 A -> B -> C，显然 A 指向 B，B 指向 C，C指向空
 *      4、那我们要把一个 D 插入 A、B 之间的话，就断开 A、B 之间的连接，然后让 A 指向 D，D 指向 B 就好啦
 *      5、然后删除一个数也同理，比如说删 B 吧，就直接断开 A 和 B 直接的连接，使 B 指向 C 就好啦
 *
 * 注意事项 ：
 *
 * 输出示例 ：
 *
 */

public class DemoLinkedList {
    public static void main(String[] args) {
        // 初始节点、临时节点、当前节点
        MyNode head = new MyNode();
        MyNode temp = new MyNode();
        MyNode current = new MyNode();

        int n = 10;
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < n; i++) {
            // 初始化当前节点，为其赋上索引值
            current = new MyNode();
            // 将输入的输入保存到节点数据
            current.data = scanner.nextInt();

        }
        


        // 咕了咕了  明天补上  嘤嘤嘤




    }
}
