package _20181018.stack;

/**
 * 小小地封装一下这个栈
 */

public class MyStack {
    // 注意这里是字符串组啊啊啊啊（因题制宜 因题制宜）
    char[] stack;
    int top;

    // 同样，在初始化时给栈里面那个数组分配个大小
    MyStack(int n) {
        stack = new char[n];
    }
}
