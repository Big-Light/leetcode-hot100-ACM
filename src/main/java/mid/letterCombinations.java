package mid;

import java.util.*;

public class letterCombinations {
    static HashMap<Character, String> map = new HashMap<>();
    static List<String> ans = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static List<String> letterCombinations(String digits) {
        if(digits.isEmpty())
            return ans;
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        backTracking(digits, 0);
        return ans;
    }

    private static void backTracking(String digits, int begin) {
        if(begin == digits.length()){
            ans.add(sb.toString());
            return;
        }

        char c = digits.charAt(begin);
        String s = map.get(c);
        for (char ch : s.toCharArray()) {
            sb.append(ch);
            backTracking(digits, begin + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(letterCombinations(s));
    }
}
