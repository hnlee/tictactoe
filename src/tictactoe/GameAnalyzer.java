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

    public ArrayList<Integer> getEmptySpaces(GameRecord record) {
        ArrayList<Integer> allSpaces = board.getSpaces();
        ArrayList<Integer> allMoves = record.getAllMoves();
        ArrayList<Integer> emptySpaces = new ArrayList<Integer>();
        for (int space : allSpaces) {
            if (!allMoves.contains(space)) {
                emptySpaces.add(space);
            }
        }
        return emptySpaces;
    }

    public GamePlayer getNextPlayer(GameRecord record) {
        GamePlayer nextPlayer;
        if (playerOne == record.getLastPlayer()) {
            nextPlayer = playerTwo;
        } else {
            nextPlayer = playerOne;
        }
        return nextPlayer;
    }

    public float scoreMove(GameRecord nextGameState,
                           int move) {
        GamePlayer nextPlayer = getNextPlayer(nextGameState);
        nextGameState.newMove(move, nextPlayer);
        float score = 0.0f;
        if (isGameWon(nextGameState)) {
            score = 1.0f;
        } else if (isGameTied(nextGameState)) {
            score = 0.0f;
        } else {
            float opponentScore = minMaxScore(nextGameState);
            ArrayList<Integer> emptySpaces = getEmptySpaces(nextGameState);
            score = -opponentScore;
        }
        return score;
    }

    public float minMaxScore(GameRecord record) {
        GameRecord nextGameState = record.copyRecord();
        ArrayList<Integer> emptySpaces = getEmptySpaces(nextGameState);
        float maxScore = 0.0f;
        for (int emptySpace : emptySpaces) {
            float score = scoreMove(nextGameState, emptySpace);
            if (maxScore < score) {
                maxScore = score;
            }
        }
        return maxScore;
    }

}
