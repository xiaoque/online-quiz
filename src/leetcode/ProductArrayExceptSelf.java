package leetcode;

import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/product-of-array-except-self
 * @author xiaoque
 * @date 2025.03.22
 */
public class ProductArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        // use var to store prev value, instead of put it directly in result
        int[] res = new int[nums.length];
        Arrays.fill(res, 1);
        int prev = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] *= prev;
            prev *= nums[i];
        }

        prev = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= prev;
            prev *= nums[i];
        }
        return res;
    }
}
