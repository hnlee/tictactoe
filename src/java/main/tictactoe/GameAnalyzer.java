package tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 * Created by hanalee on 8/2/16.
 */
public class GameAnalyzer {
    private GamePlayer playerOne;
    private GamePlayer playerTwo;

    GameAnalyzer(GamePlayer playerOne,
                 GamePlayer playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public ArrayList<GamePlayer> getRowPlayers(GameRecord record, int[] row) {
        ArrayList<GamePlayer> rowPlayedBy = new ArrayList<GamePlayer>();
        for (int space : row) {
            GamePlayer spacePlayedBy = record.whoPlayedMove(space);
            if (spacePlayedBy != null &&
                    !rowPlayedBy.contains(spacePlayedBy)) {
                rowPlayedBy.add(spacePlayedBy);
            }
        }
        return rowPlayedBy;
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
        for (int[] row : record.getBoard().getRows()) {
            if (getRowOccupancy(record, row) == 3 &&
                    getRowPlayers(record, row).size() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameTied(GameRecord record) {
        ArrayList<Integer> allSpaces = record.getBoard().getSpaces();
        ArrayList<Integer> allMoves = record.getAllMoves();
        if (allSpaces.equals(allMoves) && !isGameWon(record)) {
            return true;
        }
        return false;
    }

    public ArrayList<Integer> getEmptySpaces(GameRecord record) {
        ArrayList<Integer> allSpaces = record.getBoard().getSpaces();
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

    public int scoreMove(GameRecord record,
                         int emptySpace,
                         GamePlayer player) {
        GameRecord nextGameState = record.copyRecord();
        nextGameState.newMove(emptySpace, player);
        if (isGameWon(nextGameState)) {
            return 1;
        }
        if (isGameTied(nextGameState)) {
            return 0;
        }
        Hashtable<Integer, Integer> nextScores = scoreNextMoves(nextGameState);
        int nextScore = Collections.max(nextScores.values());
        return -1 * nextScore;
    }

    public Hashtable<Integer, Integer> scoreNextMoves(GameRecord record) {
        ArrayList<Integer> emptySpaces = getEmptySpaces(record);
        GamePlayer nextPlayer = getNextPlayer(record);
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        for (int emptySpace : emptySpaces) {
            scores.put(emptySpace, scoreMove(record, emptySpace, nextPlayer));
        }
        return scores;
    }

}
