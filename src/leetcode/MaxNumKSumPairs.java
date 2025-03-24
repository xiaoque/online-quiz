package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/*
 * @source https://leetcode.com/problems/max-number-of-k-sum-pairs/
 * @author xiaoque
 * @date 2025.03.23
 */
public class MaxNumKSumPairs {
    // opt1: sort the array, turn the problem into find nums[i] + num[j] = k,
    // (nlogn)
    // opt2: map to store: (k-num[i], freq), if there is a match, count++ (n)
    public int maxOperations(int[] nums, int k) {

        Arrays.sort(nums);
        int count = 0, left = 0, right = nums.length - 1;

        while (left < right) {
            int curr = nums[left] + nums[right];
            if (curr == k) {
                count++;
                left++;
                right--;
            } else if (curr > k) {
                right--;
            } else if (curr < k) {
                left++;
            }
        }

        return count;
    }

    public int maxOperations2(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int count = 0;

        for (int num : nums) {
            int val = k - num;
            if (freq.getOrDefault(val, 0) > 0) {
                count++;
                freq.put(val, freq.get(val) - 1);
            } else {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
        }
        return count;
    }
}
