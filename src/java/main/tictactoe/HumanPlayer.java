package tictactoe;

import java.util.NoSuchElementException;

/**
 * Created by hanalee on 8/4/16.
 */
public class HumanPlayer extends GamePlayer {
    public HumanPlayer(String marker) {
        super(marker);
    }

    public int move(GameUI ui) {
        String prompt = "Enter move";
        String playerInput = ui.displayPrompt(prompt);
        return Integer.parseInt(playerInput);
    }

}
