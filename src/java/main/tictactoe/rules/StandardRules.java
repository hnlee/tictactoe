package tictactoe.rules;

import tictactoe.board.Board;
import tictactoe.record.MoveHistory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Created by hanalee on 8/25/16.
 */
public class StandardRules implements StatusChecker {

    private Board board;

    public StandardRules(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public List<Integer> getSpaces() {
        return board.getSpaces();
    }

    public boolean isGameWon(MoveHistory record) {
        int numRows = board.getNumRows();
        boolean hasWinningRow = Arrays.stream(board.getRows())
                .anyMatch((row) -> getRowOccupancy(record, row) == numRows &&
                        getRowPlayers(record, row).size() == 1);
        return hasWinningRow;
    }

    public boolean isGameTied(MoveHistory record) {
        List<Integer> allSpaces = board.getSpaces();
        List<Integer> allMoves = record.getAllMoves();
        return allSpaces.equals(allMoves) && !isGameWon(record);
    }

    public int getRowOccupancy(MoveHistory record, int[] row) {
        List<Integer> allMoves = record.getAllMoves();
        IntStream isOccupied = Arrays.stream(row)
                .map((space) -> allMoves.contains(space) ? 1 : 0);
        return isOccupied.sum();
    }

    public List<Integer> getRowPlayers(MoveHistory record, int[] row) {
        List<Integer> rowPlayedBy = new ArrayList<Integer>();
        for (int space : row) {
            Optional<Integer> isSpacePlayed = record.whoPlayedMove(space);
            if (isSpacePlayed.isPresent() &&
                    !rowPlayedBy.contains(isSpacePlayed.get())) {
                rowPlayedBy.add(isSpacePlayed.get());
            }
        }
        return rowPlayedBy;
    }

}
