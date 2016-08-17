package tictactoe;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class GameControlCenter {
    private GameUI ui;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private MoveHistory record;
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
        playerOne = new HumanPlayer(new StringMarker("X"), ui);
        playerTwo = new ComputerPlayer(new StringMarker("O"));
        record = new GameRecord(new SquareBoard(3));
        analyzer = new GameAnalyzer(playerOne, playerTwo);
        status = "ready";
        moveNumber = 0;
        ui.displayMessage("Tic Tac Toe");
        ui.displayBoard(record);
    }

    public void setUp(Board board,
                      GamePlayer firstPlayer,
                      GamePlayer secondPlayer) {
        playerOne = firstPlayer;
        playerTwo = secondPlayer;
        record = new GameRecord(board);
        analyzer = new GameAnalyzer(playerOne, playerTwo);
        status = "ready";
        moveNumber = 0;
        ui.displayTitle();
        ui.displayBoard(record);
    }

    public GameUI getUI() {
        return ui;
    }

    public GamePlayer getPlayer(int playerNum) {
        if (playerNum == 1) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    public MoveHistory getRecord() {
        return record;
    }

    public GameAnalyzer getAnalyzer() { return analyzer; }

    public int getMove(GamePlayer player) {
        if (player instanceof ComputerPlayer) {
            return player.move(analyzer, record);
        }
        if (player instanceof HumanPlayer) {
            return player.move();
        }
        return player.move(record.getBoard().getNumRows());
    }

    public void updateMove(GamePlayer player) {
        boolean validate = false;
        while (!validate) {
            int move = getMove(player);
            validate = analyzer.isValidMove(move, record);
            if (validate) {
                record.newMove(move, player);
                ui.displayMoveNumber(moveNumber);
                ui.displayBoard(record);
            } else {
                ui.displayError("invalid");
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
