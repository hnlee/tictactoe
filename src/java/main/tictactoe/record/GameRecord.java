package tictactoe.record;

import tictactoe.board.Board;
import tictactoe.player.GamePlayer;

import java.util.*;

/**
 * Created by hanalee on 7/28/16.
 */
public class GameRecord implements MoveHistory {
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

    public Board getBoard() { return board; }

    public boolean isValidMove(int move) {
        Board board = getBoard();
        int dim = board.getNumRows();
        if (move > dim * dim - 1 || move < 0) {
            return false;
        }
        for (GamePlayer player : movesByPlayer.keySet()) {
            if (movesByPlayer.get(player).contains(move)) {
                return false;
            }
        }
        return true;
    }

    public void newMove(int move, GamePlayer player) {
        lastPlayer = player;
        movesByPlayer.get(player).add(move);
    }

    public int getLastMove() {
        ArrayList<Integer> lastPlayerMoves = movesByPlayer.get(lastPlayer);
        return lastPlayerMoves.get(lastPlayerMoves.size() - 1);
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

    public MoveHistory copyRecord() {
        GameRecord copy = new GameRecord(board, playerOne, playerTwo);
        copy.setLastPlayer(getLastPlayer());
        Hashtable<GamePlayer,
                  ArrayList<Integer>> copyMoves = copy.getMovesByPlayer();
        for (GamePlayer player : movesByPlayer.keySet()) {
            copyMoves.put(player, (ArrayList<Integer>) movesByPlayer.get(player).clone());
        }
        return copy;
    }
}