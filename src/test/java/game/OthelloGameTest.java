package game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OthelloGameTest {

    private final OthelloGame othelloGame = new OthelloGame(
            "--------\n" +
                    "--------\n" +
                    "--------\n" +
                    "---OX---\n" +
                    "---XO---\n" +
                    "--------\n" +
                    "--------\n" +
                    "--------"
    );

    @Test
    public void shouldHave8x8BoardOnGameStart() {
        OthelloGame othelloGame = new OthelloGame(8);
        assertEquals(othelloGame.displayBoard(),
                "--------\n" +
                        "--------\n" +
                        "--------\n" +
                        "---OX---\n" +
                        "---XO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldStartWithPlayerXAndPlaceDiscThatTurnsAllVerticalAsPlayerX() {
        assertEquals(othelloGame.placeDisc("3d"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "---XO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldPlaceDiscWithPlayerOAfterXThatTurnsAllHorizontalAsPlayerO() {
        othelloGame.placeDisc("3d");
        assertEquals(othelloGame.placeDisc("c5"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "--OOO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldShowErrorOnInvalidMove() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        assertEquals(othelloGame.placeDisc("e7"),
                "Invalid move. Please try again."
        );
    }

    @Test
    public void shouldBeSamePlayerIfThePreviousMoveWasInvalid() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        othelloGame.placeDisc("e7");
        assertEquals(othelloGame.placeDisc("e6"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "--OOX---\n" +
                        "----X---\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldBeOppositePlayerTurnAfterASuccessfulMove() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        othelloGame.placeDisc("e7");
        othelloGame.placeDisc("e6");
        assertEquals(othelloGame.placeDisc("5f"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "--OOOO--\n" +
                        "----X---\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldTurnDiscOnPositiveGradientDiagonal() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        othelloGame.placeDisc("e7");
        othelloGame.placeDisc("e6");
        othelloGame.placeDisc("5f");
        assertEquals(othelloGame.placeDisc("6c"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "--OXOO--\n" +
                        "--X-X---\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldTurnDiscOnNegativeGradientDiagonal() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        othelloGame.placeDisc("e7");
        othelloGame.placeDisc("e6");
        othelloGame.placeDisc("5f");
        assertEquals(othelloGame.placeDisc("6f"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "--OOXO--\n" +
                        "----XX--\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldTurnDiscOnPositiveGradientDiagonalWithOffset() {
        OthelloGame initialBoard = new OthelloGame(
                "--------\n" +
                        "--------\n" +
                        "----X---\n" +
                        "---OO---\n" +
                        "---OO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------");
        assertEquals(initialBoard.placeDisc("5c"),
                "--------\n" +
                        "--------\n" +
                        "----X---\n" +
                        "---XO---\n" +
                        "--XOO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }


    @Test
    public void shouldTurnDiscOnNegativeGradientDiagonalWithOffset() {
        OthelloGame initialBoard = new OthelloGame(
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---OO---\n" +
                        "---OO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------");
        assertEquals(initialBoard.placeDisc("5f"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---OX---\n" +
                        "---OOX--\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldReturnResultOnGameEnd() {
        OthelloGame initialBoard = new OthelloGame(
                "XXXXXXXX\n" +
                        "XXXXXXOX\n" +
                        "XXXXXXXX\n" +
                        "XXXXXXXX\n" +
                        "OXXXXXXX\n" +
                        "OXXXXXXX\n" +
                        "-XXOXXXX\n" +
                        "OXXOXXXX");
        assertEquals(initialBoard.placeDisc("a7", Player.O),
                "XXXXXXXX\n" +
                        "XXXXXXOX\n" +
                        "XXXXXXXX\n" +
                        "XXXXXXXX\n" +
                        "OXXXXXXX\n" +
                        "OXXXXXXX\n" +
                        "OOOOXXXX\n" +
                        "OXXOXXXX"
        );
        assertEquals(initialBoard.nextStep(),
                "No further moves available\n" +
                        "Player 'X' wins ( 55 vs 9 )");
    }

    @Test
    public void shouldShowInvalidMoveIfInbetweenPlayerHasEmptyCell() {
        OthelloGame initialBoard = new OthelloGame(
                "--------\n" +
                        "--------\n" +
                        "----O---\n" +
                        "--XOO---\n" +
                        "--OXXX--\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
        assertEquals(initialBoard.placeDisc("a1"), "Invalid move. Please try again.");
    }

    @Test
    public void shouldReplaceDiscInBetweenTheNewlyAddedDisc() {
        OthelloGame initialBoard = new OthelloGame(
                "O-------\n" +
                        "OXXX-X--\n" +
                        "OXOOXX--\n" +
                        "O-OXOX--\n" +
                        "--OOXXXX\n" +
                        "--OOOO--\n" +
                        "----O---\n" +
                        "--------");
        assertEquals(initialBoard.placeDisc("8e"),
                "O-------\n" +
                        "OXXX-X--\n" +
                        "OXOOXX--\n" +
                        "O-OXOX--\n" +
                        "--OOXXXX\n" +
                        "--OOXO--\n" +
                        "----X---\n" +
                        "----X---");
    }

    @Test
    public void test() {
        String[] commands = {"4c", "3e", "4f", "5c", "6c", "5b", "e6", "7e", "5f", "b7", "5a",
                "g4", "4h", "3b", "4b", "2b", "6b", "5g", "5h", "4a", "3a", "6h", "6a", "7c", "7d",
                "7a", "8a", "6d", "3f", "6f", "6g", "8c", "7f", "g7", "d8", "8e", "7h", "8h",
                "3g", "8f", "8g", "8b", "3d", "3c", "2d", "1d", "2e", "2f", "3h", "2c", "2b", "2a",
                "1a", "1b", "1c", "2h", "1e", "1f", "2g", "1h", "1g"};
        for (String command : commands) {
            othelloGame.placeDisc(command);
        }
        assertEquals(othelloGame.nextStep(), "No further moves available\n" +
                "Player 'O' wins ( 39 vs 25 )");
    }


}