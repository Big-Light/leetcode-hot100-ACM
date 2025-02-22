package mid;

import java.util.*;

public class partitionLabels {
    public static List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        int[] pos = new int[26];
        for (int i = 0; i < s.length(); i++) {
            pos[s.charAt(i) - 'a'] = i; //获取每个字符的最远位置
        }

        int begin = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            end = Math.max(end, pos[c - 'a']);
            if(i == end){
                ans.add(end - begin + 1);
                begin = end + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(partitionLabels(s));
    }
}
