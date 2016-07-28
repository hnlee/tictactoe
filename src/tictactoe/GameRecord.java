package tictactoe;


/**
 * Created by hanalee on 7/28/16.
 */
public class GameRecord {
    private int lastMove;

    public void newMove(int move) {
        lastMove = move;
    }

    public int getLastMove() {
        return lastMove;
    }
}
