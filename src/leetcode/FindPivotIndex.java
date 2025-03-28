package leetcode;

import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/find-pivot-index/
 * @author xiaoque
 * @date 2025.03.24
 */
public class FindPivotIndex {
    /**
     * nums[i] in [-1000, 1000]
     * i sum([0,i-1]) = sum([i+1, n])
     * rightSum = total - leftSum - nums[i]
     */
    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int leftSum = 0;

        // find leftmost index that 2 *leftSum + nums[i] = total;
        for (int i = 0; i < nums.length; i++) {
            if (2 * leftSum + nums[i] == total)
                return i;
            leftSum += nums[i];
        }
        return -1;
    }
}
