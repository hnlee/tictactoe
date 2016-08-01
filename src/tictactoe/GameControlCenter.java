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
        status = "ready";
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

    public String analyzeBoard() {
        ArrayList<Integer> allMoves = record.getAllMoves();
        ArrayList<Integer> spaces = board.getSpaces();
//        for (int[] row : board.getRows()) {
//            if (getRowOccupancy(row) == 3 && !isRowBlocked(row)) {
//                status = "win";
//            }
//        }
        if (!status.equals("win")) {
            if (allMoves.equals(spaces)) {
                status = "tie";
            } else {
                status = "playing";
            }
        }
        return status;
    }

    public boolean isRowBlocked(int[] row) {
        ArrayList<GamePlayer> playedBy = new ArrayList<GamePlayer>();
        for (int space : row) {
            playedBy.add(record.whoPlayedMove(space));
        }
        if (playedBy.contains(playerOne) && playedBy.contains(playerTwo)) {
            return true;
        }
        return false;
    }
//
//    public int getRowOccupancy(int[] row) {
//        ArrayList<Integer> allMoves = record.getAllMoves();
//        int occupancy = 0;
//        for (int space : row) {
//            if (allMoves.contains(space)) {
//                occupancy++;
//            }
//        }
//        return occupancy;
//    }

}
