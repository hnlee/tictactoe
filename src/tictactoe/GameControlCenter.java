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

    public void updateMove(int move) {
        record.newMove(move);
    }

}
