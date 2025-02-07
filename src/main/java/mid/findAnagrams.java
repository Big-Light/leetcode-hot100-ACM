package mid;

import java.util.*;


public class findAnagrams {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if(s.length() < p.length())
            return ans;

        int[] nums = new int[26];
        for (int i = 0; i < p.length(); i++) {
            nums[p.charAt(i) - 'a']++;
        }

        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            nums[c - 'a']--;
            while (nums[c - 'a'] < 0){
                nums[s.charAt(l) - 'a']++;
                l++;
            }
            if(r - l + 1 == p.length())
                ans.add(l);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String p = sc.nextLine();
        System.out.println(findAnagrams(s, p));
    }
}
