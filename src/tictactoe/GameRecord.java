package tictactoe;


/**
 * Created by hanalee on 7/28/16.
 */
public class GameRecord {
    private int lastMove;
    private GameBoard board;

    GameRecord(GameBoard board) {
        this.board = board;
    }

    public void newMove(int move) {
        lastMove = move;
    }

    public int getLastMove() {
        return lastMove;
    }

    public boolean isValidMove(int move) {
        int dim = board.getNumRows();
        if (move > dim * dim || move < 0) {
            return false;
        }

        return true;

    }
}
