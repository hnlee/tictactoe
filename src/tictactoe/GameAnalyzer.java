package tictactoe;

import java.util.ArrayList;

/**
 * Created by hanalee on 8/2/16.
 */
public class GameAnalyzer {
    private GameBoard board;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;

    GameAnalyzer(GameBoard board,
                 GamePlayer playerOne,
                 GamePlayer playerTwo) {
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public boolean isRowBlocked(GameRecord record, int[] row) {
        ArrayList<GamePlayer> playedBy = new ArrayList<GamePlayer>();
        for (int space : row) {
            playedBy.add(record.whoPlayedMove(space));
        }
        if (playedBy.contains(playerOne) && playedBy.contains(playerTwo)) {
            return true;
        }
        return false;
    }

    public int getRowOccupancy(GameRecord record, int[] row) {
        ArrayList<Integer> allMoves = record.getAllMoves();
        int occupancy = 0;
        for (int space : row) {
            if (allMoves.contains(space)) {
                occupancy++;
            }
        }
        return occupancy;
    }

    public boolean isGameWon(GameRecord record) {
        for (int[] row : board.getRows()) {
            if (getRowOccupancy(record, row) == 3 && !isRowBlocked(record, row)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameTied(GameRecord record) {
        ArrayList<Integer> allSpaces = board.getSpaces();
        ArrayList<Integer> allMoves = record.getAllMoves();
        if (allSpaces.equals(allMoves)) {
            return !isGameWon(record);
        }
        return false;
    }

    public int scoreMinMax(GameRecord record, int move) {
        GameRecord nextGameState = record.copyRecord();
        GamePlayer nextPlayer;
        if (playerOne == record.getLastPlayer()) {
            nextPlayer = playerTwo;
        } else {
            nextPlayer = playerOne;
        }
        nextGameState.newMove(move, nextPlayer);
        if (isGameWon(nextGameState)) {
            return 1;
        }
        return 0;
    }
}
