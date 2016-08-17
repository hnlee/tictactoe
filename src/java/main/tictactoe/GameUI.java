package tictactoe;


/**
 * Created by hanalee on 7/28/16.
 */
public interface GameUI {

    void displayMessage(String message);

    void displayBoard(MoveHistory record);

    String getUserInput();

    default String displayPrompt(String prompt) {
        displayMessage(prompt);
        return getUserInput();
    }

    default void displayTitle() {
        displayMessage("Tic Tac Toe");
    }

    default void displayMoveNumber(int moveNumber) {
        displayMessage(String.format("Move #%d", moveNumber + 1));
    }

    default void displayEnding() {
        displayMessage("Game over");
    }

    default String promptMove() {
        return displayPrompt("Enter move");
    }

    default void displayError(String errorType) {
        if (errorType.equals("invalid")) {
            displayMessage("Invalid move");
        } else {
            displayMessage("Error");
        }
    }

}
