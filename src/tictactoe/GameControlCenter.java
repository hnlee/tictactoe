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
    private GameAnalyzer analyzer;
    private String status;

    GameControlCenter() {
        ui = new CommandLineUI();
        status = "start";
    }

    public void setUp(int numRows) {
        setPlayers();
        board = new GameBoard(numRows);
        record = new GameRecord(getBoard());
        analyzer = new GameAnalyzer(playerOne, playerTwo);
        status = "ready";
    }

    public GameUI getUI() {
        return ui;
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

    public GameRecord getRecord() {
        return record;
    }

    public GameAnalyzer getAnalyzer() { return analyzer; }

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
        if (analyzer.isGameWon(record)) {
            status = "win";
        } else {
            if (analyzer.isGameTied(record)) {
                status = "tie";
            } else {
                status = "playing";
            }
        }
        return status;
    }
}
