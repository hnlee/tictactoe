package tictactoe;


/**
 * Created by hanalee on 7/28/16.
 */
public interface GameUI {

    void displayMessage(String message);

    void displayBoard(GameRecord record);

    String getUserInput();

    default String displayPrompt(String prompt) {
        displayMessage(prompt);
        return getUserInput();
    }
}
