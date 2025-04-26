
/*
 * @source https://leetcode.com/problems/max-consecutive-ones-iii/
 * @author xiaoque
 * @date 2025.03.24
 */
public class MaxConsecutiveOnesIII {
    /*
     * Note: binany array, max consecutive 1 + k
     * maintain at most k 0s, to see max 1s
     */
    public int longestOnes(int[] nums, int k) {
        int max = 0, begin = 0, zeros = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
            }
            // remove previous 0, move begin forward
            while (zeros > k) {
                if (nums[begin] == 0) {
                    zeros--;
                }
                begin++;
            }
            // keep track of length for every move
            max = Math.max(max, i - begin + 1);
        }
        return max;
    }
}
