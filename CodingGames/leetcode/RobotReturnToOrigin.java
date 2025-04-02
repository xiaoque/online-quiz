
/*
 * @source https://leetcode.com/problems/robot-return-to-origin/description/
 * @author xiaoque
 * @date 2025.03.22
 */
public class RobotReturnToOrigin {
    public boolean judgeCircle(String moves) {
        int[] position = new int[2];
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'U' -> position[1]++;
                case 'D' -> position[1]--;
                case 'L' -> position[0]--;
                case 'R' -> position[0]++;
            }
        }
        return (position[0] == 0) && (position[1] == 0);
    }
}
