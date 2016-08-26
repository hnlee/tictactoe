package tictactoe;

import tictactoe.player.GamePlayer;
import tictactoe.record.MoveHistory;
import tictactoe.rules.StatusChecker;
import tictactoe.ui.GameUI;

public class GameControlCenter {
    private GameUI ui;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private MoveHistory record;
    private StatusChecker rules;
    private boolean isPlaying;
    private int moveNumber;

    GameControlCenter(GameUI ui,
                      MoveHistory record,
                      StatusChecker rules) {
        this.ui = ui;
        this.record = record;
        this.playerOne = record.getPlayerOne();
        this.playerTwo = record.getPlayerTwo();
        this.rules = rules;
        this.moveNumber = 0;
    }

    public void start() {
        ui.displayTitle();
        ui.displayBoard(record);
    }

    public MoveHistory getRecord() {
        return record;
    }

    public StatusChecker getRules() { return rules; }

    public void makeMove(GamePlayer currentPlayer) {
        currentPlayer.move(record);
        ui.displayMoveNumber(moveNumber);
        ui.displayBoard(record);
    }

    public void analyzeBoard() {
        if (rules.isGameWon(record)) {
            isPlaying = false;
            ui.displayWin(record.getLastPlayer());
        } else {
            if (rules.isGameTied(record)) {
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
