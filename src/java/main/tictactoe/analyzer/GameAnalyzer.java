package tictactoe.analyzer;

import tictactoe.player.GamePlayer;
import tictactoe.record.MoveHistory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

/**
 * Created by hanalee on 8/2/16.
 */
public class GameAnalyzer implements StatusChecker, Scorer {

    public ArrayList<GamePlayer> getRowPlayers(MoveHistory record, int[] row) {
        ArrayList<GamePlayer> rowPlayedBy = new ArrayList<GamePlayer>();
        for (int space : row) {
            Optional<GamePlayer> isSpacePlayed = record.whoPlayedMove(space);
            if (isSpacePlayed.isPresent() &&
                    !rowPlayedBy.contains(isSpacePlayed.get())) {
                rowPlayedBy.add(isSpacePlayed.get());
            }
        }
        return rowPlayedBy;
    }

    public int getRowOccupancy(MoveHistory record, int[] row) {
        List<Integer> allMoves = record.getAllMoves();
        int occupancy = 0;
        for (int space : row) {
            if (allMoves.contains(space)) {
                occupancy++;
            }
        }
        return occupancy;
    }

    public boolean isGameWon(MoveHistory record) {
        for (int[] row : record.getBoard().getRows()) {
            if (getRowOccupancy(record, row) == 3 &&
                    getRowPlayers(record, row).size() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameTied(MoveHistory record) {
        List<Integer> allSpaces = record.getBoard().getSpaces();
        List<Integer> allMoves = record.getAllMoves();
        if (allSpaces.equals(allMoves) && !isGameWon(record)) {
            return true;
        }
        return false;
    }

    public List<Integer> getEmptySpaces(MoveHistory record) {
        List<Integer> allSpaces = record.getBoard().getSpaces();
        List<Integer> allMoves = record.getAllMoves();
        List<Integer> emptySpaces = new ArrayList<Integer>();
        for (int space : allSpaces) {
            if (!allMoves.contains(space)) {
                emptySpaces.add(space);
            }
        }
        return emptySpaces;
    }

    public GamePlayer getNextPlayer(MoveHistory record) {
        GamePlayer lastPlayer = record.getLastPlayer();
        if (record.getPlayerOne().equals(lastPlayer)) {
            return record.getPlayerTwo();
        }
        return record.getPlayerOne();
    }

    public int scoreMove(MoveHistory record,
                         int emptySpace,
                         GamePlayer player) {
        MoveHistory nextGameState = record.copyRecord();
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

    public Hashtable<Integer, Integer> scoreNextMoves(MoveHistory record) {
        Hashtable<Integer, Integer> scores = new Hashtable<Integer, Integer>();
        List<Integer> emptySpaces = getEmptySpaces(record);
        GamePlayer nextPlayer = getNextPlayer(record);
        for (int emptySpace : emptySpaces) {
            scores.put(emptySpace, scoreMove(record, emptySpace, nextPlayer));
        }
        return scores;
    }

}