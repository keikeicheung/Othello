package game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OthelloGameTest {

    private final OthelloGame othelloGame = new OthelloGame(8);

    @Test
    public void shouldHave8x8BoardOnGameStart() {
        assertEquals(othelloGame.displayBoard(),
                "--------\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldStartWithPlayerXAndPlaceDiscWithCoordinate() {
        othelloGame.placeDisc("3d");
        assertEquals(othelloGame.displayBoard(),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowExceptionWhenInputInvalidCoordinate() {
        othelloGame.placeDisc("invalid-coordinate");
    }

}