package mid;

import java.util.*;

public class wordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (String string : wordDict) {
            set.add(string);
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if(dp[j] && set.contains(s.substring(j, i)))
                    dp[i] = true;
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String s2 = sc.nextLine();
        String[] split = s2.split(" ");
        List<String> list = new ArrayList<>();
        for (String string : split) {
            list.add(string);
        }
        System.out.println(wordBreak(s, list));
    }
}
