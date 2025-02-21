package mid;

import java.util.ArrayDeque;
import java.util.Scanner;

public class decodeString {
    public static String decodeString(String s) {
        ArrayDeque<Integer> numStack = new ArrayDeque<>();
        ArrayDeque<String> strStack = new ArrayDeque<>();

        int num = 0;
        String cur = "";
        for (char c : s.toCharArray()) {
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
            } else if (c == '[') {
                numStack.push(num);
                strStack.push(cur);
                num = 0;
                cur = "";
            } else if (c == ']') {
                int loop = numStack.pop();
                StringBuilder sb = new StringBuilder(strStack.pop());
                for (int i = 0; i < loop; i++) {
                    sb.append(cur);
                }
                cur = sb.toString();
            }else
                cur += c;
        }
        return cur;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(decodeString(s));
    }
}
