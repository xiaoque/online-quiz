
import java.util.Stack;

/*
 * @source https://leetcode.com/problems/baseball-game/description/
 * @author xiaoque
 * @date 2025.03.22
 */
public class BaseballGame {
    public int calPoints(String[] operations) {
        Stack<Integer> recentScores = new Stack<>();
        for (String operation : operations) {
            switch (operation) {
                case "C" -> recentScores.pop();
                case "D" -> recentScores.push(2 * recentScores.peek());
                case "+" -> {
                    int lastScore = recentScores.pop();
                    int newScore = recentScores.peek() + lastScore;
                    recentScores.push(lastScore);
                    recentScores.push(newScore);
                }
                default -> {
                    recentScores.push(Integer.valueOf(operation));
                }
            }
        }
        int result = 0;
        while (!recentScores.isEmpty()) {
            result += recentScores.pop();
        }
        return result;
    }
}
