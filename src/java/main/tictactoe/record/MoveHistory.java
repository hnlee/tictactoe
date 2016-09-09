package tictactoe.record;

import java.util.*;

/**
 * Created by hanalee on 7/28/16.
 */
public class MoveHistory {
    private Hashtable<Integer, List<Integer>> movesByPlayer;
    private int numRows;
    private int lastPlayer;

    public MoveHistory(int numRows) {
        this.movesByPlayer = new Hashtable<Integer, List<Integer>>();
        for (int player = 1; player <= 2; player++) {
            movesByPlayer.put(player, new ArrayList<Integer>());
        }
        this.numRows = numRows;
    }

    public boolean isValidMove(int move) {
        boolean isOutOfRange = move > numRows * numRows - 1 || move < 0;
        boolean isOccupied = movesByPlayer.values().stream()
                .anyMatch((moveList) -> moveList.contains(move));
        return !isOutOfRange && !isOccupied;
    }

    public void newMove(int move) {
        int player = getAllMoves().size() % 2 + 1;
        movesByPlayer.get(player).add(move);
        lastPlayer = player;
    }

    private void setLastPlayer(int lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    public int getLastPlayer() {
        return lastPlayer;
    }

    public Optional<Integer> whoPlayedMove(int move) {
        for (int player : movesByPlayer.keySet()) {
            if (movesByPlayer.get(player).contains(move)) {
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }

    public List<Integer> getAllMoves() {
        List<Integer> allMoves = new ArrayList<Integer>();
        for (int player : movesByPlayer.keySet()) {
            allMoves.addAll(movesByPlayer.get(player));
        }
        Collections.sort(allMoves);
        return allMoves;
    }

    public Hashtable<Integer, List<Integer>> getMovesByPlayer() {
        return movesByPlayer;
    }

    public MoveHistory copyRecord() {
        MoveHistory copy = new MoveHistory(numRows);
        copy.setLastPlayer(getLastPlayer());
        Hashtable<Integer, List<Integer>> copyMoves = copy.getMovesByPlayer();
        for (int player : movesByPlayer.keySet()) {
            copyMoves.put(player, new ArrayList<Integer>());
            for (int move : movesByPlayer.get(player)) {
                copyMoves.get(player).add(move);
            }
        }
        return copy;
    }
}
