/*
 * @source https://leetcode.com/problems/calculate-score-after-performing-instructions/
 * @author xiaoque
 * @date 2025.04.21
 */
public class CalculateScoreAfterPerformingInstructions {
    public long calculateScore(String[] instructions, int[] values) {
        // use an arr to store visited
        long result = 0;
        int index = 0;
        int n = instructions.length;
        boolean[] visited = new boolean[n];
        // iterate until index out of bound or visited
        while (index >= 0 && index < n) {
            if (visited[index])
                break;
            visited[index] = true;
            if (instructions[index].equals("jump")) {
                index += values[index];
            } else {
                result += values[index];
                index++;
            }
        }
        return result;
    }
}
