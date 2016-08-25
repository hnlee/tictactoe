package tictactoe.rules;

import tictactoe.player.GamePlayer;
import tictactoe.record.MoveHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by hanalee on 8/25/16.
 */
public class StandardRules implements StatusChecker {

    public boolean isGameWon(MoveHistory record) {
        for (int[] row : record.getRows()) {
            if (getRowOccupancy(record, row) == 3 &&
                    getRowPlayers(record, row).size() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameTied(MoveHistory record) {
        List<Integer> allSpaces = record.getSpaces();
        List<Integer> allMoves = record.getAllMoves();
        if (allSpaces.equals(allMoves) && !isGameWon(record)) {
            return true;
        }
        return false;
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


}
