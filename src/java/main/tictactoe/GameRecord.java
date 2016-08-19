package tictactoe;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Collections;
import java.util.List;

/**
 * Created by hanalee on 7/28/16.
 */
public class GameRecord implements MoveHistory {
    private Board board;
    private GamePlayer lastPlayer;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private Hashtable<GamePlayer, ArrayList<Integer>> movesByPlayer;

    GameRecord(Board board) {
        this.board = board;
        this.movesByPlayer = new Hashtable<GamePlayer, ArrayList<Integer>>();
    }

    public void setPlayers(GamePlayer firstPlayer,
                           GamePlayer secondPlayer) {
        playerOne = firstPlayer;
        playerTwo = secondPlayer;
        movesByPlayer.put(playerOne, new ArrayList<Integer>());
        movesByPlayer.put(playerTwo, new ArrayList<Integer>());
    }

    public GamePlayer getPlayer(int playerNum) {
        if (playerNum == 2) {
            return playerTwo;
        }
        return playerOne;
    }

    public Board getBoard() { return board; }

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

    private void setLastPlayer(GamePlayer lastPlayer) {
        this.lastPlayer = lastPlayer;
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
        GameRecord copy = new GameRecord(board);
        copy.setPlayers(playerOne, playerTwo);
        copy.setLastPlayer(getLastPlayer());
        Hashtable<GamePlayer,
                  ArrayList<Integer>> copyMoves = copy.getMovesByPlayer();
        for (GamePlayer player : movesByPlayer.keySet()) {
            copyMoves.put(player, (ArrayList<Integer>) movesByPlayer.get(player).clone());
        }
        return copy;
    }
}
