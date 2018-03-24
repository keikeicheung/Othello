package game;

public enum Player {
    X(1), O(2);

    private int value;

    Player(int value) {
        this.value = value;
    }

    public static Player getPlayerByIntValue(int value) {
        for (Player player : Player.values()) {
            if (player.value == value) {
                return player;
            }
        }
        throw new IllegalArgumentException("Cannot find Player with Int Value " + value);
    }

    public static boolean isOppositePlayer(int playerOnBoard, int currentPlayer) {
        return playerOnBoard != currentPlayer && playerOnBoard != 0;
    }

    public int getIntValue() {
        return value;
    }
}
