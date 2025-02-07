package mid;

import java.util.HashSet;
import java.util.Scanner;

public class lengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int l = 0;
        int ans = 0;
        HashSet<Character> set = new HashSet<>();
        for (int r = l; r < s.length(); r++) {
            char c = s.charAt(r);
            while (l < r && set.contains(c)){
                set.remove(s.charAt(l));
                l++;
            }
            set.add(c);
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(lengthOfLongestSubstring(s));
    }
}
