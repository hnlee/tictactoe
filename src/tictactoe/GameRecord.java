package tictactoe;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Collections;

/**
 * Created by hanalee on 7/28/16.
 */
public class GameRecord {
    private GameBoard board;
    private GamePlayer lastPlayer;
    private Hashtable<GamePlayer, ArrayList<Integer>> movesByPlayer;

    GameRecord(GameBoard board) {
        this.board = board;
        this.movesByPlayer = new Hashtable<GamePlayer, ArrayList<Integer>>();
    }

    public void newMove(int move, GamePlayer player) {
        lastPlayer = player;
        if (!movesByPlayer.containsKey(player)) {
            movesByPlayer.put(player, new ArrayList<Integer>());
        }
        movesByPlayer.get(player).add(move);
    }

    public int getLastMove() {
        ArrayList<Integer> lastPlayerMoves = movesByPlayer.get(lastPlayer);
        return lastPlayerMoves.get(lastPlayerMoves.size() - 1);
    }

    public boolean isValidMove(int move) {
        int dim = board.getNumRows();
        if (move > dim * dim || move < 0) {
            return false;
        }
        for (GamePlayer player : movesByPlayer.keySet()) {
            if (movesByPlayer.get(player).contains(move)) {
                return false;
            }
        }
        return true;
    }

    public GamePlayer getLastPlayer() {
        return lastPlayer;
    }

    public GamePlayer whoPlayedMove(int move) throws NullPointerException {
        for (GamePlayer player : movesByPlayer.keySet()) {
            if (movesByPlayer.get(player).contains(move)) {
                return player;
            }
        }
        return null;
    }

    public ArrayList<Integer> getAllMoves() {
        ArrayList<Integer> allMoves = new ArrayList<Integer>();
        for (GamePlayer player : movesByPlayer.keySet()) {
            allMoves.addAll(movesByPlayer.get(player));
        }
        Collections.sort(allMoves);
        return allMoves;
    }

    public Hashtable<GamePlayer, ArrayList<Integer>> getMovesByPlayer() {
        return movesByPlayer;
    }

    public GameRecord copyRecord() {
        GameRecord copy = new GameRecord(board);
        Hashtable<GamePlayer,
                  ArrayList<Integer>> copyMoves = copy.getMovesByPlayer();
        for (GamePlayer player : movesByPlayer.keySet()) {
            copyMoves.put(player, (ArrayList<Integer>) movesByPlayer.get(player).clone());
        }
        return copy;
    }
}
