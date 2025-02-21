package easy;

import java.util.ArrayDeque;
import java.util.Scanner;

public class isValid {
    public static boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if(c == '(' || c == '[' || c == '{')
                stack.push(c);
            else {
                if(stack.isEmpty())
                    return false;
                else if (c == ')' && stack.peek() == '(') {
                    stack.pop();
                }else if (c == ']' && stack.peek() == '[') {
                    stack.pop();
                }else if (c == '}' && stack.peek() == '{') {
                    stack.pop();
                }else
                    return false;
            }
        }
        if(!stack.isEmpty())    return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(isValid(s));
    }
}
