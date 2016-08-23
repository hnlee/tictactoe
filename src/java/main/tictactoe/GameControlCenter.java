package tictactoe;

import tictactoe.player.GamePlayer;
import tictactoe.record.MoveHistory;
import tictactoe.analyzer.StatusChecker;
import tictactoe.ui.GameUI;

public class GameControlCenter {
    private GameUI ui;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private MoveHistory record;
    private StatusChecker analyzer;
    private boolean isPlaying;
    private int moveNumber;

    GameControlCenter(GameUI ui,
                      MoveHistory record,
                      StatusChecker analyzer) {
        this.ui = ui;
        this.record = record;
        this.playerOne = record.getPlayerOne();
        this.playerTwo = record.getPlayerTwo();
        this.analyzer = analyzer;
        this.moveNumber = 0;
    }

    public void start() {
        ui.displayTitle();
        ui.displayBoard(record);
    }

    public GamePlayer getPlayerOne() { return playerOne; }

    public GamePlayer getPlayerTwo() { return playerTwo; }

    public MoveHistory getRecord() {
        return record;
    }

    public StatusChecker getAnalyzer() { return analyzer; }

    public void makeMove(GamePlayer currentPlayer) {
        currentPlayer.move(record);
        ui.displayMoveNumber(moveNumber);
        ui.displayBoard(record);
    }

    public void analyzeBoard() {
        if (analyzer.isGameWon(record)) {
            isPlaying = false;
            ui.displayWin(record.getLastPlayer());
        } else {
            if (analyzer.isGameTied(record)) {
                isPlaying = false;
                ui.displayTie();
            } else {
                isPlaying = true;
            }
        }
    }

    public void run() {
        GamePlayer currentPlayer;
        analyzeBoard();
        while (isPlaying) {
            if (moveNumber % 2 == 0) {
                currentPlayer = playerOne;
            } else {
                currentPlayer = playerTwo;
            }
            makeMove(currentPlayer);
            analyzeBoard();
            moveNumber++;
        }
        ui.displayEnding();
    }
}
