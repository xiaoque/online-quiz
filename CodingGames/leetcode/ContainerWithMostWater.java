
/*
 * @source https://leetcode.com/problems/container-with-most-water/
 * @author xiaoque
 * @date 2025.03.23
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        // result = min(height[i], height[j])*(j - i)
        // max(j -i) = height.length, as index move closer it gets smaller
        // to get larger height[i], move the smaller one
        int result = 0;
        int begin = 0, end = height.length - 1;
        while (begin < end) {
            result = Math.max(Math.min(height[begin], height[end]) * (end - begin), result);
            if (height[begin] < height[end]) {
                begin++;
            } else {
                end--;
            }
        }
        return result;
    }
}
