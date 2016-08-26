package tictactoe.rules;

import tictactoe.player.GamePlayer;
import tictactoe.record.MoveHistory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by hanalee on 8/25/16.
 */
public class StandardRules implements StatusChecker {

    public boolean isGameWon(MoveHistory record) {
        int numRows = record.getNumRows();
        boolean hasWinningRow = Arrays.stream(record.getRows())
                .anyMatch((row) -> getRowOccupancy(record, row) == numRows &&
                        getRowPlayers(record, row).size() == 1);
        return hasWinningRow;
    }

    public boolean isGameTied(MoveHistory record) {
        List<Integer> allSpaces = record.getSpaces();
        List<Integer> allMoves = record.getAllMoves();
        return allSpaces.equals(allMoves) && !isGameWon(record);
    }

    public int getRowOccupancy(MoveHistory record, int[] row) {
        List<Integer> allMoves = record.getAllMoves();
        IntStream isOccupied = Arrays.stream(row)
                .map((space) -> allMoves.contains(space) ? 1 : 0);
        return isOccupied.sum();
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
