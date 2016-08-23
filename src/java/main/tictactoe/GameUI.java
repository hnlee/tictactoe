package tictactoe;


/**
 * Created by hanalee on 7/28/16.
 */
public interface GameUI {

    void displayBoard(MoveHistory record);

    String promptMove();

    void displayTitle();

    void displayMoveNumber(int moveNumber);

    void displayWin(GamePlayer player);

    void displayTie();

    void displayEnding();

    void displayError(String errorType);

}
