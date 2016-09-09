package tictactoe.ui;

import tictactoe.board.Board;
import tictactoe.player.GamePlayer;
import tictactoe.record.MoveHistory;
import tictactoe.ui.GameUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanalee on 8/15/16.
 */
public class MockUI implements GameUI {

    private List<String> inputs;
    private boolean gameStarted;
    private boolean boardDisplayed;
    private boolean moveUpdated;
    private boolean gameWon;
    private boolean gameTied;
    private boolean gameOver;
    private int errorCode;

    public MockUI() {
        gameStarted = false;
        boardDisplayed = false;
        moveUpdated = false;
        gameWon = false;
        gameTied = false;
        gameOver = false;
        errorCode = 0;
    }

    public void setInputs(List<String> inputs) {
        this.inputs = new ArrayList<String>(inputs);
    }

    public boolean isBoardDisplayed() {
        return boardDisplayed;
    }

    public void displayBoard(Board board, MoveHistory record) {
        boardDisplayed = true;
    }

    public String promptMove() {
        moveUpdated = false;
        boardDisplayed = false;
        return inputs.remove(0);
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void displayTitle() {
        gameStarted = true;
    }

    public boolean isMoveUpdated() {
        return moveUpdated;
    }

    public void displayMoveNumber(int moveNumber) {
        moveUpdated = true;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void displayWin(int player) {
        gameWon = true;
    }

    public boolean isGameTied() {
        return gameTied;
    }

    public void displayTie() {
        gameTied = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void displayEnding() {
        gameOver = true;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void displayError(int errorCode) {
        this.errorCode = errorCode;
    }

}