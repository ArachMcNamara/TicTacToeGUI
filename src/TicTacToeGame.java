public class TicTacToeGame {
    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private int moves = 0;

    public TicTacToeGame() {
        reset();
    }

    public void reset() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                board[r][c] = ' ';
        currentPlayer = 'X';
        moves = 0;
    }

    public boolean isLegal(int r, int c) {
        return board[r][c] == ' ';
    }

    public void makeMove(int r, int c) {
        board[r][c] = currentPlayer;
        moves++;
    }

    public boolean checkWin() {
        char p = currentPlayer;

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p) return true;
            if (board[0][i] == p && board[1][i] == p && board[2][i] == p) return true;
        }

        return (board[0][0] == p && board[1][1] == p && board[2][2] == p) ||
                (board[0][2] == p && board[1][1] == p && board[2][0] == p);
    }

    public boolean checkTie() {
        return moves == 9;
    }

    public boolean checkEarlyTie() {
        // For each row
        for (int r = 0; r < 3; r++) {
            if (lineStillPossible(board[r][0], board[r][1], board[r][2]))
                return false;
        }

        // For each column
        for (int c = 0; c < 3; c++) {
            if (lineStillPossible(board[0][c], board[1][c], board[2][c]))
                return false;
        }

        // Diagonal 1
        if (lineStillPossible(board[0][0], board[1][1], board[2][2]))
            return false;

        // Diagonal 2
        if (lineStillPossible(board[0][2], board[1][1], board[2][0]))
            return false;

        return true; // No line is winnable → early tie
    }

    private boolean lineStillPossible(char a, char b, char c) {
        boolean hasX = (a == 'X' || b == 'X' || c == 'X');
        boolean hasO = (a == 'O' || b == 'O' || c == 'O');
        return !(hasX && hasO); // If both appear, line is dead
    }


    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
}
