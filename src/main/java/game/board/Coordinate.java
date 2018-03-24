package game.board;

public class Coordinate {
    private final int x;
    private final int y;
    private static final String yPos = "12345678";
    private static final String xPos = "abcdefgh";

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate parseCoordinate(String coordinateStr) {
        char[] positions = coordinateStr.toCharArray();
        int x = -1;
        int y = -1;
        for (int i = 0; i < positions.length; i++) {
            if (xPos.indexOf(positions[i]) > -1) {
                x = xPos.indexOf(positions[i]);
            } else {
                y = yPos.indexOf(positions[i]);
            }
        }
        return new Coordinate(x, y);
    }

    public boolean isValidPositionToPlaceDisc(int boardSize) {
        return x > -1 && x < boardSize && y > -1 && y < boardSize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
