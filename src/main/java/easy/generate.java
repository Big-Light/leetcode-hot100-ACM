package easy;

import java.util.*;

public class generate {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(List.of(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> path = new ArrayList<>();
            path.add(1);
            if(i > 1){
                for (int j = 0; j < i - 1; j++) {
                    path.add(ans.get(i - 1).get(j) + ans.get(i - 1).get(j + 1));
                }
            }
            path.add(1);
            ans.add(path);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(generate(n));
    }
}
