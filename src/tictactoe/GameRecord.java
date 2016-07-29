package tictactoe;

import java.util.ArrayList;

/**
 * Created by hanalee on 7/28/16.
 */
public class GameRecord {
    private ArrayList<Integer> moves;
    private GameBoard board;
    private GamePlayer lastPlayer;

    GameRecord(GameBoard board) {
        this.board = board;
        this.moves = new ArrayList<Integer>();
    }

    public void newMove(int move, GamePlayer player) {
        moves.add(move);
        lastPlayer = player;
    }

    public int getLastMove() {
        return moves.get(moves.size() - 1);
    }

    public boolean isValidMove(int move) {
        int dim = board.getNumRows();
        if (move > dim * dim || move < 0) {
            return false;
        }
        if (moves.contains(move)) {
            return false;
        }
        return true;
    }

    public GamePlayer getLastPlayer() {
        return lastPlayer;
    }
}
