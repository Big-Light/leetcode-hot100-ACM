package mid;

import java.util.Scanner;

public class longestPalindrome {
    public static String longestPalindrome(String s) {
        int n = s.length();
        String ans = "";

        for (int i = 0; i < 2 * n - 1; i++) {
            int l = i / 2;
            int r = l + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)){
                if(r - l + 1 > ans.length()){
                    ans = s.substring(l, r + 1);
                }
                l--;
                r++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(longestPalindrome(s));
    }
}
