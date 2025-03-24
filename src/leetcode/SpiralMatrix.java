package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * @source https://leetcode.com/problems/spiral-matrix/description/
 * @author xiaoque
 * @date 2025.03.22
 */
public class SpiralMatrix {
    /*
     * define range of each direction,
     * initial case left =0, top = 0, right = col -1, bottom = row -1
     * moving range right (left, right) at top row, top++
     * bottom (top, bottom) at right col, right --
     * left (right, left) at bottom row, bottom--
     * up (bottom, top) at left col, left++
     * end if left > right,
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int col = matrix[0].length;
        int row = matrix.length;
        int left = 0, top = 0, right = col - 1, bottom = row - 1;
        while (top < bottom) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // need to recheck the condition after modification
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;
    }
}
