package tictactoe;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class GameControlCenter {
    private GameUI ui;
    private GameBoard board;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private GameRecord record;

    GameControlCenter() {
        ui = new CommandLineUI();
    }

    public void setUp() {
        setPlayers();
        setBoard(3);
        setRecord();
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
        record = new GameRecord();
    }

    public GameRecord getRecord() {
        return record;
    }

    public int getMove(GamePlayer player) {
        return player.move();
    }


//    boolean isWon() {
//        int[][] rows = board.getRows();
//        int numRows = board.getNumRows();
//        boolean victory = false;
//
//        for (int[] row : rows) {
//            String[] markers = new String[numRows];
//            for (int i = 0; i < numRows; i++) {
//                markers[i] = board.getSpace(row[i]);
//            }
//            Set<String> unique = new HashSet<>(Arrays.asList(markers));
//            if (unique.size() == 1) {
//                victory = true;
//            }
//        }
//
//        return victory;
//    }

}
