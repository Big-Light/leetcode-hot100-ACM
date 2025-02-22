package mid;

import java.util.Arrays;
import java.util.Scanner;

public class numSquares {
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        for (int i = 1; i * i <= n; i++) {  //遍历所有小于等于 n 的完全平方数 i * i。
            for (int j = i * i; j <= n; j++)    //根据当前的完全平方数，更新 dp[j]，表示构成 j 的最小完全平方数的个数。
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(numSquares(n));
    }
}
