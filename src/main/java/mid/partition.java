package mid;

import java.util.*;

public class partition {
    static List<List<String>> ans = new ArrayList<>();
    static List<String> path = new ArrayList<>();
    public static List<List<String>> partition(String s) {
        backTracking(s, 0);
        return ans;
    }

    private static void backTracking(String s, int begin) {
        if(begin == s.length()){
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < s.length(); i++) {
            if(isH(s, begin, i)){
                String substring = s.substring(begin, i + 1);
                path.add(substring);
                backTracking(s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    private static boolean isH(String s, int l, int r) {
        while (l < r){
            if(s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(partition(s));
    }


}
