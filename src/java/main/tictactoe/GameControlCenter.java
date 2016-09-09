package tictactoe;

import tictactoe.board.Board;
import tictactoe.player.GamePlayer;
import tictactoe.record.MoveHistory;
import tictactoe.rules.StatusChecker;
import tictactoe.ui.GameUI;

public class GameControlCenter {
    private GameUI ui;
    private Board board;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private MoveHistory record;
    private StatusChecker rules;
    private boolean isPlaying;
    private int moveNumber;

    public GameControlCenter(GameUI ui,
                      GamePlayer playerOne,
                      GamePlayer playerTwo,
                      StatusChecker rules) {
        this.ui = ui;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.rules = rules;
        this.board = rules.getBoard();
        this.record = new MoveHistory(board.getNumRows());
        this.moveNumber = 0;
    }

    public MoveHistory getRecord() {
        return record;
    }

    public void start() {
        ui.displayTitle();
        ui.displayBoard(board, record);
    }

    public void makeMove(GamePlayer currentPlayer) {
        currentPlayer.move(record);
        ui.displayMoveNumber(moveNumber);
        ui.displayBoard(board, record);
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
