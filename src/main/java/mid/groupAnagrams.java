package mid;
import java.io.*;
import java.util.*;

public class groupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String cur = new String(array);
            List<String> list = map.getOrDefault(cur, new ArrayList<>());
            list.add(str);
            map.put(cur, list);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] strs = line.split(" ");    //此处假设输入字符串无双引号，以空格作为间隔
        System.out.println(groupAnagrams(strs));
    }
}
