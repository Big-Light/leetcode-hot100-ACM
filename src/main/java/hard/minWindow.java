package hard;

import java.util.Scanner;

public class minWindow {
    public static String minWindow(String s, String t) {
        if(s.length() < t.length())
            return "";

        int[] nums = new int[128];
        int count = 0;
        for (int i = 0; i < t.length(); i++) {
            nums[t.charAt(i) - 'A']++;
            count++;
        }

        int start = -1, end = s.length();
        for (int r = 0, l = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if(--nums[c - 'A'] >= 0)
                count--;
            while (count == 0){
                if(++nums[s.charAt(l) - 'A'] > 0){
                    if(r - l < end - start){
                        start = l;
                        end = r;
                    }
                    count++;
                }
                l++;
            }
        }
        return start == -1 ? "" : s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();
        System.out.println(minWindow(s, t));
    }
}
