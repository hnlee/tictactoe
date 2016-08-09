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
        playerOne = new GamePlayer("X");
        playerTwo = new GamePlayer("O");
    }

    public void setPlayers(String[] playerTypes) {
        GamePlayer[] players = new GamePlayer[2];
        for (int index = 0; index < 2; index++) {
            if (playerTypes[index] == "Computer") {
                players[index] = new ComputerPlayer("O");
            } else if (playerTypes[index] == "Human") {
                players[index] = new HumanPlayer("X");
            } else {
                players[index] = new GamePlayer("X");
            }
        }
        playerOne = players[0];
        playerTwo = players[1];
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
        if (player instanceof ComputerPlayer) {
            return player.move(analyzer, record);
        }
        return player.move(board.getNumRows());
    }

    public boolean updateMove(int move, GamePlayer player) {
        if (record.isValidMove(move)) {
            record.newMove(move, player);
            return true;
        }
        return false;
    }

    public String getStatus() {
        return status;
    }

    public void analyzeBoard() {
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
    }

    public void run() {
        int moveNumber = 0;
        GamePlayer currentPlayer;
        analyzeBoard();
        while (status.equals("playing")) {
            if (moveNumber % 2 == 0) {
                currentPlayer = playerOne;
            } else {
                currentPlayer = playerTwo;
            }
            boolean validate = false;
            while (!validate) {
                int move = getMove(currentPlayer);
                validate = updateMove(move, currentPlayer);
            }
            analyzeBoard();
            moveNumber++;
        }
        status = "finish";
    }
}
