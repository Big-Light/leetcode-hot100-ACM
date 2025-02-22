package mid;


import java.util.*;

public class topKFrequent {
    public static int[] topKFrequent(int[] nums, int k) {
        // 统计每个元素的频率
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 创建最小堆，按频率升序排列
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {  //遍历map，而不是堆
            heap.offer(entry);
            if(heap.size() > k)
                heap.poll();
        }

        int[] ans = new int[k];
        int idx = 0;
        while (!heap.isEmpty()){
            ans[idx++] = heap.poll().getKey();
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        int k = sc.nextInt();
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }
}
