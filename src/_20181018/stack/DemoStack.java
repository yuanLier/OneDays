package _20181018.stack;

import java.util.Scanner;

/**
 * 模拟栈
 *
 * 题目描述 ：用听起来高端一点的方法判断回文字符串
 *
 * 基本思路第一弹 ：模拟栈
 *      1、将这串字符看作一个栈，用字符组 stack 表示
 *      2、引入一个整型变量 top，用来表示栈顶
 *      3、那么向栈中添加数据就是给 stack[top] 赋值然后 top++
 *      4、出栈就直接 top--，反正下一个值会把之前的覆盖掉
 *      5、当 top == 0，这个栈就空掉了
 *
 * 基本思路第二弹 ：开始套题
 *      1、找到该字符串的中间数 mid（注意这里的 mid 其实并不是正中间那个）
 *      2、将 mid 及 mid 之前的字符依次入栈，并根据字符串长度的奇偶性判断 next
 *      3、按序取出栈中元素，并依次和 next 及 next 之后的对应字符相比较
 *      4、若对应位置的元素相等，则令其出栈（top--）
 *      5、当找到对应位置不相等的字符，或整个字符串已经遍历完时，退出循环
 *      6、此时，若 top == 0，则是为回文字符串；反之，则不是
 *
 * 注意事项 ：
 *      这个 mid 其实不是正中间那个数（主要是考虑到字符串长度的奇偶不同，处理方式也不同）
 *      就比如说现在有两串字符，它们的字符长度分别为奇数和偶数 ：
 *          奇数串 A[7] ：1 2 3 4 5 6 7
 *          偶数长 B[6] ：1 2 3 4 5 6
 *      那么这里 A 和 B 的 mid 对应的数均为 3（即 A[2]、B[2]，mid == 2）
 *      B很好理解，但是 A 是为啥？中间数不应该 4 嘛？
 *      原因就在于，如果我们把 mid 设为 4 的话，就得先把 4 出栈才能进行两两比较了
 *      而设为 3 的话，就能够直接用 stack[top] 和 A[mid+1] 进行第一轮比较，是不是很完美
 *      最后总结一下上述两种情况，就能够得到 mid = arr.length / 2 - 1 的结论啦
 *
 * 输出示例 ：
 *      请输入要判断的字符串 ：
 *      abcabcabc
 *      字符串abcabcabc不是回文字符串
 *
 *      请输入要判断的字符串 ：
 *      abcdcba
 *      字符串abcdcba是回文字符串
 *
 *      请输入要判断的字符串 ：
 *      123321
 *      字符串123321是回文字符串
 */

public class DemoStack {
    public static void main(String[] args) {
        // 创建一个栈，并初始化它的top
        MyStack s = new MyStack(100);
        s.top = 0;

        System.out.println("请输入要判断的字符串 ：");
        // 首先读入一串字符，并求得其 mid
        String arr = new Scanner(System.in).next();
        int mid = arr.length() / 2 - 1;

        // 根据字符串长度的奇偶性判断next
        // 若为偶数，则 next 就是 mid 后面那个
        // 若为奇数，则 next 在 mid 后面两个的位置（因为要跳过正中间那个字符）
        int next = (arr.length() % 2 == 0) ? (mid + 1) : (mid + 2);

        // 将 mid 及 mid 之前的字符依次入栈
        for(int i = 0; i <= mid; i++) {
            // 哦！天！看看这优秀的 charAt()
            s.stack[s.top++] = arr.charAt(i);
        }

        // 从 next 开始遍历字符串
        for(int i = next; i < arr.length(); i++) {
            // 因为入栈结束后，top是指向 arr[mid] 的后一位的，所以要先减一道
            if(arr.charAt(i) != s.stack[--s.top]) {
                // 若对应字符不相等，直接结束
                break;
            }
        }

        // 最后判断是否为回文字符串（若跳出循环后栈为空，则是；反之，则否）
        if(s.top == 0) {
            System.out.println("字符串" + arr + "是回文字符串");
        } else {
            System.out.println("字符串" + arr + "不是回文字符串");
        }
    }
}
