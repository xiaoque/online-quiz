package leetcode;

/*
 * @source https://leetcode.com/problems/find-the-highest-altitude/
 * @author xiaoque
 * @date 2025.03.24
 */
public class FindHighestAltitude {
    /**
     * Note: cumulate sum at position i
     */
    public int largestAltitude(int[] gain) {
        int curr = 0, highest = 0;
        for (int num : gain) {
            curr += num;
            highest = Math.max(highest, curr);
        }
        return highest;
    }
}
