
/*
 * @source https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/description/
 * @author xiaoque
 * @date 2025.03.22
 */
public class TicTacToe {
    public String tictactoe(int[][] moves) {
        if (moves.length <= 4)
            return "Pending";

        int[][] board = new int[3][3];
        boolean isAMove = true;
        for (int[] move : moves) {
            board[move[0]][move[1]] = isAMove ? 1 : -1;
            isAMove = !isAMove;
        }

        // check row & column
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0] > 0 ? "A" : "B";
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i] > 0 ? "A" : "B";
            }
        }

        if ((board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2])
                || (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return board[1][1] > 0 ? "A" : "B";
        }

        if (moves.length == 9)
            return "Draw";

        return "Pending";
    }
}
