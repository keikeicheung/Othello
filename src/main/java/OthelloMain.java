import game.OthelloGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OthelloMain {
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*** Start Othello Game ***");
        OthelloGame othelloGame = new OthelloGame(8);
        String input = "";
        System.out.println(othelloGame.displayBoard());
        try {
            while (!input.equalsIgnoreCase("quit")) {
                System.out.println(othelloGame.nextStep());
                input = in.readLine();
                System.out.println(othelloGame.placeDisc(input));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
