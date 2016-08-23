package tictactoe;


/**
 * Created by hanalee on 7/28/16.
 */
public interface GameUI {

    void displayMessage(String message);

    void displayBoard(MoveHistory record);

    String getUserInput();

    String displayPrompt(String prompt);

    void displayTitle();

    void displayMoveNumber(int moveNumber);

    void displayEnding();

    String promptMove();

    void displayError(String errorType);

}
