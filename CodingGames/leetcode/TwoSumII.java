
/*
 * @source https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * @author xiaoque
 * @date 2025.03.22
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int prev = 0;
        int later = numbers.length - 1;
        if (numbers.length == 2)
            return new int[] { 1, 2 };

        // traverse the array with 2 directions
        // when the sum is greater than target, later --
        // when the sum is smaller than targer, prev ++

        while (prev < later) {
            if (numbers[prev] + numbers[later] == target)
                break;
            if (numbers[prev] + numbers[later] < target) {
                prev++;
            } else {
                later--;
            }
        }
        return new int[] { prev + 1, later + 1 };
    }
}
