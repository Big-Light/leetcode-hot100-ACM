# readme

使用IDEA复习hot100，同时熟悉一下ACM模式

相较于核心模式而言，需要自己额外处理输入输出

可以先写好核心模式的主要逻辑，放到力扣测试代码正确性，再来写输入输出

这里我就是简单的使用力扣的用例来测试

比如第二题这种测试用例

```
["eat", "tea", "tan", "ate", "nat", "bat"]
```

我在输入的时候就不打`""`了，简单的用` `作为分隔符

而对于像第三题这样的测试用例

```
[100,4,200,1,3,2]
```

可以直接复制`100,4,200,1,3,2`，然后就是以`,`作为分隔符
---
以下是个人题解，包括简短思路和核心模式代码


# 一、哈希表

## [1. 两数之和](https://leetcode.cn/problems/two-sum/)

- 思路：map（key存储元素值，value为索引）。遍历数组的过程中，判断map中是否有`target - 当前元素`，如果没有就把当前元素插入到map。 

- 详细题解：[1.两数之和](https://leetcode.cn/problems/two-sum/solutions/2855965/1-liang-shu-zhi-he-by-fervent-wilburtzg-tkw1/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码

  ```java
  class Solution {
      public int[] twoSum(int[] nums, int target) {
          HashMap<Integer, Integer> map = new HashMap<>();
          int[] ans = new int[2];
  
          for (int i = 0; i < nums.length; i++) {
              if(map.containsKey(target - nums[i])){
                  ans[0] = map.get(target - nums[i]);
                  ans[1] = i;
                  break;
              }
              map.put(nums[i], i);
          }
          
          return ans;
      }
  }
  ```

  



## [49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams/)

- 思路：**字母异位词转化为字符数组，经过排序再转为字符串，是相同的**。利用这一点，使用map，key为经过排序后的字符串，value为当前异位词列表。最终将value值返回到一个列表中。

- 详细题解：[49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams/solutions/2854795/49-zi-mu-yi-wei-ci-fen-zu-by-fervent-wil-21ef/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码

  ```java
  class Solution {
      public List<List<String>> groupAnagrams(String[] strs) {
          HashMap<String , List<String>> map = new HashMap<>();
          for (String str : strs) {
              char[] array = str.toCharArray();
              Arrays.sort(array);
              String key = new String(array);
              List<String> list = map.getOrDefault(key, new ArrayList<>());
              list.add(str);
              map.put(key, list);
          }
          return new ArrayList<>(map.values());
      }
  }
  ```

  

## [128. 最长连续序列](https://leetcode.cn/problems/longest-consecutive-sequence/)

- 思路：一个数字能不能作为序列的开始，取决于它前一个数字是否存在；一个数字能不能作为序列的结束，取决于它后一个数字是否不存在。借助此，我们可以用一个set来现把所有元素加入，然后直接遍历set集合来看

- 详细题解：[128. 最长连续序列](https://leetcode.cn/problems/longest-consecutive-sequence/solutions/2879087/128-zui-chang-lian-xu-xu-lie-by-fervent-ptn22/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码

  ```java
  class Solution {
      public int longestConsecutive(int[] nums) {
          HashSet<Integer> set = new HashSet<>();
          int ans = 0;
          for (int num : nums) {
              set.add(num);
          }
          for (int num : set) {
              if(!set.contains(num - 1)){
                  int len = 1;
                  while (set.contains(++num))
                      len++;
                  ans = Math.max(ans, len);
              }
          }
          return ans;
      }
  }
  ```

  

------

# 二、双指针

## [283. 移动零](https://leetcode.cn/problems/move-zeroes/)

- 思路：**始终让慢指针指向零元素**（如果不为0直接自增后跳出当前循环进入下一次循环），快指针去寻找不为零的元素，然后找到了就设置慢指针所指的元素为快指针所指的元素，再让慢指针自增

  - 补充（2025/2/6）
  - **为什么 fast 无论如何都要加一**：无论 `fast` 指向的元素是否为 0，`fast` 都需要加一，以确保能够遍历数组中的每一个元素。如果 `fast` 指向的元素为 0，那么 `fast` 继续向前移动，寻找下一个非零元素。如果 `fast` 指向的元素不为 0，那么就将该元素移动到 `slow` 的位置，并将 `fast` 指向的位置置为 0，然后 `fast` 继续向前移动。

- 详细题解：[283. 移动零](https://leetcode.cn/problems/move-zeroes/solutions/2853159/283-yi-dong-ling-by-fervent-wilburtzg-7pnq/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码

  ```java
  class Solution {
      public void moveZeroes(int[] nums) {
          int slow = 0, fast = 1;
          while (fast < nums.length){
              if(nums[slow] == 0 && nums[fast] != 0){
                  nums[slow] = nums[fast];
                  nums[fast] = 0;
                  slow++;
              }
              if(nums[slow]!= 0)
                  slow++;
              fast++;
          }
      }
  }
  ```



## [11. 盛最多水的容器](https://leetcode.cn/problems/container-with-most-water/)

- 思路：从两边向中间靠，每次计算此时的体积（高为左右线的较低者），然后较低的线那一边向中间靠拢

- 详细题解：

- 参考代码

  ```java
  class Solution {
      public int maxArea(int[] height) {
          int low = 0;
          int high = height.length - 1;
          int ans = Integer.MIN_VALUE;
          while (low <= high){
              ans = Math.max(ans, (high - low) * Math.min(height[low], height[high]));
              if(height[low] < height[high])
                  low++;
              else 
                  high--;
          }
          return ans;
      }
  }
  ```



## [15. 三数之和](https://leetcode.cn/problems/3sum/)

- 思路：首先将数组排成有序。然后遍历数组，有两个退出条件：1.当前数字大于0，break；2.当前元素和前一个元素一样，continue（相当于对第一个元素的去重），然后在剩下元素中，从首尾向中间靠拢，如果满足条件了，再对左右指针所指元素去重

- 详细题解：[15. 三数之和](https://leetcode.cn/problems/3sum/solutions/2856912/15-san-shu-zhi-he-by-fervent-wilburtzg-pj4x/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public List<List<Integer>> threeSum(int[] nums) {
          List<List<Integer>> ans = new ArrayList<>();
          Arrays.sort(nums);
          for (int i = 0; i < nums.length; i++) {
              if(nums[i] > 0)
                  break;
              if(i > 0 && nums[i] == nums[i - 1])
                  continue;
              int l = i + 1, r = nums.length - 1;
              while (l < r){
                  int temp = nums[i] + nums[l] + nums[r];
                  if(temp < 0)
                      l++;
                  else if (temp > 0) {
                      r--;
                  }else {
                      ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                      while (l < r && nums[l] == nums[l + 1]) l++;
                      while (l < r && nums[r] == nums[r - 1]) r--;
                      l++;
                      r--;
                  }
              }
          }
          return ans;
      }
  }
  ```



## [42. 接雨水](https://leetcode.cn/problems/trapping-rain-water/)

- 思路：首尾位置不装雨水，其余`每个位置所装雨水 = min（左边柱子最大值，右边柱子最大值）- 当且柱子高`。左右指针的更新类似[11. 盛最多水的容器](https://leetcode.cn/problems/container-with-most-water/) 这一题，每次移动较低的那根柱子

  - 补充（此题步骤）
    - 数组长度<=2，直接返回0
    - 初始化左右最大值分别为第一个元素和最后一个元素
    - 初始化左右指针分别为第二个元素和倒数第二个元素
    - 在循环中先更新左右最大值，再根据左右最大值来决定此时结果应该加多少

- 详细题解：[42. 接雨水](https://leetcode.cn/problems/trapping-rain-water/solutions/2880186/42-jie-yu-shui-by-fervent-wilburtzg-8nmq/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int trap(int[] height) {
          if(height.length <= 2) return 0;
          int maxLeft = height[0];
          int maxRight = height[height.length - 1];
          int left = 0;
          int right = height.length - 1;
          int ans = 0;
  
          while (left <= right){
              maxLeft = Math.max(maxLeft, height[left]);
              maxRight = Math.max(maxRight, height[right]);
              if(maxLeft < maxRight)
                  ans += maxLeft - height[left++];
              else
                  ans += maxRight - height[right--];
          }
          return ans;
      }
  }
  ```

------



# 三、滑动窗口

## [3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)

- 思路：双指针之快慢指针。将快指针所遍历的字符加入set，**当添加失败时（说明有重复字符）**，将慢指针向快指针收缩，直到此时**快指针所指字符**可以加入set，再继续移动快指针  

- 详细题解：[3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/solutions/2893251/3-wu-zhong-fu-zi-fu-de-zui-chang-zi-chua-bh06/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int lengthOfLongestSubstring(String s) {
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
  }
  ```



## [438. 找到字符串中所有字母异位词](https://leetcode.cn/problems/find-all-anagrams-in-a-string/)

- 思路：快慢指针＋哈希（数组）。用数组哈希记录p每个字符出现的次数，快指针遍历s中的字符，每次让数组中对应字符的值减一，如果当前这个数组元素值小于0，说明出现了p中没有的字符，收缩慢指针同时恢复字符次数状态，直到慢指针赶上快指针的时候才能填补上这个差距。如果快慢指针的长度等于p的长度，可以将此时慢指针的坐标添加到结果  

- 详细题解：[438. 找到字符串中所有字母异位词](https://leetcode.cn/problems/find-all-anagrams-in-a-string/solutions/2893416/438-zhao-dao-zi-fu-chuan-zhong-suo-you-z-j73k/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public List<Integer> findAnagrams(String s, String p) {
          List<Integer> ans = new ArrayList<>();
          int[] cnt = new int[26];
          for (int i = 0; i < p.length(); i++) {
              cnt[p.charAt(i) - 'a']++;
          }
  
          for (int r = 0, l = 0; r < s.length(); r++) {
              char c = s.charAt(r);
              cnt[c - 'a']--;
              while (cnt[c - 'a'] < 0){	//这里前面的条件 l <= r && 一般就别写了，有时候写是正确的有时候不写是正确的，一律不写反而大多数都会正确
                  //左指针右移
                  cnt[s.charAt(l) - 'a']++;
                  l++;
              }
              if(r - l + 1 == p.length())
                  ans.add(l);
          }
          return ans;
      }
  }
  ```

------

# 四、字符串

## [560. 和为 K 的子数组](https://leetcode.cn/problems/subarray-sum-equals-k/)

- 思路：利用**前缀和**加上一个**哈希表**记录前缀和出现的次数，通过查找当前前缀和减去目标和`k`的值是否已经存在于哈希表中，快速统计满足条件的子数组个数。 

- 详细题解：[560. 和为 K 的子数组](https://leetcode.cn/problems/subarray-sum-equals-k/solutions/2891466/560-he-wei-k-de-zi-shu-zu-by-fervent-wil-ehgv/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int subarraySum(int[] nums, int k) {
          HashMap<Integer, Integer> map = new HashMap<>();
          map.put(0, 1);
          int ans = 0;
          int pre = 0;
          for (int i = 0; i < nums.length; i++) {
              pre += nums[i];
              ans += map.getOrDefault(sum - k, 0);
              map.put(pre, map.getOrDefault(pre, 0) + 1);
          }
          return ans;
      }
  }
  ```



## [239. 滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/)（难）

- 思路：借助**单调队列**，来存储可能成为最大值的元素。具体而言，如果当前元素比队列末尾元素要小，就直接进入队列。这样队头就是最大的元素。注意：在窗口移动过程中，如果当前队头元素和即将被（窗口）移除的元素一样大，那队头元素也要出队。

- 详细题解：[239. 滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/solutions/2861748/239-hua-dong-chuang-kou-zui-da-zhi-by-fe-2nw2/)

- 参考代码：

  ```java
  class Solution {
      public int[] maxSlidingWindow(int[] nums, int k) {
          int[] ans = new int[nums.length - k + 1];
          LinkedList<Integer> deque = new LinkedList<>();
  
          //未形成窗口时
          for (int i = 0; i < k; i++) {
              while (!deque.isEmpty() && deque.peekLast() < nums[i])
                  deque.removeLast();
              deque.addLast(nums[i]);
          }
          ans[0] = deque.peekFirst();
  
          //形成窗口时
          for (int i = k; i < nums.length; i++) {
              if(nums[i - k] == deque.peekFirst())
                  deque.removeFirst();
              while (!deque.isEmpty() && deque.peekLast() < nums[i])
                  deque.removeLast();
              deque.addLast(nums[i]);
              ans[i - k + 1] = deque.peekFirst();
          }
          return ans;
      }
  }
  ```

## [76. 最小覆盖子串](https://leetcode.cn/problems/minimum-window-substring/)

在做这题之前，可以先试一下这题的“简单版”——[438. 找到字符串中所有字母异位词](https://leetcode.cn/problems/find-all-anagrams-in-a-string/) 

- 思路：和438类似，不过这题要维护一个变量，用于记录t中字符是否匹配完了

- 详细题解：[这个是我在题解下面看的别人的](https://leetcode.cn/problems/minimum-window-substring/solutions/2682264/jin-tian-tu-ran-ling-guang-zha-xian-xie-lpo32/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码

  ```java
  class Solution {
      public String minWindow(String s, String t) {
          int[] cnt = new int[128];
          int count = 0;//记录t中需要匹配的字符个数
          for (int i = 0; i < t.length(); i++) {
              cnt[t.charAt(i) - 'A']++;
              count++;
          }
  
          int start = -1, end = s.length();
          for (int r = 0, l = 0; r < s.length(); r++) {
              char cur = s.charAt(r);
              if(--cnt[cur - 'A'] >= 0)//如果当前消除的是有效（t中的）字符
                  count--;
              while (l <= r && count == 0) {  //当前需要匹配的字符全部匹配完了
                  if(++cnt[s.charAt(l) - 'A'] > 0){   //说明当前左指针指向的字符是有效字符
                      if(r - l < end - start){    //更新有效区间
                          end = r;
                          start = l;
                      }
                      count++;
                  }
                  l++;
              }
          }
          return start == -1 ? "" : s.substring(start, end + 1);
      }
  }
  ```

------

# 五、普通数组

## [53. 最大子数组和](https://leetcode.cn/problems/maximum-subarray/)

- 思路：dp。dp[i]代表**以下标i结尾**的最大子数组和为dp[i]，所以我们**要找的是**： max (dp[i]), i ∈ {0, n - 1}

  还要注意的是，状态转移方程`dp[i] = max(dp[i-1]+nums[i]，nums[i]) `

- 详细题解：[53.最大子数组和](https://leetcode.cn/problems/maximum-subarray/solutions/2891973/53-zui-da-zi-shu-zu-he-by-fervent-wilbur-4u6d/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码

  ```java
  class Solution {
      public int maxSubArray(int[] nums) {
          int[] dp = new int[nums.length];
          int pre = nums[0];
          int ans = pre;
  
          for (int i = 1; i < nums.length; i++) {
              pre += nums[i];
              pre = Math.max(nums[i], pre);
              ans = Math.max(ans, pre);
          }
          return ans;
      }
  }
  ```

  因为当前状态仅和上一个状态有关，因此也可以这样写：

  ```java
  class Solution {
      public int maxSubArray(int[] nums) {
          int pre = 0, maxAns = nums[0];
          for (int x : nums) {
              pre = Math.max(pre + x, x);	//状态转移
              maxAns = Math.max(maxAns, pre);
          }
          return maxAns;
      }
  }
  ```



## [56. 合并区间](https://leetcode.cn/problems/merge-intervals/)

- 思路：用一个数组类型的列表来存储最终结果。遍历所给二维数组，如果当前数组的第一个元素<=列表中最后一个元素p（数组）的第二个元素，那就要对p的第二个数字进行更新。例如：[1,3], [2, 4] => [1, 4]

- 详细题解：[56.合并区间](https://leetcode.cn/problems/merge-intervals/solutions/2889374/56-he-bing-qu-jian-by-fervent-wilburtzg-v3kg/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int[][] merge(int[][] intervals) {
          Arrays.sort(intervals, (p, q) -> p[0] - q[0]);
          List<int[]> list = new ArrayList<>();
          for (int[] interval : intervals) {
              int m = list.size();
              if(m > 0 && list.get(m - 1)[1] >= interval[0])
                  list.get(m - 1)[1] = Math.max(interval[1], list.get(m - 1)[1]);
              else
                  list.add(interval);
          }
          return list.toArray(new int[list.size()][]);
      }
  }
  ```

## [189. 轮转数组](https://leetcode.cn/problems/rotate-array/)

- 思路：先对整个数组逆置。再对前k个元素逆置。再对后n - k个元素逆置。**注意先对k进行处理**

- 详细题解：[189.轮转数组](https://leetcode.cn/problems/rotate-array/solutions/2890410/189-lun-zhuan-shu-zu-by-fervent-wilburtz-1xai/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码

  ```java
  class Solution {
      public void rotate(int[] nums, int k) {
          int n = nums.length;
          k = k % n;
          reverse(nums, 0, n - 1);
          reverse(nums, 0, k - 1);
          reverse(nums, k, n - 1);
      }
  
      private void reverse(int[] nums, int low, int high) {
          while (low <= high){
              int temp = nums[high];
              nums[high--] = nums[low];
              nums[low++] = temp;
          }
      }
  }
  ```



## [238. 除自身以外数组的乘积](https://leetcode.cn/problems/product-of-array-except-self/)

- 思路：一种简单的思路是，使用两个数组，一个数组存储每个元素左边元素的乘积，另一个数组存储每个元素右边元素的乘积。最后结果就是这两个数组对应位置相乘，就是每个元素除自身以外数组的乘积。但是可以省略记录右边元素乘积的这个数组，之间将其存储到结果数组中。见代码。

- 详细题解：[238.除自身以外数组的乘积](https://leetcode.cn/problems/product-of-array-except-self/solutions/2890490/238-chu-zi-shen-yi-wai-shu-zu-de-cheng-j-vkog/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码

  ```java
  class Solution {
      public int[] productExceptSelf(int[] nums) {
          int n = nums.length;
          int[] ans = new int[n];	//记录左边元素的乘积
          ans[0] = 1;
          for (int i = 1; i < n; i++) {
              ans[i] = ans[i - 1] * nums[i - 1];
          }
          int temp = 1;
          for (int i = n - 1; i >= 0; i--){
              ans[i] = ans[i] * temp;
              temp *= nums[i];
          }
          return ans;
      }
  }
  ```



## [41. 缺失的第一个正数](https://leetcode.cn/problems/first-missing-positive/) （★★

- 思路：数组内置哈希（置换法）——源自GPT

  1. **将正整数放到对应的位置上：**
     - 如果数组中存在数字 x，且 1≤x≤n（n 是数组长度），我们可以尝试将它放到索引 x−1 的位置上。
     - 通过不断交换数字和它应该在的位置，将所有可能的正整数放到正确的位置。
  2. **遍历数组寻找缺失的最小正整数：**
     - 经过上述处理后，索引 i 应该存储 i+1。
     - 如果某个位置 i不满足 nums[i]=i+1，那么 i+1 就是缺失的最小正整数。
  3. **若所有位置都正确：**
     - 如果数组中所有位置都满足 nums[i]=i+1，则缺失的最小正整数是 n+1（数组范围之外的第一个正整数）。

- 详细题解：[41.缺失的第一个正数](https://leetcode.cn/problems/first-missing-positive/solutions/2891362/41-que-shi-de-di-yi-ge-zheng-shu-by-ferv-k9pc/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int firstMissingPositive(int[] nums) {
          int n = nums.length;
  
          int x = 0;
          for (int i = 0; i < n; i++) {
              x = nums[i];
              while (x > 0 && x <= n && x != nums[x - 1]){
                  int temp = nums[x - 1];
                  nums[x - 1] = x;
                  x = temp;
              }
          }
  
          for (int i = 0; i < n; i++) {
              if(nums[i] != i + 1)
                  return i + 1;
          }
          return n + 1;
      }
  }
  ```

------



# 六、矩阵

## [73. 矩阵置零](https://leetcode.cn/problems/set-matrix-zeroes/)

- 思路：1、扫描首行首列，标记首行首列是否要置零；2、扫描剩余元素（非首行首列的其余元素），如果某个元素为0，就将其首行对应行索引，首列对应列索引设置为0，方便后续对该行该列置零；3、扫描首行首列，如果有元素0，就将对应列/行置零。4、最后根据第一步的标记位，对首行首列置零

- 详细题解：[73.矩阵置零](https://leetcode.cn/problems/set-matrix-zeroes/solutions/2880266/73-ju-zhen-zhi-ling-by-fervent-wilburtzg-n2br/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public void setZeroes(int[][] matrix) {
          int m = matrix.length;
          int n = matrix[0].length;
          boolean b1 = false, b2 = false;
  
          //1、先扫描第一行第一列，记录第一行，第一列是否要置0
          for (int i = 0; i < n; i++)
              if(matrix[0][i] == 0)
                  b1 = true;
  
          for (int i = 0; i < m; i++)
              if(matrix[i][0] == 0)
                  b2 = true;
  
          //2、扫描其余，如果有元素为0，将它最左方和最上方的元素置零
          for (int i = 1; i < m; i++)
              for (int j = 1; j < n; j++)
                  if(matrix[i][j] == 0){
                      matrix[0][j] = 0;
                      matrix[i][0] = 0;
                  }
  
          //3、按照首行首列所记录元素，如果为0将对应行列置为0
          for (int i = 1; i < n; i ++)
              if(matrix[0][i] == 0)
                  for (int j = 1; j < m; j++)
                      matrix[j][i] = 0;
          for (int i = 1; i < m; i++)
              if(matrix[i][0] == 0)
                  Arrays.fill(matrix[i], 0);
  
          //4、根据第一步得到的结果对第一行列置零
          if(b1)
              Arrays.fill(matrix[0], 0);
              
          if(b2)
          for (int j = 0; j < m; j++)
                  matrix[j][0] = 0;
              
      }
  }
  ```



## [54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix/)

- 思路：设置上下左右四个指针，按照→↓←↑的方向，每次到了最边界，就缩减边界。

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public List<Integer> spiralOrder(int[][] matrix) {
          List<Integer> ans = new ArrayList<>();
          int m = matrix.length;
          int n = matrix[0].length;
          int l = 0, r = n - 1;
          int s = 0, x = m - 1;
          int i = 0;
          while (i < m * n){
              for (int j = l; j <= r; j++){
                  ans.add(matrix[s][j]);
                  i++;
                  if(i == m * n)
                      return ans;
              }
              s++;
  
              for (int j = s; j <= x; j++){
                  ans.add(matrix[j][r]);
                  i++;
                  if(i == m * n)
                      return ans;
              }
              r--;
  
              for (int j = r; j >= l; j--){
                  ans.add(matrix[x][j]);
                  i++;
                  if(i == m * n)
                      return ans;
              }
              x--;
  
              for (int j = x; j >= s; j--){
                  ans.add(matrix[j][l]);
                  i++;
                  if(i == m * n)
                      return ans;
              }
              l++;
          }
          return ans;
      }
  }
  ```



## [48. 旋转图像](https://leetcode.cn/problems/rotate-image/)

- 思路：先沿着主对角线交换元素，然后再对每一行进行逆置

- 详细题解：

- 参考代码

  ```java
  class Solution {
      public void rotate(int[][] matrix) {
          int m = matrix.length;
          int n = matrix[0].length;
          for (int i = 0; i < m; i++) {
              for (int j = 0; j < i; j++) {
                  int temp = matrix[i][j];
                  matrix[i][j] = matrix[j][i];
                  matrix[j][i] = temp;
              }
          }
  
          for (int i = 0; i < m; i++) {
              int l = 0, r = n - 1;
              while (l <= r){
                  int temp = matrix[i][l];
                  matrix[i][l++] = matrix[i][r];
                  matrix[i][r--] = temp;
              }
          }
      }
  }
  ```



## [240. 搜索二维矩阵 II](https://leetcode.cn/problems/search-a-2d-matrix-ii/)

- 思路：以第一行最后一个元素作为根节点，可将矩阵看作是一个BST。**在不越界的前提下**，如果元素比target大，往左找，否则，往下找

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public boolean searchMatrix(int[][] matrix, int target) {
          int m = matrix.length;
          int n = matrix[0].length;
  
          int i = 0;
          int j = n - 1;
          while (i < m && j >=0){
              int temp = matrix[i][j];
              if(target > temp){
                  //往下找
                  i++;
              } else if (target < temp) {
                  //往左找
                  j--;
              }else 
                  return true;
          }
          return false;
      }
  }
  ```

------



# 七、链表

## [160. 相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/)

- 思路：如果两个链表相交，设相交部分为c，另外两个链表各自的部分分别为a、b。有a + c + b = b + c + a，这就是说，当两个指针从各自链表开头走，走完链表再从对方链表的开头走，它们会在相交部分的起点相遇。**这里的代码写法很妙，值得记忆一下**。而如果两个链表不相交，核心是m + n = n + m，这就是说，当两个指针各自走完自己的链表，再去走完对方的链表，此时两个指针均指向一个共同的“交点‘——null

- 详细题解：[160.相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/solutions/2854505/160-xiang-jiao-lian-biao-by-fervent-wilb-krm1/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码

  ```java
  public class Solution {
      public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
          //两个指针分别走完各自的链表，再从对方的链表节点开始走，然后它俩相等的时候，就是相交节点
          ListNode A = headA;
          ListNode B = headB;
  
          while (A != B){
              A = A == null ? headB : A.next;
              B = B == null ? headA : B.next;
          }
          return A;
      }
  }
  ```

## [206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/)

最基础的操作，这题是后面好几题进阶链表反转的基础核心

- 思路：借用三个指针，pre，temp，cur，这题可以看着代码手动模拟一下

- 详细题解：[206.反转链表](https://leetcode.cn/problems/reverse-linked-list/solutions/2853587/206-fan-zhuan-lian-biao-by-fervent-wilbu-hrd6/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public ListNode reverseList(ListNode head) {
          ListNode pre = null;
          ListNode temp = pre;
          
          while (head != null){
              temp = head.next;
              head.next = pre;
              pre = head;
              head = temp;
          }
          return pre;
      }
  }
  ```



## [234. 回文链表](https://leetcode.cn/problems/palindrome-linked-list/)

- 思路：补充最优思路（达到`O(1)`空间复杂度的解）。1.用快慢指针找到中间节点；2.翻转后半段链表；3.比较前后两段链表，就可以得到结果了

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public boolean isPalindrome(ListNode head) {
          if (head == null || head.next == null) {
              return true;
          }
  
          // 1. 快慢指针找中点
          ListNode slow = head, fast = head;
          while (fast != null && fast.next != null) {
              slow = slow.next;
              fast = fast.next.next;
          }
  
          // 2. 反转后半部分链表
          ListNode reversedHalf = reverseList(slow);
  
          // 3. 比较前后两部分
          ListNode p1 = head, p2 = reversedHalf;
          boolean result = true;
          while (p2 != null) {  // 只需比较后半部分的长度
              if (p1.val != p2.val) {
                  result = false;
                  break;
              }
              p1 = p1.next;
              p2 = p2.next;
          }
          // 4. 恢复链表（可选）
          reverseList(reversedHalf);
          return result;
      }
  
      private ListNode reverseList(ListNode head) {
          ListNode pre = null, cur = head;
          while (cur != null) {
              ListNode next = cur.next;
              cur.next = pre;
              pre = cur;
              cur = next;
          }
          return pre;
      }
  }
  ```



## [141. 环形链表](https://leetcode.cn/problems/linked-list-cycle/)

- 思路：快慢指针。如果有环，二者肯定能相遇

- 详细题解：[141.环形链表](https://leetcode.cn/problems/linked-list-cycle/solutions/2885957/141-huan-xing-lian-biao-by-fervent-wilbu-o5ux/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  public class Solution {
      public boolean hasCycle(ListNode head) {
          if(head == null)
              return false;
          ListNode slow = head;
          ListNode fast = head;
  
          while (fast != null && fast.next != null){
              slow = slow.next;
              fast = fast.next.next;
              if(slow == fast)
                  return true;
          }
          return false;
      }
  }
  ```



## [142. 环形链表 II](https://leetcode.cn/problems/linked-list-cycle-ii/)

- 思路：直接给结论，还是快慢指针，当二者相遇的时候，再创建一个指针从链表头节点开始走，同时慢指针也开始走，当这俩指针相遇的时候，就是环的入口。详细验证请看详细题解

- 详细题解：[142.环形链表Ⅱ](https://leetcode.cn/problems/linked-list-cycle-ii/solutions/2854646/142-huan-xing-lian-biao-ii-by-fervent-wi-6ea2/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  public class Solution {
      public ListNode detectCycle(ListNode head) {
          ListNode slow = head;
          ListNode fast = head;
          while (fast != null && fast.next != null){
              slow = slow.next;
              fast = fast.next.next;
  
              if (slow == fast){
                  //此时相遇了，让slow和头指针一起往后走
                  ListNode cur = head;
                  while (cur != slow){
                      cur = cur.next;
                      slow = slow.next;
                  }
                  return cur;
              }
          }
          return null;
      }
  }
  ```



## [21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/)

- 思路：双指针，两两比较

- 详细题解：[21.合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/solutions/2888519/21-he-bing-liang-ge-you-xu-lian-biao-by-2ak4k/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
          ListNode dummy = new ListNode();
          ListNode cur = dummy;
          while (list1 != null && list2 != null){
              if(list1.val < list2.val){
                  cur.next = list1;
                  list1 = list1.next;
              }else {
                  cur.next = list2;
                  list2 = list2.next;
              }
  
              cur = cur.next;
          }
          cur.next = list1 == null ? list2 : list1;
          return dummy.next;
      }
  }
  ```



### ❤️类似题目：”两数相加“类型

- [21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/) 
- [67. 二进制求和](https://leetcode.cn/problems/add-binary/) 
- [2. 两数相加](https://leetcode.cn/problems/add-two-numbers/) 



## [2. 两数相加](https://leetcode.cn/problems/add-two-numbers/)

- 思路：通过创建新节点，同时需要一位来记录进位。新节点的值设置为`(x + y + temp) % 10`，进位temp更新为`(x + y + temp) / 10`。注意这个**while循环的条件**和**获取当前两个节点值的三目运算符写法**，这个在这种类型的题目中经常这样使用

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
          ListNode ans = new ListNode();
          ListNode dummy = ans;
          int temp = 0;
          while (l1 != null || l2 != null){
              int x = l1 == null ? 0 : l1.val;
              int y = l2 == null ? 0 : l2.val;
              int cur = (x + y + temp) % 10;
              ans.next = new ListNode(cur);
  
              temp = (x + y + temp) / 10;    //作为下一个节点要累加的值
              if(l1 != null)
                  l1 = l1.next;
              if(l2 != null)
                  l2 = l2.next;
              ans = ans.next;
          }
  
          if(temp == 1)
              ans.next = new ListNode(1);
  
          return dummy.next;
      }
  }
  ```



## [19. 删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)

- 思路：要删除这个节点，只需找到它前面的一个节点就可以了。快慢指针，快指针从哨兵节点开始，先走n+1步，此时快指针在要删除节点的前一个节点。此时快慢指针同时移动（慢指针也是从哨兵节点开始的），当快指针指向null，此时慢指针就正好指向要删除节点的前一个节点。

- 详细题解：[19.删除链表的倒数第N个节点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/solutions/2854411/19-shan-chu-lian-biao-de-dao-shu-di-n-ge-6nyf/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public ListNode removeNthFromEnd(ListNode head, int n) {
          ListNode slow = new ListNode();
          slow.next = head;
          ListNode fast = slow, dummy = slow;
  
          for (int i = 0; i <= n; i++) {
              fast = fast.next;
          }
          while (fast != null){
              slow = slow.next;
              fast = fast.next;
          }
          slow.next = slow.next.next;
          return dummy.next;
      }
  }
  ```



## [24. 两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs/)

- 思路：这题就是`两个数交换`的稍微复杂一点的版本。用temp指向要交换两个节点的前一个节点，head指向要交换两个节点的第一个节点

- 详细题解：[24.两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs/solutions/2854283/24-liang-liang-jiao-huan-lian-biao-zhong-0f8x/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  //这样写更直观
  class Solution {
      public ListNode swapPairs(ListNode head) {
          ListNode dummyHead = new ListNode(0);
          dummyHead.next = head;
          ListNode temp = dummyHead;  //temp要始终位于要交换的两个节点之前的一个节点
          //如果没有节点或只有一个节点，直接返回
          while (temp.next != null && temp.next.next != null){
              ListNode node1 = temp.next;
              ListNode node2 = temp.next.next;
              //开始交换这两个节点
              temp.next = node2;  //先让temp连向node2
              node1.next = node2.next;    //node2后面还连接着剩余的链表，要先把剩余的接上再调整node2和node1的关系
              node2.next = node1;     //此时让node2的后面连接node1，现在的关系就是temp——>node2——>node1了
              //更新temp，不用更新node1和node2，下次进入循环会更新它俩
              temp = node1;   //假如后面还有两个节点要更换，比如node3和node4，那node3的前面节点此时就是现在的node1，所以要设置temp指向node1
  
          }
          //虚拟节点的后一个才是真正的头节点
          return dummyHead.next;
      }
  }
  
  //简单写就是这样
  class Solution {
      public ListNode swapPairs(ListNode head) {
          ListNode dummy = new ListNode();
          dummy.next = head;
          ListNode temp = dummy;
          
          while (head != null && head.next != null){
              temp.next = head.next;
              head.next = head.next.next;
              temp.next.next = head;
              temp = head;
              head = head.next;
          }
          
          return dummy.next;
      }
  }
  ```



## [25. K 个一组翻转链表](https://leetcode.cn/problems/reverse-nodes-in-k-group/)

这题还是有点难度的，在做这题之前，可以先理解这几题：

1. [206.翻转链表](https://leetcode.cn/problems/reverse-linked-list/description/)
2. [92.翻转链表Ⅱ](https://leetcode.cn/problems/reverse-linked-list-ii/description/)



- 思路：先统计链表长度，然后每翻转一组，长度-k，直到不能翻转为止。再翻转每组时，要维护好这段链表和其前后的关系。这题和上面两题不同的是，要显式的为这k组链表的第一个节点来声明，以此来维护前后关系。这题最好还是手动模拟一两轮就知道是怎么回事了

- 详细题解：[25.K个一组翻转链表](https://leetcode.cn/problems/reverse-nodes-in-k-group/solutions/2895834/25-k-ge-yi-zu-fan-zhuan-lian-biao-you-sh-iko4/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public ListNode reverseKGroup(ListNode head, int k) {
          ListNode dummy = new ListNode();
          dummy.next = head;
          ListNode cur = head;
  
          int count = 0;
          while (cur != null){
              cur = cur.next;
              count++;
          }
          cur = head;
          ListNode node = dummy;
          ListNode pre = null;
          ListNode temp = pre;
          while (k <= count){
              for (int i = 0; i < k; i++) {
                  temp = cur.next;
                  cur.next = pre;
                  pre = cur;
                  cur = temp;
              }
  
              ListNode tail = node.next;
              node.next = pre;
              tail.next = cur;
              node = tail;
              count -= k;
          }
          return dummy.next;
      }
  }
  ```



## [138. 随机链表的复制](https://leetcode.cn/problems/copy-list-with-random-pointer/)

还有一个[133. 克隆图](https://leetcode.cn/problems/clone-graph/) ，本质上和这题一样

- 思路：哈希表，先创建原链表每个节点和新链表每个节点的映射关系，然后再对新链表每个节点的next和random关系进行维护

- 详细题解：[138.随机链表的复制](https://leetcode.cn/problems/copy-list-with-random-pointer/solutions/2894489/138-sui-ji-lian-biao-de-fu-zhi-by-ferven-rrh8/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public Node copyRandomList(Node head) {
          HashMap<Node, Node> hashMap = new HashMap<>();
          Node cur = head;
  
          while (cur != null){
              hashMap.put(cur, new Node(cur.val));
              cur = cur.next;
          }
          cur = head;
          while (cur != null){
              hashMap.get(cur).next = hashMap.get(cur.next);
              hashMap.get(cur).random = hashMap.get(cur.random);
              cur = cur.next;
          }
          return hashMap.get(head);
      }
  }
  ```



## [148. 排序链表](https://leetcode.cn/problems/sort-list/)

分治+递归，有点像<u>归并排序</u>（这个我暂时还没学习）

- 思路：将链表分成两段，这两段调用这个函数都排成有序的了，然后再对这两段链表做合并，类似[21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/) 

- 详细题解：[148.排序链表](https://leetcode.cn/problems/sort-list/solutions/2914305/148-pai-xu-lian-biao-by-fervent-wilburtz-z8l4/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public ListNode sortList(ListNode head) {
          if(head == null || head.next == null)
              return head;
          ListNode slow = head, fast = head.next;
          while (fast != null && fast.next != null){
              slow = slow.next;
              fast = fast.next.next;
          }
          ListNode temp = slow.next;
          slow.next = null;
          ListNode left = sortList(head);
          ListNode right = sortList(temp);
  
          ListNode dummy = new ListNode();
          ListNode cur = dummy;
          while (left != null && right != null){
              if(left.val < right.val){
                  cur.next = left;
                  left = left.next;
              }else {
                  cur.next = right;
                  right = right.next;
              }
              cur = cur.next;
          }
          cur.next = left == null ? right : left;
          return dummy.next;
      }
  }
  ```



## [23. 合并 K 个升序链表](https://leetcode.cn/problems/merge-k-sorted-lists/)(难★)

- 思路：分治、归并

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public ListNode mergeKLists(ListNode[] lists) {
          if(lists == null || lists.length == 0)
              return null;
          return merge(lists, 0, lists.length - 1);
      }
  
      private ListNode merge(ListNode[] lists, int begin, int end) {
          if(begin == end)
              return lists[begin];
          int mid = begin + (end - begin)/2;
          ListNode left = merge(lists, begin, mid);
          ListNode right = merge(lists, mid + 1,  end);
          return mergeList(left, right);
      }
  
      //合并两个有序链表
      private ListNode mergeList(ListNode a, ListNode b) {
          if(a == null || b == null)
              return a == null ? b : a;
          if(a.val < b.val){
              a.next = mergeList(a.next, b);
              return a;
          }else {
              b.next = mergeList(a, b.next);
              return b;
          }
      }
  }
  ```



## [146. LRU 缓存](https://leetcode.cn/problems/lru-cache/)(难)

属于是完全不想写第二遍的题目

- 思路：抽书

  - 双向循环链表（带哨兵节点）负责方便插入删除
  - 哈希表负责O(1)复杂度来寻找元素

  要写三个函数： 

  - 在双向链表中移除节点x
  - 在链表头部添加一个节点x
  - 确定当前链表中是否有节点x（通过哈希表对值为key的映射来判断

- 参考题解：[灵茶山的题解](https://leetcode.cn/problems/lru-cache/solutions/2456294/tu-jie-yi-zhang-tu-miao-dong-lrupythonja-czgt/?envType=study-plan-v2&envId=top-100-liked)

- 详细题解：

  ```java
  class LRUCache {
  
      //双向循环链表的节点结构
      private static class Node{
          int key,value;
          Node prev, next;
  
          Node(int key, int value){
              this.key = key;
              this.value = value;
          }
      }
  
      private final int capacity;
      private final Node dummy = new Node(0,0);   //哨兵节点
      //哈希表，用以保存值和节点的关系，
      private final HashMap<Integer, Node> keyToNode = new HashMap<>();
      public LRUCache(int capacity) {
          this.capacity = capacity;
          dummy.prev = dummy;
          dummy.next = dummy;
      }
  
      public int get(int key) {
          //如果哈希表不存在，返回-1
          Node node = getNode(key);
          //如果哈希表存在，则返回该节点的值，同时该节点用了，要把它移动到链表头部，怎么移动呢，直接删除再添加到头部
          return node == null ? -1: node.value;
      }
  
      public void put(int key, int value) {
          //如果哈希表有这个key，就把它移到链表头部，同时更新value
          Node node = getNode(key);
          if(node != null){
              //移动操作在getNode里面做过了，直接更新返回即可
              node.value = value;
              return;
          }
          //如果没有这个key，就新建一个节点，插入头部，同时插入哈希表
          node = new Node(key, value);
          pushFront(node);
          keyToNode.put(key, node);
          if(keyToNode.size() > capacity){
              //如果插入后大于容量，就要移除链表尾部的节点,同时在哈希表中也要移除
              Node backNode = dummy.prev; //哨兵的前一个节点就是链表尾部节点
              remove(backNode);
              keyToNode.remove(backNode.key);
          }
      }
  
      //get和put都需要确定哈希表中是否有key所对应的节点，因此可以抽象成一个函数
      private Node getNode(int key){
          //不含这个key所对应的节点
          if(!keyToNode.containsKey(key)){
              return null;
          }
          //含的话，不仅要返回这个节点，还要把它移动到链表头部，怎么移动呢，直接删除再添加到头部
          Node node = keyToNode.get(key);
          remove(node);
          pushFront(node);
          return node;
      }
  
      //删除一个节点
      private void remove(Node x){
          x.next.prev = x.prev;
          x.prev.next = x.next;
      }
      //在链表头部添加一个节点
      private void pushFront(Node x){
          dummy.next.prev = x;
          x.next = dummy.next;
          x.prev = dummy;
          dummy.next = x;
      }
  }
  ```

------



# 八、二叉树

## [94. 二叉树的中序遍历](https://leetcode.cn/problems/binary-tree-inorder-traversal/)

- 思路：递归or迭代（栈）。这两种都要掌握。递归就不说了，说一下迭代的大致思路

  维护一个栈，一直往最左边走，同时不断添加节点到栈，找到最左节点后加入结果，然后向右遍历，详细过程见代码，可手动模拟一下就清晰了

- 详细题解：

- 参考代码：

  - 递归版

  ```java
  class Solution {
      List<Integer> ans = new ArrayList<>();
      public List<Integer> inorderTraversal(TreeNode root) {
          lnr(root);
          return ans;
      }
  
      private void lnr(TreeNode root) {
          if(root == null)
              return;
          lnr(root.left);
          ans.add(root.val);
          lnr(root.right);
      }
  }
  ```

  - 迭代版

  ```java
  class Solution {
      public List<Integer> inorderTraversal(TreeNode root) {
          List<Integer> ans = new ArrayList<>();
          if(root == null)
              return ans;
          ArrayDeque<TreeNode> stack = new ArrayDeque<>();
          while (root != null || !stack.isEmpty()){
              while (root != null){
                  stack.push(root);
                  root = root.left;
              }
              root = stack.pop();
              ans.add(root.val);
              root = root.right;
          }
          return ans;
      }
  }
  ```



## [104. 二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree/)

- 思路：二叉树的最大深度等于`MAX(左子树最大深度，右子树最大深度)+1`

- 详细题解：[104.二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree/solutions/2862911/104-er-cha-shu-de-zui-da-shen-du-by-ferv-hghs/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int maxDepth(TreeNode root) {
          if(root == null)
              return 0;
          int left = maxDepth(root.left);
          int right = maxDepth(root.right);
          return Math.max(left, right) + 1;
      }
  }
  ```



## [226. 翻转二叉树](https://leetcode.cn/problems/invert-binary-tree/)

- 思路：类似在数组中交换两个元素。这题先交换两个左右子树，然后再递归的对左子树和右子树这样操作

- 详细题解：[226.翻转二叉树](https://leetcode.cn/problems/invert-binary-tree/solutions/2862889/226-fan-zhuan-er-cha-shu-by-fervent-wilb-fspr/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public TreeNode invertTree(TreeNode root) {
          if(root == null)
              return null;
          TreeNode temp = root.left;
          root.left = root.right;
          root.right = temp;
          invertTree(root.left);
          invertTree(root.right);
          return root;
      }
  }
  ```



## [101. 对称二叉树](https://leetcode.cn/problems/symmetric-tree/)

- 思路：左右子树要想对称，左子树的右节点——右子树的左节点；左子树的左节点——右子树的右节点

- 详细题解：[101.对称二叉树](https://leetcode.cn/problems/symmetric-tree/solutions/2866322/101-dui-cheng-er-cha-shu-by-fervent-wilb-dtst/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public boolean isSymmetric(TreeNode root) {
          return isD(root.left, root.right);
      }
  
      private boolean isD(TreeNode left, TreeNode right) {
          if(left == null && right == null)
              return true;
          else if (left != null && right != null) {
              if(left.val != right.val)
                  return false;
          }else 
              return false;
          boolean b1 = isD(left.left, right.right);
          boolean b2 = isD(left.right, right.left);
          return b1 && b2;
      }
  }
  ```



## [543. 二叉树的直径](https://leetcode.cn/problems/diameter-of-binary-tree/)

该题和[124. 二叉树中的最大路径和](https://leetcode.cn/problems/binary-tree-maximum-path-sum/) 类似

- 思路：二叉树的直径 = MAX(ans， 左子树+右子树)。这题特殊的是这个'链长'的写法。如果为空节点了，返回-1，左子树和右子树的链长在递归的基础上+1，可抵消这个-1，然后递归函数返回左右子树中较大的那个

- 详细题解：[543.二叉树的直径](https://leetcode.cn/problems/diameter-of-binary-tree/solutions/2867461/543-er-cha-shu-de-zhi-jing-by-fervent-wi-oii5/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      int ans = 0;
      public int diameterOfBinaryTree(TreeNode root) {
          dfs(root);
          return ans;
      }
  
      private int dfs(TreeNode root) {
          if(root == null)
              return -1;
          int left = dfs(root.left) + 1;
          int right = dfs(root.right) + 1;
          ans = Math.max(ans, left + right);
          return Math.max(left, right);
      }
  }
  ```



## [102. 二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/)

BFS，迭代（队列）。这个题也是基础，后续很多题都是在这个基础上改了一点点

- 思路：先把头节点入队列，每次弹出队列中的节点，并把该节点的左右节点入队列

- 详细题解：[102.二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/solutions/2866589/102-er-cha-shu-de-ceng-xu-bian-li-by-fer-rs2t/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public List<List<Integer>> levelOrder(TreeNode root) {
          List<List<Integer>> ans = new ArrayList<>();
          if(root == null)
              return ans;
          ArrayDeque<TreeNode> deque = new ArrayDeque<>();
          deque.offer(root);
          while (!deque.isEmpty()){
              int size = deque.size();
              List<Integer> path = new ArrayList<>();
              for (int i = 0; i < size; i++) {
                  TreeNode remove = deque.remove();
                  path.add(remove.val);
                  if(remove.left != null)
                      deque.offer(remove.left);
                  if(remove.right != null)
                      deque.offer(remove.right);
              }
              ans.add(path);
          }
          return ans;
      }   
  }
  ```

### ❤️二叉树BFS（队列）类题目

- [102. 二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/) 
- [199. 二叉树的右视图](https://leetcode.cn/problems/binary-tree-right-side-view/) 

下面为150的题目

- [117. 填充每个节点的下一个右侧节点指针 II](https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/) 
- [173. 二叉搜索树迭代器](https://leetcode.cn/problems/binary-search-tree-iterator/) 
- [637. 二叉树的层平均值](https://leetcode.cn/problems/average-of-levels-in-binary-tree/) 
- [103. 二叉树的锯齿形层序遍历](https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/) 



## [108. 将有序数组转换为二叉搜索树](https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/)

- 思路：类似二分查找

- 详细题解：[108.将有序数组转换为二叉搜索树](https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/solutions/2868819/108-jiang-you-xu-shu-zu-zhuan-huan-wei-e-0vam/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public TreeNode sortedArrayToBST(int[] nums) {
          return build(0, nums.length - 1, nums);
      }
  
      private TreeNode build(int low, int high, int[] nums) {
          if(low > high)
              return null;
          int mid = low + (high - low) / 2;
          TreeNode root = new TreeNode(nums[mid]);
          root.left = build(low, mid - 1, nums);
          root.right = build(mid + 1, high, nums);
          return root;
      }
  }
  ```



### ❤️BST题目汇总

分治：

1. [108. 将有序数组转换为二叉搜索树](https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/) 
2. 、

二叉树性质（严格递增）：

1. [98. 验证二叉搜索树](https://leetcode.cn/problems/validate-binary-search-tree/) 
2. [230. 二叉搜索树中第 K 小的元素](https://leetcode.cn/problems/kth-smallest-element-in-a-bst/) 
3. [530. 二叉搜索树的最小绝对差](https://leetcode.cn/problems/minimum-absolute-difference-in-bst/) 



## [98. 验证二叉搜索树](https://leetcode.cn/problems/validate-binary-search-tree/)

- 思路：如果当前节点<=前一个节点，说明不满足BST性质。这题要特别注意节点值的范围，要用long型

- 详细题解：[98.验证二叉搜索树](https://leetcode.cn/problems/validate-binary-search-tree/solutions/2866694/98-yan-zheng-er-cha-sou-suo-shu-by-ferve-59bb/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      long pre = Long.MIN_VALUE;
      public boolean isValidBST(TreeNode root) {
          if(root == null)
              return true;
          if(!isValidBST(root.left))
              return false;
          if(root.val <= pre)
              return false;
          pre = root.val;
          if(!isValidBST(root.right))
              return false;
          return true;
      }
  }
  ```



## [230. 二叉搜索树中第 K 小的元素](https://leetcode.cn/problems/kth-smallest-element-in-a-bst/)

- 思路：中序遍历，初始化index = 1

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      int ans = Integer.MAX_VALUE;
      int index = 1;
      public int kthSmallest(TreeNode root, int k) {
          dfs(root, k);
          return ans;
      }
  
      private void dfs(TreeNode root, int k) {
          if(root == null)
              return;
          dfs(root.left, k);
          if(index == k)
              ans = root.val;
          index++;
          dfs(root.right, k);
      }
  }
  ```



## [199. 二叉树的右视图](https://leetcode.cn/problems/binary-tree-right-side-view/)

- 思路：层序遍历，到了最后一个节点就保存记录。

- 详细题解：[199.二叉树的右视图](https://leetcode.cn/problems/binary-tree-right-side-view/solutions/2896922/199-er-cha-shu-de-you-shi-tu-by-fervent-zizpa/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public List<Integer> rightSideView(TreeNode root) {
          List<Integer> ans = new ArrayList<>();
          ArrayDeque<TreeNode> deque = new ArrayDeque<>();
          if(root == null)
              return ans;
          deque.offer(root);
          while (!deque.isEmpty()){
              int size = deque.size();
              for (int i = 0; i < size; i++) {
                  TreeNode node = deque.remove();
                  if(i == size - 1)
                      ans.add(node.val);
                  if(node.left != null)
                      deque.offer(node.left);
                  if(node.right != null)
                      deque.offer(node.right);
              }
          }
          return ans;
      }
  }
  ```



## [114. 二叉树展开为链表](https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/)

- 思路：不断地将左子树替换为root的右子树，并将之前的右子树拼到其新右子树（也就是之前的左子树）的最右节点之后，然后更新root

- 详细题解：[114.二叉树展开为链表](https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/solutions/2896959/114-er-cha-shu-zhan-kai-wei-lian-biao-by-dlr8/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public void flatten(TreeNode root) {
          while (root != null){
              if(root.left != null){
                  TreeNode temp = root.left;
                  TreeNode cur = temp;
                  while (cur.right != null){
                      cur = cur.right;
                  }
                  cur.right = root.right;
                  root.left = null;
                  root.right = temp;
              }
              root = root.right;
          }
      }
  }
  ```



## [105. 从前序与中序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

难倒是不难，就是繁琐，得慢慢模拟那个传入的值

类似的还有：[106. 从中序与后序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/) 

- 思路：借助哈希表和全局数组，前者存储中序遍历数组<值，索引>，后者赋值为前序遍历数组。通过哈希表得知当前节点值在中序遍历数组中的索引。**这题最关键的是如何确定在左右子树中传递的值**

- 详细题解：[105.从前序与中序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solutions/2865582/105-cong-qian-xu-yu-zhong-xu-bian-li-xu-1mv3h/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      HashMap<Integer, Integer> map = new HashMap<>();
      int[] p ;
      public TreeNode buildTree(int[] preorder, int[] inorder) {
          p = preorder;
          for (int i = 0; i < inorder.length; i++) {
              map.put(inorder[i], i);
          }
          return build(0, preorder.length - 1, 0, inorder.length - 1);
      }
  
      private TreeNode build(int pb, int pe, int ib, int ie) {
          if(pb < 0 || ib < 0 || pb > pe || ib > pe)
              return null;
          int index = map.get(p[pb]);
          TreeNode root = new TreeNode(p[pb]);
          root.left = build(pb + 1, pb + index - ib, ib, index - 1);
          root.right = build(pb + index + 1 - ib, pe, index + 1, ie);
          return root;
      }
  }
  ```



后面几题都有点邪乎了。。

## [437. 路径总和 III](https://leetcode.cn/problems/path-sum-iii/) （小难

哈希表+前缀和，类似[560. 和为 K 的子数组](https://leetcode.cn/problems/subarray-sum-equals-k/) 

- 思路：哈希表保存<当前路径从根节点到当前节点的节点值之和，出现次数>，如果想在当前路径找到一段路径之和为target，那么哈希表中必须有这样的元素，即当前节点值之和 - 该元素的key = target。还有一些细节参考详细题解，这题核心思路就是这样，但还有一些繁琐的小细节，比如哈希表key的类型，状态恢复（因为题目要求只能是从父节点到子节点，也即是不能跨越父节点有两段子树参与）

- 详细题解：[437.路径总和 III](https://leetcode.cn/problems/path-sum-iii/solutions/2897122/437-lu-jing-zong-he-iii-by-fervent-wilbu-ih9a/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      HashMap<Long, Integer> map = new HashMap<>();
      int targrt;
      public int pathSum(TreeNode root, int targetSum) {
          targrt =targetSum;
          map.put(0L, 1);
          return dfs(root, 0L);
      }
  
      private int dfs(TreeNode root, long curSum) {
          if(root == null)
              return 0;
          curSum += root.val;
          int ans = 0;
          ans += map.getOrDefault(curSum - targrt, 0);
          map.put(curSum, map.getOrDefault(curSum, 0) + 1);
          int left = dfs(root.left, curSum);
          int right = dfs(root.right, curSum);
          ans += left + right;
          map.put(curSum, map.get(curSum) - 1);
          return ans;
      }
  }
  ```



## [236. 二叉树的最近公共祖先](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/) （思路有点懵懂

- 思路：这题可直接参考[Krahets ](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/solutions/240096/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/?envType=study-plan-v2&envId=top-100-liked)的题解，他这个思路很清晰，代码我是参考的[灵茶山艾府的](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/solutions/2023872/fen-lei-tao-lun-luan-ru-ma-yi-ge-shi-pin-2r95/?envType=study-plan-v2&envId=top-100-liked)，这个代码比较好写

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
          if(root == null || p == root || q == root)
              return root;
          TreeNode l = lowestCommonAncestor(root.left, p, q);
          TreeNode r = lowestCommonAncestor(root.right, p, q);
          if(l != null && r != null)
              return root;
          return l == null ? r : l;
      }
  }
  ```

  

## [124. 二叉树中的最大路径和](https://leetcode.cn/problems/binary-tree-maximum-path-sum/)

- 思路：递归函数返回当前节点及其左右子树较大值之和与0的比较，而ans要取当前节点和其左右子树之和与and的较大值

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      int ans = Integer.MIN_VALUE;
      public int maxPathSum(TreeNode root) {
          dfs(root);
          return ans;
      }
  
      private int dfs(TreeNode root) {
          if(root == null)
              return 0;
          int left = dfs(root.left);
          int right = dfs(root.right);
          ans = Math.max(ans, left + right + root.val);
          return Math.max(0, Math.max(left, right) + root.val);
      }
  }
  ```

  

------



# 九、图论

四个题目分别涉及了DFS、BFS、拓扑排序

## [200. 岛屿数量](https://leetcode.cn/problems/number-of-islands/)

- 思路：遍历每个单元格，当遇到陆地（`'1'`）时，将其所有相连的陆地标记为已访问，从而统计为一个岛屿。

  1. 遍历整个网格的每一个格子。
  2. 当遇到一个'1'时，岛屿数量加一。
  3. 然后使用DFS或BFS将这个岛屿的所有相连的'1'都标记为'0'，防止重复计数。
  4. 继续遍历剩下的格子，重复上述过程。

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public int numIslands(char[][] grid) {
          int m = grid.length;
          int n = grid[0].length;
          int ans = 0;
          for (int i = 0; i < m; i++) {
              for (int j = 0; j < n; j++) {
                  if(grid[i][j] == '1'){
                      dfs(i, j, grid);
                      ans++;
                  }
              }
          }
          return ans;
      }
  
      private void dfs(int i, int j, char[][] grid) {
          if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1')
              return;
          grid[i][j] = '0';
          dfs(i - 1, j, grid);
          dfs(i + 1, j, grid);
          dfs(i, j - 1,grid);
          dfs(i, j + 1,grid);
      }
  }
  ```



## [994. 腐烂的橘子](https://leetcode.cn/problems/rotting-oranges/)

- 思路：BFS（队列）。逐层处理队列中的腐烂橘子，将周围的新鲜橘子感染，并加入队列。每一层对应一分钟的扩散时间。 

- 详细题解：[994.腐烂的橘子](https://leetcode.cn/problems/rotting-oranges/solutions/3080810/994-fu-lan-de-ju-zi-by-fervent-wilburtzg-b57g/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int orangesRotting(int[][] grid) {
          int m = grid.length;
          int n = grid[0].length;
          Queue<int[]> queue = new LinkedList<>();
          int fresh = 0;
          for (int i = 0; i < m; i++) {
              for (int j = 0; j < n; j++) {
                  if(grid[i][j] == 2) //说明是腐烂的橘子，将坐标加入队列
                      queue.offer(new int[]{i, j});
                  else if (grid[i][j] == 1) {
                      fresh++;
                  }
              }
          }
          if(fresh == 0)  //说明没有新鲜橘子
              return 0;
  
          int time = 0;
          int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
          while (!queue.isEmpty()){
              int size = queue.size();
              boolean flag = false;   //用于标记当前遍历是否感染了新鲜橘子
              for (int i = 0; i < size; i++) {
                  int[] cur = queue.poll();
                  for (int[] dire : direction) {
                      int x = cur[0] + dire[0];
                      int y = cur[1] + dire[1];
                      //如果没越界且是新鲜橘子，就给腐烂
                      if(x >=0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
                          grid[x][y] = 2;
                          queue.offer(new int[]{x, y});
                          fresh--;
                          flag = true;
                      }
                  }
              }
              if(flag)
                  time++;
          }
          return fresh == 0 ? time : -1;
      }
  }
  ```



## [207. 课程表](https://leetcode.cn/problems/course-schedule/)

- 思路：拓扑排序（DFS检测环）

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public boolean canFinish(int numCourses, int[][] prerequisites) {
          List<List<Integer>> graph = new ArrayList<>();
          for (int i = 0; i < numCourses; i++) {  //邻接表构建图
              graph.add(new ArrayList<>());
          }
  
          for (int[] pre : prerequisites) {       //(a,b)要想学习a得先学习b，因此构建b——>a的有向边
              graph.get(pre[1]).add(pre[0]);
          }
  
          int[] visited = new int[numCourses];    //状态数组，0=未访问，1=正在访问，2=已访问
  
          for (int i = 0; i < numCourses; i++) {  //检测每个节点是否存在环
              if(hasCycle(graph, visited, i))
                  return false;
          }
  
          return true;
      }
  
      private boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
          if(visited[course] == 1)    // 如果课程已经访问过且在当前路径上，说明存在环
              return true;
  
          if(visited[course] == 2)     // 如果课程已经访问过且不在当前路径上，说明没有环
              return false;
  
          visited[course] = 1;
          for (int nextCourse : graph.get(course)) {  // 递归访问当前课程的所有依赖课程
              if(hasCycle(graph, visited, nextCourse))
                  return true;
          }
          visited[course] = 2;
          return false;
  
      }
  }
  ```



## [208. 实现 Trie (前缀树)](https://leetcode.cn/problems/implement-trie-prefix-tree/)

这里要构建一个图，它的节点代表的是索引号，而边上面存储的是一个一个的字符

- 思路：自定义图节点的属性，然后模拟图的创建和查找过程

- 详细题解：参考代码注释，写的很详细

- 参考代码：

  ```java
  public class Trie {
      /*
      定义节点类，相当于每个节点有26个子节点，但只有真正有值的才有节点，其余都为null。
      另外还有一个标志位用来标记当前节点是否为最后一个节点
       */
      public class  TrieNode{
          TrieNode[] children;
          boolean isEnd;
          TrieNode(){
              children = new TrieNode[26];
              isEnd = false;
          }
      }
      TrieNode root;
      public Trie() {
          root = new TrieNode();
      }
  
      public void insert(String word) {
          TrieNode node = root;
          for (char c : word.toCharArray()) {
              int index = c - 'a';    //得到有值的节点的索引
              if(node.children[index] == null)
                  node.children[index] = new TrieNode();
              node = node.children[index];    //相当于节点后移
          }
          node.isEnd = true;  //此时该字符串遍历完了，node一定是最后一个节点
      }
  
      /*
      根据这个单词的路径一直走，如果中间哪个为null了说明字符未出现直接返回false
      否则安全遍历完之后还要确认当前字符是否为最后一个字符，如果不是说明它只是一个前缀，还要返回false
       */
      public boolean search(String word) {    //
          TrieNode node = root;
          for (char c : word.toCharArray()) {
              int index = c - 'a';
              if(node.children[index] == null)
                  return false;
              node = node.children[index];
          }
          return node.isEnd;
      }
  
      /*
      思路同上，不同的是最后不需要关注它是否为最后一个字符了，因为这里只是找前缀
       */
      public boolean startsWith(String prefix) {
          TrieNode node = root;
          for (char c : prefix.toCharArray()) {
              int index = c - 'a';
              if(node.children[index] == null)
                  return false;
              node = node.children[index];
          }
          return true;
      }
  }
  ```

  

------



# 十、回溯

回溯，主要就是套模板。我感觉最关键的就是两个要素：

- 回溯传递的参数
- 回溯退出的条件

## [46. 全排列](https://leetcode.cn/problems/permutations/)

- 思路：可以用一个`list`来存储每组的元素。退出条件就是`list.size() == nums.length`，同时要能加入list的前提是list不含当前元素

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      List<List<Integer>> ans = new ArrayList<>();
      List<Integer> path = new ArrayList<>();
      public List<List<Integer>> permute(int[] nums) {
          backTracking(nums);
          return ans;
      }
  
      private void backTracking(int[] nums) {
          if(path.size() == nums.length)
              ans.add(new ArrayList<>(path));
          for (int num : nums) {
              if(!path.contains(num)){
                  path.add(num);
                  backTracking(nums);
                  path.remove(path.size() - 1);
              }
          }
      }
  }
  ```



## [78. 子集](https://leetcode.cn/problems/subsets/)

- 思路：这题有点特殊的是，每个分组都要添加到结果，所以不用写退出条件了

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      List<List<Integer>> ans = new ArrayList<>();
      List<Integer> path = new ArrayList<>();
      public List<List<Integer>> subsets(int[] nums) {
          backTracking(nums, 0);
          return ans;
      }
  
      private void backTracking(int[] nums, int begin) {
          ans.add(new ArrayList<>(path));
          for (int i = begin; i < nums.length; i++) {
              path.add(nums[i]);
              backTracking(nums, i + 1);
              path.remove(path.size() - 1);
          }
      }
  }
  ```



## [17. 电话号码的字母组合](https://leetcode.cn/problems/letter-combinations-of-a-phone-number/)

- 思路：借助哈希表存储电话号码和字符串的映射关系，传入字符串即开始索引begin，退出条件为`begin == digits.length()`

- 详细题解：[17.电话号码的字母组合](https://leetcode.cn/problems/letter-combinations-of-a-phone-number/solutions/2870989/17-dian-hua-hao-ma-de-zi-mu-zu-he-by-fer-885z/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      List<String> ans = new ArrayList<>();
      HashMap<Character, String> map = new HashMap<>();
      StringBuilder sb = new StringBuilder();
      public List<String> letterCombinations(String digits) {
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
  
      private void backTracking(String digits, int begin) {
          if(begin == digits.length()){
              ans.add(sb.toString());
              return;
          }
              
  
          char c = digits.charAt(begin);
          String curS = map.get(c);
          for (int i = 0; i < curS.length(); i++) {
              sb.append(curS.charAt(i));
              backTracking(digits, begin + 1);
              sb.deleteCharAt(sb.length() - 1);
          }
      }
  }
  ```

## [39. 组合总和](https://leetcode.cn/problems/combination-sum/)

- 思路：传入数组开始索引`begin`，当前和`cur`，目标值，数组。结束条件是`begin == candidates.length || cur == target`，也有可能提前退出，那就是`cur > target`

- 详细题解：[39.组合总和](https://leetcode.cn/problems/combination-sum/solutions/2871089/39-zu-he-zong-he-by-fervent-wilburtzg-bufr/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      List<List<Integer>> ans = new ArrayList<>();
      List<Integer> path = new ArrayList<>();
      public List<List<Integer>> combinationSum(int[] candidates, int target) {
          backTracking(0, 0,  target, candidates);
          return ans;
      }
  
      private void backTracking(int begin, int cur, int target, int[] candidates) {
          if(cur == target){
              ans.add(new ArrayList<>(path));
              return;
          }
          if(cur > target || begin == candidates.length)
              return;
          for (int i = begin; i < candidates.length; i++) {
              cur += candidates[i];
              path.add(candidates[i]);
              backTracking(i, cur, target, candidates);
              path.remove(path.size() - 1);
              cur -= candidates[i];
          }
      }
  }
  ```



## [22. 括号生成](https://leetcode.cn/problems/generate-parentheses/)

- 思路：传入当前左括号剩余left和右括号剩余right以及当前字符串cur。退出条件是`left == 0 && right == 0`。还要一个提前退出条件，就是`left > right`，此时无法组成合法的括号。这里回溯的写法形式也不太一样。

- 详细题解：[22.括号生成](https://leetcode.cn/problems/generate-parentheses/solutions/2898044/22-gua-hao-sheng-cheng-by-fervent-wilbur-3sdf/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      List<String> ans = new ArrayList<>();
      public List<String> generateParenthesis(int n) {
          backTracking(n, n, "");
          return ans;
      }
  
      private void backTracking(int left, int right, String cur) {
          if(left == 0 && right == 0){
              ans.add(cur);
              return;
          }
          if(left > right)
              return;
          if(left > 0){
              backTracking(left-1, right, cur + "(");
          }
          if(right > 0)
              backTracking(left, right-1, cur + ")");
      }
  }
  ```



## [79. 单词搜索](https://leetcode.cn/problems/word-search/)

- 思路：DFS，从矩阵每个元素开始匹配，匹配了修改当前字符为`.``,表示已经用过，搜索完之后回溯为之前的字符

- 详细题解：[79.单词搜索](https://leetcode.cn/problems/word-search/solutions/2898134/79-dan-ci-sou-suo-by-fervent-wilburtzg-re1b/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  public boolean exist(char[][] board, String word) {
          int m = board.length;
          int n = board[0].length;
          int k = 0;
          for (int i = 0; i < m; i++) {
              for (int j = 0; j < n; j++) {
                  if(backTracking(i, j, board, word, k))
                      return true;
              }
          }
          return false;
      }
  
      private boolean backTracking(int i, int j, char[][] board, String word, int k) {
          if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || word.charAt(k) != board[i][j])
              return false;
          if(k == word.length() - 1)  //这一步要注意，因为上面没有对k的越界判断，所以这里就要有一个判真的退出
              return true;
          char tem = board[i][j];
          board[i][j] = '.';
          boolean ans = backTracking(i + 1, j, board, word, k + 1) ||
                  backTracking(i - 1, j, board, word, k + 1) ||
                  backTracking(i, j  + 1, board, word, k + 1) ||
                  backTracking(i, j - 1, board, word, k + 1);
          board[i][j] = tem;
          return ans;
      }
  ```





## [131. 分割回文串](https://leetcode.cn/problems/palindrome-partitioning/)

- 思路：每个字符在回溯循环中往后匹配，需要写一个判断是否为回文串的函数，如果是就添加进中间结果集。回溯传入字符串和开始索引，退出条件为开始索引等于字符串长度

- 详细题解：[131. 分割回文串](https://leetcode.cn/problems/palindrome-partitioning/solutions/2871212/131-fen-ge-hui-wen-chuan-by-fervent-wilb-uz96/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      List<List<String>> ans = new ArrayList<>();
      List<String> path = new ArrayList<>();
      public List<List<String>> partition(String s) {
          backTracking(s, 0);
          return ans;
      }
  
      private void backTracking(String s, int begin) {
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
  
      private boolean isH(String s, int l, int r) {
          while (l <= r){
              if(s.charAt(l) != s.charAt(r))
                  return false;
              l++;
              r--;
          }
          return true;
      }
  }
  ```



## [51. N 皇后](https://leetcode.cn/problems/n-queens/)

- 思路：回溯函数传入棋盘数组和当前行索引，退出条件为当前行索引等于棋盘宽，回溯里面的循环是列循环。同上题一样，需要写一个函数来`判断是否满足条件`，判断当前字符是否可以作为Q。具体来说，就是判断当前行，列，主对角，副对角是否有其它Q。

- 详细题解：[51. N 皇后](https://leetcode.cn/problems/n-queens/solutions/2898201/51-n-huang-hou-by-fervent-wilburtzg-c2ko/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      List<List<String>> ans = new ArrayList<>();
      List<String> path = new ArrayList<>();
      public List<List<String>> solveNQueens(int n) {
          char[][] chars = new char[n][n];
          for (int i = 0; i < n; i++) {
              for (int j = 0; j < n; j++) {
                  chars[i][j] = '.';
              }
          }
          backTracking(0, chars);
          return ans;
      }
  
      private void backTracking(int row, char[][] chars) {
          if(row == chars.length){
              ans.add(chars2List(chars));
              return;
          }
          for (int col = 0; col < chars[0].length; col++) {
              if(isT(row, col, chars)){
                  chars[row][col] = 'Q';
                  backTracking(row + 1, chars);
                  chars[row][col] = '.';
              }
          }
      }
  
      private List<String> chars2List(char[][] chars) {
          List<String> ans = new ArrayList<>();
          for (int i = 0; i < chars.length; i++) {
              ans.add(new String(chars[i]));
          }
          return ans;
      }
  
      private boolean isT(int row, int col, char[][] chars) {
          int m = chars.length;
          int n = chars[0].length;
          for (int i = 0; i < m; i++) {
              if(chars[i][col] == 'Q')
                  return false;
          }
          for (int i = 0; i < n; i++) {
              if(chars[row][i] == 'Q')
                  return false;
          }
          for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
              if(chars[i][j] == 'Q')
                  return false;
          for (int i = row, j = col; i >= 0 && j < n; i--, j++)
              if(chars[i][j] == 'Q')
                  return false;
          return true;
      }
  }
  ```

------



# 十一、二分查找

## [35. 搜索插入位置](https://leetcode.cn/problems/search-insert-position/)

- 思路：二分查找找存在时的元素这没什么好说的，当元素不存在时，为什么返回low 就可以了呢，观察while退出条件，退出时，low = high + 1

- 详细题解：[35. 搜索插入位置](https://leetcode.cn/problems/search-insert-position/solutions/2682231/35-sou-suo-cha-ru-wei-zhi-ti-jie-by-ferv-dcbf/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int searchInsert(int[] nums, int target) {
          int low = 0, high = nums.length- 1;
          while (low <= high){
              int mid = low + (high - low) / 2;
              if(nums[mid] == target)
                  return mid;
              else if (nums[mid] > target) {
                  high = mid - 1;
              }else
                  low = mid + 1;
          }
          return low;
      }
  }
  ```



## [74. 搜索二维矩阵](https://leetcode.cn/problems/search-a-2d-matrix/)

- 思路：将矩阵按行展开拼成一个一维数组，可以发现就是对这个数组来进行二分查找，每次得到的mid和行列有一个对应关系，`row = mid / n, col = mid % n`

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public boolean searchMatrix(int[][] matrix, int target) {
          int m = matrix.length;
          int n = matrix[0].length;
          int low = 0, high = m * n - 1;
          while (low <= high){
              int mid = low + (high - low) / 2;
              int row = mid / n;
              int col = mid % n;
              if(matrix[row][col] == target)
                  return true;
              else if (matrix[row][col] > target) {
                  high = mid - 1;
              }else 
                  low = mid + 1;
          }
          return false;
      }
  }
  ```



## [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)

- 思路：二分查找，如果找不到，那就正常套路，如果找到了，直接从当前位置向左右扩散，直到扩散的元素不等于target

- 详细题解：[34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/solutions/2850183/34-zai-pai-xu-shu-zu-zhong-cha-zhao-yuan-edy0/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int[] searchRange(int[] nums, int target) {
          int low = 0, high = nums.length - 1;
          while (low <= high){
              int mid = low + (high - low) / 2;
              if(nums[mid] > target)
                  high = mid - 1;
              else if(nums[mid] < target)
                  low = mid + 1;
              else {
                  int l = mid, r = mid;
                  while (l >= 0 && nums[l] == target) l--;
                  while (r < nums.length && nums[r] == target)    r++;
                  return new int[]{l + 1, r - 1};
              }
          }
          return new int[] {-1, -1};
      }
  }
  ```



## [33. 搜索旋转排序数组](https://leetcode.cn/problems/search-in-rotated-sorted-array/)

- 思路：这种数组是局部有序，全局无序，比如前半部分是有序的，后半部分也是有序的。我们做二分查找时，如果当前mid元素不等于target，那就判断当前元素是处于左边有序区间还是右边有序区间，然后再分别做区间缩减

- 详细题解：[33. 搜索旋转排序数组](https://leetcode.cn/problems/search-in-rotated-sorted-array/solutions/2883575/33-sou-suo-xuan-zhuan-pai-xu-shu-zu-by-f-ls5p/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int search(int[] nums, int target) {
          int low = 0, high = nums.length - 1;
          while (low <= high){
              int mid = low + (high - low) / 2;
              if(nums[mid] == target)
                  return mid;
              else if (nums[mid] < nums[high]) {
                  if(nums[mid] < target && target <= nums[high])
                      low = mid + 1;
                  else 
                      high = mid - 1;
              }else {
                  if(target >= nums[low] && target < nums[mid])
                      high = mid - 1;
                  else 
                      low = mid + 1;
              }
          }
          return -1;
      }
  }
  ```



## [153. 寻找旋转排序数组中的最小值](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/)

- 思路：同上题一样，这种旋转数组要分区间。如果是在左边区间，就将最小值和left比较，如果是在最右边，就将最小值和mid比较

- 详细题解：[153. 寻找旋转排序数组中的最小值](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/solutions/2883605/153-xun-zhao-xuan-zhuan-pai-xu-shu-zu-zh-o18x/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int findMin(int[] nums) {
          int low = 0, high = nums.length - 1;
          int ans = Integer.MAX_VALUE;
          while (low <= high){
              int mid = low + (high - low) / 2;
              int temp = nums[mid];
              if(temp < nums[high]){
                  ans = Math.min(ans, temp);
                  high = mid - 1;
              }else {
                  ans = Math.min(ans, nums[low]);
                  low = mid + 1;
              }
          }
          return ans;
      }
  }
  ```

## [4. 寻找两个正序数组的中位数](https://leetcode.cn/problems/median-of-two-sorted-arrays/) （难）

- 思路：

- 详细题解：

- 参考代码：

  ```java
  
  ```

------



# 十二、栈

## [20. 有效的括号](https://leetcode.cn/problems/valid-parentheses/)

- 思路：借助栈，如果是左括号直接入栈，右括号就根据栈顶元素来确定对右括号的策略

- 详细题解：[20. 有效的括号](https://leetcode.cn/problems/valid-parentheses/solutions/2860939/20-you-xiao-de-gua-hao-by-fervent-wilbur-2308/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public boolean isValid(String s) {
          ArrayDeque<Character> stack = new ArrayDeque<>();
          for (int i = 0; i < s.length(); i++) {
              char c = s.charAt(i);
              if(c == '[' || c == '(' || c == '{')
                  stack.push(c);
              else {
                  if(stack.isEmpty())
                      return false;
                  else if (c == ']' && stack.peek() == '[') {
                      stack.pop();
                  }else if (c == ')' && stack.peek() == '(') {
                      stack.pop();
                  }else if (c == '}' && stack.peek() == '{') {
                      stack.pop();
                  }else 
                      return false;
              }
          }
          if (!stack.isEmpty())   return false;
          return true;
      }
  }
  ```



## [155. 最小栈](https://leetcode.cn/problems/min-stack/)

- 思路：另外创建一个只存储栈中最小元素的辅助栈，来模拟最小栈的操作

- 详细题解：[155. 最小栈](https://leetcode.cn/problems/min-stack/solutions/2892186/155-zui-xiao-zhan-by-fervent-wilburtzg-lsmz/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class MinStack {
  
      ArrayDeque<Integer> nums = new ArrayDeque<>();
      ArrayDeque<Integer> minStack = new ArrayDeque<>();
      public MinStack() {
          nums = new ArrayDeque<>();
          minStack = new ArrayDeque<>();
          minStack.push(Integer.MAX_VALUE);
      }
  
      public void push(int val) {
          nums.push(val);
          minStack.push(Math.min(minStack.peek(), val));
      }
  
      public void pop() {
          nums.pop();
          minStack.pop();
      }
  
      public int top() {
          return nums.peek();
      }
  
      public int getMin() {
          return minStack.peek();
      }
  }
  ```



## [394. 字符串解码](https://leetcode.cn/problems/decode-string/)

- 思路：双栈模拟，一个栈仅存数字，另一个栈仅存字符串。遍历s的字符，根据字符为数字、左括号、右括号、字符四种情况分类操作。详细可见代码及详细题解

- 详细题解：[394. 字符串解码](https://leetcode.cn/problems/decode-string/solutions/2892504/394-zi-fu-chuan-jie-ma-by-fervent-wilbur-37k7/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public String decodeString(String s) {
          ArrayDeque<Integer> numStack = new ArrayDeque<>();
          ArrayDeque<String> strStack = new ArrayDeque<>();
  
          int num = 0;
          String curStr = "";
          for (int i = 0; i < s.length(); i++) {
              char c = s.charAt(i);
              if(Character.isDigit(c)){
                  num = num * 10 + c - '0';
              } else if (c == '[') {
                  numStack.push(num);
                  strStack.push(curStr);
                  num = 0;
                  curStr = "";
              } else if (c == ']') {
                  int loop = numStack.pop();
                  StringBuilder sb = new StringBuilder(strStack.pop());
                  for (int j = 0; j < loop; j++) {
                      sb.append(curStr);
                  }
                  curStr = sb.toString();
              }else
                  curStr += c;
          }
          return curStr;
      }
  }
  ```



## [739. 每日温度](https://leetcode.cn/problems/daily-temperatures/)

单调栈，这题以及[84. 柱状图中最大的矩形](https://leetcode.cn/problems/largest-rectangle-in-histogram/) 都是**单调栈**，略微难想

- 思路：这里也别在意这个所谓的单调栈到底是怎么单调的了，就这题而言，我们可以这样考虑，每次遍历数组元素的时候就把当前下标加入栈，但是在加入之前呢，得先看看栈最顶的下标所指元素是否找到了下一个更大的元素，如果找到了，就弹出然后设置。同时这个操作还得循环进行，比如当前遍历的元素比前几个元素都大，那就是说前几个元素的下一个更大元素都找到了。详细可参照代码理解

- 详细题解：[739.每日温度](https://leetcode.cn/problems/daily-temperatures/solutions/2894899/739-mei-ri-wen-du-by-fervent-wilburtzg-gt8h/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int[] dailyTemperatures(int[] temperatures) {
          int[] ans = new int[temperatures.length];
          ArrayDeque<Integer> stack = new ArrayDeque<>();
  
          for (int i = 0; i < temperatures.length; i++) {
              while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                  int p = stack.pop();    //弹出的索引该处的元素下一个更大元素找到了！
                  ans[p] = i - p;	
              }
              stack.push(i);  
          }
          return ans;
      }
  }
  ```



## [84. 柱状图中最大的矩形](https://leetcode.cn/problems/largest-rectangle-in-histogram/)

- 思路：

  - **单调栈**：维护一个单调递增的栈，用于快速找到每个柱子左边和右边第一个比它矮的柱子。
  - 遍历每个柱子，当遇到比栈顶柱子矮的柱子时，弹出栈顶并计算其面积，更新最大面积。 

- 详细题解：

- 参考代码：

  ```java
  public int largestRectangleArea(int[] heights) {
          int ans = Integer.MIN_VALUE;
          ArrayDeque<Integer> stack = new ArrayDeque<>();
          stack.push(-1);
  
          for (int i = 0; i < heights.length; i++) {
              while (stack.peek() != -1 && heights[i] <= heights[stack.peek()]){
                  int high = heights[stack.pop()];
                  int width = i - stack.peek() - 1;
                  ans = Math.max(ans, high * width);
              }
              stack.push(i);
          }
  
          while (stack.peek() != -1){
              int high = heights[stack.pop()];
              int width = heights.length - stack.peek() - 1;
              ans = Math.max(ans, high * width);
          }
          return ans;
      }
  ```

------



# 十三、堆

## [215. 数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/)

- 思路：快排

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      private static final Random rand = new Random();
      public int findKthLargest(int[] nums, int k) {
          quickSort(nums, 0, nums.length - 1);
          return nums[nums.length - k];
      }
  
      private void quickSort(int[] nums, int l, int r) {
          if(l < r){
              int pivotIndex = partition(nums, l, r);
              quickSort(nums, l, pivotIndex - 1);
              quickSort(nums, pivotIndex + 1, r);
          }
      }
  
      private int partition(int[] nums, int l, int r) {
          int random = rand.nextInt(r - l  + 1) + l;
          swap(nums, random, l);
          int i = l, j = r;
          int pivot = nums[l];
          while (i < j){
              while (i < j && nums[j] >= pivot)   j--;
              while (i < j && nums[i] <= pivot)   i++;
              if(i < j)
              swap(nums, i, j);
          }
          swap(nums, l, i);
          return i;
      }
  
      private void swap(int[] nums, int l, int r) {
          int temp = nums[l];
          nums[l] = nums[r];
          nums[r] = temp;
      }
  }
  ```



## [347. 前 K 个高频元素](https://leetcode.cn/problems/top-k-frequent-elements/)

- 思路：

  - **统计频率**：使用`HashMap`遍历数组，记录每个元素的出现次数。
  - **最小堆维护**：优先队列按元素频率升序排列，当堆的大小超过k时，移除堆顶的最小频率元素，确保堆中保留当前最大的k个频率元素。
  - **结果提取**：将堆中的元素依次取出，转换为数组返回。由于题目允许任意顺序，无需额外排序。

- 详细题解：

- 参考代码：

  ```java
  	class Solution {
      public int[] topKFrequent(int[] nums, int k) {
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
  }
  ```



## [295. 数据流的中位数](https://leetcode.cn/problems/find-median-from-data-stream/)

------



# 十四、贪心算法

## [121. 买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/)

- 思路：记录今天及之前的最小值，然后更新可获得的最大利润

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public int maxProfit(int[] prices) {
          int pre = Integer.MAX_VALUE;
          int ans = Integer.MIN_VALUE;
          for (int price : prices) {
              pre = Math.min(pre, price);
              ans = Math.max(ans, price - pre);
          }
          return ans;
      }
  }
  ```



## [55. 跳跃游戏](https://leetcode.cn/problems/jump-game/)

- 思路：对当前最大可跳跃步数做循环，同时维护更新最大步数，如果这个值超过了数组长度 - 1，说明可以到达最后一个元素

- 详细题解：[55. 跳跃游戏](https://leetcode.cn/problems/jump-game/solutions/2875394/55-tiao-yue-you-xi-by-fervent-wilburtzg-16fk/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public boolean canJump(int[] nums) {
          int maxStep = 0;
          for (int i = 0; i <= maxStep; i++) {
              maxStep = Math.max(maxStep, i + nums[i]);
              if(maxStep >= nums.length - 1)
                  return true;
          }
          return false;
      }
  }
  ```



## [45. 跳跃游戏 II](https://leetcode.cn/problems/jump-game-ii/)

- 思路：遍历数组元素，不断维护更新可到达最远位置，如果到了最边界就更新边界同时ans++。这题要注意的是不必到达最后一个元素才判断，到达倒数第二个元素就可以了（详细看官方题解下对于这点说明

- 详细题解：[45. 跳跃游戏 II](https://leetcode.cn/problems/jump-game-ii/solutions/2902601/45-tiao-yue-you-xi-ii-by-fervent-wilburt-3v3o/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int jump(int[] nums) {
          int ans = 0;
          int maxPosition = 0;
          int end = 0;
          for (int i = 0; i < nums.length - 1; i++) {
              maxPosition = Math.max(maxPosition, i + nums[i]);
              if(i == end){
                  end = maxPosition;
                  ans++;
              }
          }
          return ans;
      }
  }
  ```



## [763. 划分字母区间](https://leetcode.cn/problems/partition-labels/)

- 思路：先记录每个字符的最远索引是在哪。然后定义begin和end来处理每个区间的长度。每次遍历不断更新end。如果到了end，则将当前区间长度添加进入ans，然后更新begin为end的下一个位置

- 详细题解：[763. 划分字母区间](https://leetcode.cn/problems/partition-labels/solutions/2902655/763-hua-fen-zi-mu-qu-jian-by-fervent-wil-xdj9/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public List<Integer> partitionLabels(String s) {
          List<Integer> ans = new ArrayList<>();
          int[] maxP = new int[26];
          for (int i = 0; i < s.length(); i++) {
              maxP[s.charAt(i) - 'a'] = i;
          }
  
          int end = 0, begin = 0;
          for (int i = 0; i < s.length(); i++) {
              end = Math.max(end, maxP[s.charAt(i) - 'a']);
              if(i == end){
                  ans.add(end - begin + 1);
                  begin = end + 1;
              }
          }
          return ans;
      }
  }
  ```

  

# 十五、动态规划

## [70. 爬楼梯](https://leetcode.cn/problems/climbing-stairs/)

- 思路：定义dp数组i为到达当前位置的方案，当前状态可由dp[i-1]或者dp [i-2]转移过来

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public int climbStairs(int n) {
          int[] dp = new int[n + 1];
          if(n == 1)
              return 1;
          if(n == 2)
              return 2;
          dp[1] = 1;
          dp[2] = 2;
          for (int i = 3; i <= n; i++) {
              dp[i] = dp[i - 1] + dp[i - 2];
          }
          return dp[n];
      }
  }
  ```



## [118. 杨辉三角](https://leetcode.cn/problems/pascals-triangle/)

- 思路：当前状态可由上一行的两个元素转移过来

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public List<List<Integer>> generate(int numRows) {
          List<List<Integer>> ans = new ArrayList<>();
          ans.add(List.of(1));
          if(numRows == 1)
              return ans;
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
  }
  ```



## [198. 打家劫舍](https://leetcode.cn/problems/house-robber/)

- 思路：dp[i]为到当前i所能获得的最高金额，这个金额取决于前面一家(也就是i-1是否打劫了)，`dp[i]=max(dp[i-1],dp [i-2]+nums [i])`

- 详细题解：[198. 打家劫舍](https://leetcode.cn/problems/house-robber/solutions/2876726/198-da-jia-jie-she-by-fervent-wilburtzg-s9x1/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int rob(int[] nums) {
          int n = nums.length;
          int[] dp = new int[n];
          if(n == 1)
              return nums[0];
          if(n == 2)
              return Math.max(nums[0], nums[1]);
          dp[0] = nums[0];
          dp[1] = Math.max(nums[0], nums[1]);
          for (int i = 2; i < n; i++) {
              dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
          }
  
          return dp[n - 1];
      }
  }
  ```



## [279. 完全平方数](https://leetcode.cn/problems/perfect-squares/)

- 思路：

  动态规划的思路通常是这样的：我们定义一个数组dp，其中dp[i]表示和为i的完全平方数的最少数量。然后，我需要找出状态转移方程。

  比如，对于每个i，我需要遍历所有可能的平方数j*j，其中j*j <=i。然后，dp[i]可能等于dp[i - j*j] +1，因为如果i-j*j可以由dp[i-j*j]个平方数组成，那么加上当前的j*j（也就是一个平方数），总共有dp[i-j*j]+1个。这时候我需要取所有可能的j中的最小值。

  这样，状态转移方程应该是dp[i] = min(dp[i], dp[i - j*j] +1)。初始条件是dp[0]=0，因为和为0不需要任何平方数。然后从1到n依次计算每个dp[i]。

  那具体怎么实现呢？比如n=12的时候，dp数组的大小应该是n+1，也就是13个元素。初始化的时候，所有元素设为最大值，比如n+1，因为最大的情况是全部由1组成，也就是n个1，所以初始值可以设为n+1，这样后续比较时就能找到更小的值。

  举个例子，计算dp[12]的时候，我们需要遍历所有可能的j，其中j的平方不超过12。j可以是1、2、3，因为3的平方是9，4的平方是16就超过了。对于每个j，我们计算dp[12 - j*j] +1的值，并取最小值。

  比如当j=3时，12-9=3，所以要看dp[3]的值加上1。那dp[3]是多少呢？dp[3]的可能分解是1+1+1，也就是3个1，或者1+2的平方？但2的平方是4，比3大，所以只能是1的平方相加。所以dp[3]应该是3。那么这时候，dp[12]可能等于3+1=4？或者可能有更优的情况？

  或者，比如当j=2的时候，j²是4。12-4=8。那么要看dp[8]的值加上1。假设dp[8]的最优解是2（比如4+4），那么加上当前的4，就是3次。所以这时候，dp[12]的最小值就是3。这应该就是示例中的正确情况。

  所以动态规划的步骤应该是：

  1. 初始化dp数组，dp[0] =0，其他初始化为较大的值，比如n。
  2. 遍历每个i从1到n。
  3. 对于每个i，遍历所有j，其中j的平方<=i。
  4. 更新dp[i]为min(dp[i], dp[i -j*j]+1)。
  5. 最后返回dp[n]。

- 详细题解：

- 参考代码：

  ```java
  public int numSquares(int n) {
          int[] dp = new int[n + 1];
          Arrays.fill(dp, n + 1);
          dp[0] = 0;
          for (int i = 1; i * i <= n; i++) {  //遍历所有小于等于 n 的完全平方数 i * i。
              for (int j = i * i; j <= n; j++)    //根据当前的完全平方数，更新 dp[j]，表示构成 j 的最小完全平方数的个数。
                  dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
          }
          return dp[n];
      }
  ```



## [322. 零钱兑换](https://leetcode.cn/problems/coin-change/)

- 思路：完全背包的组合问题，dp[i]表示组成金额i所需的最少硬币个数，怎么转移过来的呢，比如当前i，前提是硬币小于金额i，如果选取了此时的硬币，那就是dp[i - 硬币金额] + 1，这个1就是此时选的硬币，如果不选的话，那就是dp[i]

- 详细题解：[322. 零钱兑换](https://leetcode.cn/problems/coin-change/solutions/2948245/322-ling-qian-dui-huan-by-fervent-wilbur-aocg/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int coinChange(int[] coins, int amount) {
          int[] dp = new int[amount + 1];
          Arrays.fill(dp, amount + 1);
          dp[0] = 0;
  
          for (int i = 0; i <= amount; i++) {
              for (int j = 0; j < coins.length; j++) {
                  if(i >= coins[j])
                      dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
              }
          }
          return dp[amount] > amount ? -1 : dp[amount];
      }
  }
  ```



## [139. 单词拆分](https://leetcode.cn/problems/word-break/)

- 思路：完全背包的排列问题，dp[i]代表以前i个字符是否能由数组里面的字符串组成，可以先把数组里面的字符串存到set里面，对于`j < i`，如果dp[j]为true，且从j到i的字符串在set里面有，那就说明dp[i]也为true

- 详细题解：[139. 单词拆分](https://leetcode.cn/problems/word-break/solutions/2891530/139-dan-ci-chai-fen-by-fervent-wilburtzg-rzxx/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public boolean wordBreak(String s, List<String> wordDict) {
          HashSet<String> set = new HashSet<>();
          for (String string : wordDict) {
              set.add(string);
          }
          boolean[] dp = new boolean[s.length() + 1];
          dp[0] = true;
          for (int i = 0; i <= s.length(); i++) {
              for (int j = 0; j < i; j++) {
                  if(dp[j] && set.contains(s.substring(j, i)))
                      dp[i] = true;
              }
          }
          return dp[s.length()];
      }
  }
  ```



## [300. 最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/)

- 思路：dp[i]代表以`nums[i]`结尾的字符的最长递增子序列，对于（j < i）的所有dp[j]，如果此时`nums[j] < nums[i]`，那么就有`dp[i] = max(dp[i], dp[j] + 1)`来更新此时的dp[i]，而更核心的在于，我们要在所有的dp[i]中选择最大的那个，来作为答案，所以要用一个变量ans来记录最大的那个dp[i]

- 详细题解：[300. 最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/solutions/2891564/300-zui-chang-di-zeng-zi-xu-lie-by-ferve-yjtc/)

- 参考代码：

  ```java
  class Solution {
      public int lengthOfLIS(int[] nums) {
          int ans = Integer.MIN_VALUE;
          int[] dp = new int[nums.length];
  
          Arrays.fill(dp, 1);
  
          for (int i = 0; i < nums.length; i++) {
              for (int j = 0; j < i; j++) {
                  if(nums[i] > nums[j])
                      dp[i] = Math.max(dp[i], dp[j] + 1);
              }
              ans = Math.max(ans, dp[i]);
          }
          return ans;
      }
  }
  ```



## [152. 乘积最大子数组](https://leetcode.cn/problems/maximum-product-subarray/)

- 思路：因为负数的存在，它可能会使前面最大的子数组乘上负数变成最小的，反之亦然，因此在不断获取最大子数组的同时，也要维护当前最小的子数组，如果当前元素是小于0的，就进行交换来获得结果

- 详细题解：

- 参考代码：

  ```java
  public int maxProduct(int[] nums) {
      // 初始化
      int maxPre = nums[0];  // 以当前元素结尾的最大乘积
      int minPre = nums[0];  // 以当前元素结尾的最小乘积
      int result = nums[0];   // 全局最大乘积
      
      // 从第二个元素开始遍历————这点格外注意！！！！！
      for (int i = 1; i < nums.length; i++) {
          // 如果当前元素是负数，交换 maxProd 和 minProd
          // 因为负数会使最大变最小，最小变最大
          if (nums[i] < 0) {
              int temp = maxPre;
              maxPre = minPre;
              minPre = temp;
          }
          
          // 更新 maxProd 和 minProd
          // maxProd 和 minProd 都需要考虑当前元素单独作为一个子数组的情况
          maxPre = Math.max(nums[i], maxPre * nums[i]);
          minPre = Math.min(nums[i], minPre * nums[i]);
          
          // 更新全局最大值
          result = Math.max(result, maxPre);
      }
      
      return result;
  }
  ```



## [416. 分割等和子集](https://leetcode.cn/problems/partition-equal-subset-sum/)

- 思路：转化为是否存在这样一个子数组：元素之和等于输入数组的元素之和一半。在此之前先做判断：如果输入数组元素之和为奇数，那肯定不存在这样的子数组直接返回。剩下的就是 0-1 背包问题：从数组中选择一些元素（每个元素只能选一次），使得它们的和恰好为 target。

  定义**dp[i]为：是否存在一个子集，其和为 i**。 dp[0] = true（表示和为 0 总是可行，不选任何元素即可）

  状态转移：

  - 对于数组中的每个元素 nums[i]，我们需要更新 dp 数组。 

  - 从后向前遍历 j（从 target 到 nums[i]），更新规则如下： 

    - `dp[j] = dp[j] || dp[j - nums[i]] `
    - 含义：如果不选当前元素，和为 j 的可能性保持不变（`dp[j]`）；如果选当前元素，则需要看` dp[j - nums[i]] `是否为 true。 

  - 从后向前遍历的目的是避免重复使用同一个元素，确保符合 0-1 背包的规则。 

    ​	

- 详细题解：

- 参考代码：

  ```java
  public boolean canPartition(int[] nums) {  
      // 计算数组总和
      int sum = 0;
      for (int num : nums) {
          sum += num;
      }
      
      // 如果总和是奇数，无法分割成两个和相等的子集
      if (sum % 2 != 0) {
          return false;
      }
      
      // 目标值：总和的一半
      int target = sum / 2;
      
      // 初始化 dp 数组
      boolean[] dp = new boolean[target + 1];
      dp[0] = true;  // 和为 0 总是可行
      
      // 动态规划过程
      for (int num : nums) {
          // 从后向前更新 dp 数组
          for (int j = target; j >= num; j--) {
              dp[j] = dp[j] || dp[j - num];
          }
      }
      
      // 返回结果
      return dp[target];
  }
  ```

  



## [32. 最长有效括号](https://leetcode.cn/problems/longest-valid-parentheses/)（难

- 思路：

- 详细题解：

- 参考代码：

  ```java
  
  ```

  



# 十六、多维动态规划

------

## [62. 不同路径](https://leetcode.cn/problems/unique-paths/)

- 思路：当前状态仅可由左边或上边这两个来转移

- 详细题解：[62. 不同路径](https://leetcode.cn/problems/unique-paths/solutions/2877900/62-bu-tong-lu-jing-by-fervent-wilburtzg-7pby/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int uniquePaths(int m, int n) {
          int[][] dp = new int[m][n];
          for (int i = 0; i < n; i++) {
              dp[0][i] = 1;
          }
          for (int i = 0; i < m; i++) {
              dp[i][0] = 1;
          }
  
          for (int i = 1; i < m; i++) {
              for (int j = 1; j < n; j++) {
                  dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
              }
          }
          
          return dp[m - 1][n - 1];
      }
  }
  ```

  

## [64. 最小路径和](https://leetcode.cn/problems/minimum-path-sum/)

- 思路：同上题，当前状态仅可由上边和左边转移过来，取二者较小值加上当前元素大小。**值得注意的是，本题的初始化**

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public int minPathSum(int[][] grid) {
          int m = grid.length;
          int n = grid[0].length;
  
          int[][] dp = new int[m][n];
          dp[0][0] = grid[0][0];
          for (int i = 1; i < n; i++) {
              dp[0][i] = dp[0][i - 1] + grid[0][i];
          }
  
          for (int i = 1; i < m; i++) {
              dp[i][0] = dp[i - 1][0] + grid[i][0];
          }
  
          for (int i = 1; i < m; i++) {
              for (int j = 1; j < n; j++) {
                  dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
              }
          }
          
          return dp[m - 1][n - 1];
      }
  }
  ```



## [5. 最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)

- 思路：中心扩展法。本题要记住循环上限`2 * n - 1`，以及左指针初始值`i / 2`，右指针`l + i % 2`

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public String longestPalindrome(String s) {
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
  }
  ```



## [1143. 最长公共子序列](https://leetcode.cn/problems/longest-common-subsequence/)

- 思路：本题主要是状态方程的定义都很新颖。`dp[i][j]`表示字符串1前i个字符和字符串2的前j个字符的最长公共子序列。然后分当前字符相同和不同两种情况来转移状态。如果相同，`dp[i][j] = dp[i - 1][j - 1] + 1 `，否则，`Math.max(dp[i - 1][j], dp[i][j - 1]) `。另外需要注意循环上限以及下标问题

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public int longestCommonSubsequence(String text1, String text2) {
          int m = text1.length();
          int n = text2.length();
          int[][] dp = new int[m + 1][n + 1];
  
          for (int i = 1; i <= m; i++) {
              for (int j = 1; j <= n; j++) {
                  if(text1.charAt(i- 1) == text2.charAt(j- 1))
                      dp[i][j] = dp[i - 1][j - 1] + 1;
                  else
                      dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
              }
          }
          return dp[m][n];
      }
  }
  ```



## [72. 编辑距离](https://leetcode.cn/problems/edit-distance/)

- 思路：

  ### 方法思路

  1. **状态定义**：定义二维数组 `dp[i][j]`，表示将 `word1` 的前 `i` 个字符转换为 `word2` 的前 `j` 个字符所需的最少操作次数。
  2. **初始状态**：
     - `dp[i][0] = i`：删除所有 `i` 个字符。
     - `dp[0][j] = j`：插入所有 `j` 个字符。
  3. **状态转移**：
     - **字符相同**：`dp[i][j] = dp[i-1][j-1]`（无需操作）。
     - **字符不同**：取三种操作的最小值加1：
       - **替换**：`dp[i-1][j-1] + 1`。
       - **删除**：`dp[i-1][j] + 1`。
       - **插入**：`dp[i][j-1] + 1`。

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public int minDistance(String word1, String word2) {
          int m = word1.length();
          int n = word2.length();
  
          int[][] dp = new int[m + 1][n + 1];
          for (int i = 0; i <= n; i++) {
              dp[0][i] = i;
          }
  
          for (int i = 0; i <= m; i++) {
              dp[i][0] = i;
          }
  
          for (int i = 1; i <= m; i++) {
              for (int j = 1; j <= n; j++) {
                  if(word1.charAt(i - 1) == word2.charAt(j - 1))
                      dp[i][j] = dp[i - 1][j - 1];
                  else
                      dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
              }
          }
          return dp[m][n];
      }
  }
  ```

  

# 十七、技巧

## [136. 只出现一次的数字](https://leetcode.cn/problems/single-number/)

- 思路：数字与，

  - 任何数和 0 做异或运算，结果仍然是原来的数，即 `a⊕0=a`。 
  - 任何数和其自身做异或运算，结果是 0，即 `a⊕a=0`。 
  - 异或运算满足交换律和结合律，即 `a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b`。 

  根据以上三条性质，可对数组中所有数字进行与运算，最终得到的结果就是只出现一次的数字

- 详细题解：

- 参考代码：

  ```java
  class Solution {
      public int singleNumber(int[] nums) {
          if(nums.length == 1)
              return nums[0];
          int k = nums[0];
          for (int i = 1; i < nums.length; i++) {
              k = k ^ nums[i];
          }
          return k;
      }
  }
  ```



## [169. 多数元素](https://leetcode.cn/problems/majority-element/)

- 思路：摩尔投票，维护一个vote代表当前投票数，先假设众数就是当前遍历的数字，然后继续遍历，如果当前数与众数相同，vote+1，反之则减1，如果vote为0就设置众数为当前数，最终返回众数即是答案

- 详细题解：[169. 多数元素](https://leetcode.cn/problems/majority-element/solutions/2889316/169-duo-shu-yuan-su-by-fervent-wilburtzg-238y/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public int majorityElement(int[] nums) {
          int ans = 0;
          int vote = 0;
          for (int num : nums) {
              if(vote == 0){
                  ans = num;
              }
              if(num == ans)
                  vote++;
              else 
                  vote--;
          }
          return ans;
      }
  }
  ```

## [75. 颜色分类](https://leetcode.cn/problems/sort-colors/)

“荷兰国旗”问题，[详细题解可参照](https://cloud.tencent.com/developer/article/1624933)

- 思路：三指针，因为要将数据分成三部分，有一个指针c用于遍历数组，a指针分隔0，1；b指针分隔1，2，退出条件为 c > b，然后根据c当前元素做出不同的操作

  - c当前元素为0，那么就交换a和c所指的元素，同时a，c右移 
  - 当前元素为1，那么a，b线都不动，让c右移 
  - 当前元素为2，那交换a和b所指的元素，但c不能右移，因为交换过来的可能是0， 如果右移了但a没有同步移动，就会出错，所以c要停留，到下次知道这个c所指的元素是什么再做判断 

- 详细题解：[75.颜色分类](https://leetcode.cn/problems/sort-colors/solutions/2892121/75-yan-se-fen-lei-he-lan-guo-qi-wen-ti-b-uhnk/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public void sortColors(int[] nums) {
          int a = 0, c = 0, b = nums.length - 1;
          while (c <= b){
              if(nums[c] == 0){
                  int temp = nums[c];
                  nums[c++] = nums[a];
                  nums[a++] = temp;
              } else if (nums[c] == 1) {
                  c++;
              }else {
                  int temp = nums[b];
                  nums[b--] = nums[c];
                  nums[c] = temp;
              }
          }
      }
  }
  ```



## [31. 下一个排列](https://leetcode.cn/problems/next-permutation/) （思路

- 思路：从后往前找到第一个不满足递增的元素a，然后从这个元素后面找到一个比它大一点的元素b，让它俩交换，最后让元素b后面的所有元素用双指针两两交换，成为从前往后是递增的

- 详细题解：[31. 下一个排列](https://leetcode.cn/problems/next-permutation/solutions/2895676/31-xia-yi-ge-pai-lie-by-fervent-wilburtz-63oj/?envType=study-plan-v2&envId=top-100-liked)

- 参考代码：

  ```java
  class Solution {
      public void nextPermutation(int[] nums) {
          int i = nums.length - 2;
          while (i >= 0 && nums[i] >= nums[i + 1])
              i--;
          if(i >= 0){
              int j = nums.length - 1;
              while (j > i && nums[j] <= nums[i])
                  j--;
              int temp = nums[j];
              nums[j] = nums[i];
              nums[i] = temp;
          }
          
          int low = i + 1;
          int high = nums.length - 1;
          while (low <= high){
              int temp = nums[low];
              nums[low++] = nums[high];
              nums[high--] = temp;
          }
      }
  }
  ```



## [287. 寻找重复数](https://leetcode.cn/problems/find-the-duplicate-number/)

- 思路：**龟兔赛跑**算法，类似链表环问题，让元素映射到链表节点上，通过快慢指针，第一步先找到环，当两个指针相遇后，将其中一个指针移回起点，并以相同速度前进，直到两个指针再次相遇。再次相遇的点即为环的起点，也就是重复的数字。

- 详细题解：使用 Floyd 的循环检测算法

  1. **将数组映射为链表**：
     - 由于 `nums` 中的值范围是 `[1, n]`，我们可以将每个数字视为链表的下标。
     - 例如，`nums[i]` 指向下标 `nums[nums[i]]`。
     - 重复的数字会导致形成环。
  2. **检测环的起点**：
     - 使用两个指针：一个慢指针（`slow`）和一个快指针（`fast`）。
     - `slow` 每次移动一步，`fast` 每次移动两步。
     - 如果存在环，`slow` 和 `fast` 最终会相遇。
  3. **找到环的起点（重复数字）**：
     - 当两个指针相遇后，将其中一个指针移回起点，并以相同速度前进，直到两个指针再次相遇。
     - 再次相遇的点即为环的起点，也就是重复的数字。

- 参考代码：

  ```java
  class Solution {
      public int findDuplicate(int[] nums) {
          int slow = nums[0], fast = nums[0];
          do {
              slow = nums[slow];
              fast = nums[nums[fast]];
          } while (slow != fast);
          slow = nums[0];
          while (slow != fast) {
              slow = nums[slow];
              fast = nums[fast];
          }
          return slow;
      }
  }
  ```

  

