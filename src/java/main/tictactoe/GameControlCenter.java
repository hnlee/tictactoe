package tictactoe;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

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

    GameControlCenter(InputStream input, OutputStream output) {
        ui = new CommandLineUI(input, output);
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
        if (player instanceof HumanPlayer) {
            return player.move(ui);
        }
        return player.move(board.getNumRows());
    }

    public void updateMove(GamePlayer player) {
        boolean validate = false;
        while (!validate) {
            int move = getMove(player);
            validate = record.isValidMove(move);
            if (validate) {
                record.newMove(move, player);
            }
        }
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
            updateMove(currentPlayer);
            analyzeBoard();
            moveNumber++;
        }
        status = "finish";
    }
}
