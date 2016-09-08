package tictactoe.record;

import tictactoe.board.Board;
import tictactoe.player.GamePlayer;

import java.util.*;

/**
 * Created by hanalee on 7/28/16.
 */
public class GameRecord {
    private Board board;
    private GamePlayer lastPlayer;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private Hashtable<GamePlayer, ArrayList<Integer>> movesByPlayer;

    public GameRecord(Board board, GamePlayer playerOne, GamePlayer playerTwo) {
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.movesByPlayer = new Hashtable<GamePlayer, ArrayList<Integer>>();
        movesByPlayer.put(playerOne, new ArrayList<Integer>());
        movesByPlayer.put(playerTwo, new ArrayList<Integer>());
    }

    public GamePlayer getPlayerOne() {
        return playerOne;
    }

    public GamePlayer getPlayerTwo() {
        return playerTwo;
    }

    public int getNumRows() { return board.getNumRows(); }

    public int[][] getRows() { return board.getRows(); }

    public List<Integer> getSpaces() { return board.getSpaces(); }

    public boolean isValidMove(int move) {
        int dim = board.getNumRows();
        boolean isOutOfRange = move > dim * dim - 1 || move < 0;
        boolean isOccupied = movesByPlayer.values().stream()
                .anyMatch((moveList) -> moveList.contains(move));
        return !isOutOfRange && !isOccupied;
    }

    public void newMove(int move, GamePlayer player) {
        lastPlayer = player;
        movesByPlayer.get(player).add(move);
    }

    private void setLastPlayer(GamePlayer lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    public GamePlayer getLastPlayer() {
        return lastPlayer;
    }

    public Optional<GamePlayer> whoPlayedMove(int move) {
        if (movesByPlayer.get(playerOne).contains(move)) {
            return Optional.of(playerOne);
        } else if (movesByPlayer.get(playerTwo).contains(move)) {
            return Optional.of(playerTwo);
        } else {
            return Optional.empty();
        }
    }

    public List<Integer> getAllMoves() {
        List<Integer> allMoves = new ArrayList<Integer>();
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
        GameRecord copy = new GameRecord(board, playerOne, playerTwo);
        copy.setLastPlayer(getLastPlayer());
        Hashtable<GamePlayer,
                  ArrayList<Integer>> copyMoves = copy.getMovesByPlayer();
        for (GamePlayer player : movesByPlayer.keySet()) {
            copyMoves.put(player, new ArrayList<Integer>());
            for (int move : movesByPlayer.get(player)) {
                copyMoves.get(player).add(move);
            }
        }
        return copy;
    }
}
