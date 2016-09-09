package tictactoe.ui;


import tictactoe.board.Board;
import tictactoe.record.MoveHistory;

/**
 * Created by hanalee on 7/28/16.
 */
public interface GameUI {

    void displayBoard(Board board, MoveHistory record);

    String promptMove();

    void displayTitle();

    void displayMoveNumber(int moveNumber);

    void displayWin(int player);

    void displayTie();

    void displayEnding();

    void displayError(int errorCode);

}
