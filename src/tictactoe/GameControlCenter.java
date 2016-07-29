package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class GameControlCenter {
    private GameUI ui;
    private GameBoard board;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private GameRecord record;
    private String status;

    GameControlCenter() {
        ui = new CommandLineUI();
        status = "start";
    }

    public void setUp() {
        setPlayers();
        setBoard(3);
        setRecord();
        status = "set up";
    }

    public void setUI() {
        ui = new CommandLineUI();
    }

    public GameUI getUI() {
        return ui;
    }

    public void setBoard(int numRows) {
        board = new GameBoard(numRows);
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setPlayers() {
        playerOne = new GamePlayer();
        playerTwo = new GamePlayer();
    }

    public GamePlayer getPlayer(int playerNum) {
        if (playerNum == 1) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    public void setRecord() {
        record = new GameRecord(getBoard());
    }

    public GameRecord getRecord() {
        return record;
    }

    public int getMove(GamePlayer player) {
        return player.move();
    }

    public void updateMove(int move, GamePlayer player) {
        if (record.isValidMove(move)) {
            record.newMove(move, player);
        }
    }

    public String getStatus() {
        return status;
    }

    public void analyzeBoard() {
        if (!status.equals("win")) {
            ArrayList<Integer> spaces = board.getSpaces();
            ArrayList<Integer> allMoves = record.getAllMoves();
            if (allMoves.equals(spaces)) {
                status = "tie";
            } else {
                status = "playing";
            }
        }
    }

    public boolean isWon() {
        if (status.equals("win")) {
            return true;
        }
        return false;
    }

    public boolean isTied() {
        if (status.equals("tie")) {
            return true;
        }
        return false;
    }

}
