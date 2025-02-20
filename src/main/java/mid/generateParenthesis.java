package mid;

import java.util.*;

public class generateParenthesis {
    static List<String> ans = new ArrayList<>();
    public static List<String> generateParenthesis(int n) {
        backTracking(n, n, "");
        return ans;
    }

    private static void backTracking(int l, int r, String cur) {
        if(l == 0 && r == 0){
            ans.add(cur);
            return;
        }
        if(l > r)
            return;
        if(l > 0){
            backTracking(l - 1, r, cur + "(");
        }
        if(r > 0){
            backTracking(l, r - 1, cur + ")");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(generateParenthesis(n));
    }
}
