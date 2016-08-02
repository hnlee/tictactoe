package tictactoe;

import java.util.ArrayList;

/**
 * Created by hanalee on 8/2/16.
 */
public class GameAnalyzer {
    private GameBoard board;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private GameRecord record;

    GameAnalyzer(GameBoard board,
                 GamePlayer playerOne,
                 GamePlayer playerTwo,
                 GameRecord record) {
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.record = record;
    }

    public boolean isRowBlocked(int[] row) {
        ArrayList<GamePlayer> playedBy = new ArrayList<GamePlayer>();
        for (int space : row) {
            playedBy.add(record.whoPlayedMove(space));
        }
        if (playedBy.contains(playerOne) && playedBy.contains(playerTwo)) {
            return true;
        }
        return false;
    }

    public int getRowOccupancy(int[] row) {
        ArrayList<Integer> allMoves = record.getAllMoves();
        int occupancy = 0;
        for (int space : row) {
            if (allMoves.contains(space)) {
                occupancy++;
            }
        }
        return occupancy;
    }
}
