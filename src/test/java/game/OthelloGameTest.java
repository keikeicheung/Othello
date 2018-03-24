package game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OthelloGameTest {

    private final OthelloGame othelloGame = new OthelloGame(
            "1 --------\n" +
                    "2 --------\n" +
                    "3 --------\n" +
                    "4 ---OX---\n" +
                    "5 ---XO---\n" +
                    "6 --------\n" +
                    "7 --------\n" +
                    "8 --------\n" +
                    "  abcdefgh"
    );

    @Test
    public void shouldHave8x8BoardOnGameStart() {
//        OthelloGame othelloGame = new OthelloGame(8);
        assertEquals(othelloGame.displayBoard(),
                "1 --------\n" +
                        "2 --------\n" +
                        "3 --------\n" +
                        "4 ---OX---\n" +
                        "5 ---XO---\n" +
                        "6 --------\n" +
                        "7 --------\n" +
                        "8 --------\n" +
                        "  abcdefgh"
        );
    }

    @Test
    public void shouldStartWithPlayerXAndPlaceDiscThatTurnsAllVerticalAsPlayerX() {
        assertEquals(othelloGame.placeDisc("3d"),
                "1 --------\n" +
                        "2 --------\n" +
                        "3 ---X----\n" +
                        "4 ---XX---\n" +
                        "5 ---XO---\n" +
                        "6 --------\n" +
                        "7 --------\n" +
                        "8 --------\n" +
                        "  abcdefgh"
        );
    }

    @Test
    public void shouldPlaceDiscWithPlayerOAfterXThatTurnsAllHorizontalAsPlayerO() {
        othelloGame.placeDisc("3d");
        assertEquals(othelloGame.placeDisc("c5"),
                "1 --------\n" +
                        "2 --------\n" +
                        "3 ---X----\n" +
                        "4 ---XX---\n" +
                        "5 --OOO---\n" +
                        "6 --------\n" +
                        "7 --------\n" +
                        "8 --------\n" +
                        "  abcdefgh"
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
                "1 --------\n" +
                        "2 --------\n" +
                        "3 ---X----\n" +
                        "4 ---XX---\n" +
                        "5 --OOX---\n" +
                        "6 ----X---\n" +
                        "7 --------\n" +
                        "8 --------\n" +
                        "  abcdefgh"
        );
    }

    @Test
    public void shouldBeOppositePlayerTurnAfterASuccessfulMove() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        othelloGame.placeDisc("e7");
        othelloGame.placeDisc("e6");
        assertEquals(othelloGame.placeDisc("5f"),
                "1 --------\n" +
                        "2 --------\n" +
                        "3 ---X----\n" +
                        "4 ---XX---\n" +
                        "5 --OOOO--\n" +
                        "6 ----X---\n" +
                        "7 --------\n" +
                        "8 --------\n" +
                        "  abcdefgh"
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
                "1 --------\n" +
                        "2 --------\n" +
                        "3 ---X----\n" +
                        "4 ---XX---\n" +
                        "5 --OXOO--\n" +
                        "6 --X-X---\n" +
                        "7 --------\n" +
                        "8 --------\n" +
                        "  abcdefgh"
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
                "1 --------\n" +
                        "2 --------\n" +
                        "3 ---X----\n" +
                        "4 ---XX---\n" +
                        "5 --OOXO--\n" +
                        "6 ----XX--\n" +
                        "7 --------\n" +
                        "8 --------\n" +
                        "  abcdefgh"
        );
    }

    @Test
    public void shouldTurnDiscOnPositiveGradientDiagonalWithOffset() {
        OthelloGame initialBoard = new OthelloGame(
                "1 --------\n" +
                        "2 --------\n" +
                        "3 ----X---\n" +
                        "4 ---OO---\n" +
                        "5 ---OO---\n" +
                        "6 --------\n" +
                        "7 --------\n" +
                        "8 --------\n" +
                        "  abcdefgh");
        assertEquals(initialBoard.placeDisc("5c"),
                "1 --------\n" +
                        "2 --------\n" +
                        "3 ----X---\n" +
                        "4 ---XO---\n" +
                        "5 --XOO---\n" +
                        "6 --------\n" +
                        "7 --------\n" +
                        "8 --------\n" +
                        "  abcdefgh"
        );
    }


    @Test
    public void shouldTurnDiscOnNegativeGradientDiagonalWithOffset() {
        OthelloGame initialBoard = new OthelloGame(
                "1 --------\n" +
                        "2 --------\n" +
                        "3 ---X----\n" +
                        "4 ---OO---\n" +
                        "5 ---OO---\n" +
                        "6 --------\n" +
                        "7 --------\n" +
                        "8 --------\n" +
                        "  abcdefgh");
        assertEquals(initialBoard.placeDisc("5f"),
                "1 --------\n" +
                        "2 --------\n" +
                        "3 ---X----\n" +
                        "4 ---OX---\n" +
                        "5 ---OOX--\n" +
                        "6 --------\n" +
                        "7 --------\n" +
                        "8 --------\n" +
                        "  abcdefgh"
        );
    }

    @Test
    public void shouldReturnResultOnGameEnd() {
        OthelloGame initialBoard = new OthelloGame(
                "1 XXXXXXXX\n" +
                        "2 XXXXXXOX\n" +
                        "3 XXXXXXXX\n" +
                        "4 XXXXXXXX\n" +
                        "5 OXXXXXXX\n" +
                        "6 OXXXXXXX\n" +
                        "7 -XXOXXXX\n" +
                        "8 OXXOXXXX\n" +
                        "  abcdefgh");
        assertEquals(initialBoard.placeDisc("a7", Player.O),
                "1 XXXXXXXX\n" +
                        "2 XXXXXXOX\n" +
                        "3 XXXXXXXX\n" +
                        "4 XXXXXXXX\n" +
                        "5 OXXXXXXX\n" +
                        "6 OXXXXXXX\n" +
                        "7 OOOOXXXX\n" +
                        "8 OXXOXXXX\n" +
                        "  abcdefgh"
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
                "1 O-------\n" +
                        "2 OXXX-X--\n" +
                        "3 OXOOXX--\n" +
                        "4 O-OXOX--\n" +
                        "5 --OOXXXX\n" +
                        "6 --OOOO--\n" +
                        "7 ----O---\n" +
                        "8 --------\n" +
                        "  abcdefgh");
        assertEquals(initialBoard.placeDisc("8e"),
                "1 O-------\n" +
                        "2 OXXX-X--\n" +
                        "3 OXOOXX--\n" +
                        "4 O-OXOX--\n" +
                        "5 --OOXXXX\n" +
                        "6 --OOXO--\n" +
                        "7 ----X---\n" +
                        "8 ----X---\n" +
                        "  abcdefgh");
    }

    @Test
    public void shouldReturnSummaryAfterAListOfCommandsAndNoFurtherMoves() {
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