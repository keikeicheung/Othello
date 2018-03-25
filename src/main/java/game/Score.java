package game;

public class Score {
    private int playerXScore;
    private int playerOScore;
    private int[][] board;

    public Score(int[][] board) {
        this.board = board;
    }

    public int getPlayerXScore() {
        return playerXScore;
    }

    public int getPlayerOScore() {
        return playerOScore;
    }

    public void calculateScore() {
        for (int y = 0; y < board.length; y++) {
            int[] row = board[y];
            for (int x = 0; x < row.length; x++) {
                Player player = Player.getPlayerByIntValue(row[x]);
                if (player == Player.X) {
                    playerXScore += 1;
                } else if (player == Player.O) {
                    playerOScore += 1;
                }
            }
        }
    }
}
