package tictactoe;

/**
 * Created by hanalee on 8/17/16.
 */
public interface Endgame {

    boolean isGameWon(MoveHistory record);

    boolean isGameTied(MoveHistory record);

    boolean isValidMove(int move, MoveHistory record);

}
