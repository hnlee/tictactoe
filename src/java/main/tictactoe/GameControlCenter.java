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
    private int moveNumber;


    GameControlCenter() {
        this.ui = new CommandLineUI();
        this.status = "start";
    }

    GameControlCenter(GameUI ui) {
        this.ui = ui;
        this.status = "start";
    }

    public void setUp() {
        playerOne = new HumanPlayer(new StringMarker("X"));
        playerTwo = new ComputerPlayer(new StringMarker("O"));
        board = new GameBoard(3);
        record = new GameRecord(getBoard());
        analyzer = new GameAnalyzer(playerOne, playerTwo);
        status = "ready";
        moveNumber = 0;
        ui.displayMessage("Tic Tac Toe");
        ui.displayBoard(record);
    }

    public void setUp(int numRows,
                      GamePlayer firstPlayer,
                      GamePlayer secondPlayer) {
        playerOne = firstPlayer;
        playerTwo = secondPlayer;
        board = new GameBoard(numRows);
        record = new GameRecord(getBoard());
        analyzer = new GameAnalyzer(playerOne, playerTwo);
        status = "ready";
        moveNumber = 0;
        ui.displayTitle();
        ui.displayBoard(record);
    }

    public GameUI getUI() {
        return ui;
    }

    public GameBoard getBoard() {
        return board;
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
                ui.displayMoveNumber(moveNumber);
                ui.displayBoard(record);
            }
        }
    }

    public String getStatus() {
        return status;
    }

    public void analyzeBoard() {
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
        ui.displayEnding();
    }
}
