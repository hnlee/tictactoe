package tictactoe;

public class GameControlCenter {
    private GameUI ui;
    private GamePlayer playerOne;
    private GamePlayer playerTwo;
    private MoveHistory record;
    private StatusChecker analyzer;
    private String status;
    private int moveNumber;

    GameControlCenter(GameUI ui,
                      MoveHistory record,
                      GamePlayer playerOne,
                      GamePlayer playerTwo) {
        this.ui = ui;
        this.record = record;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        record.setPlayers(playerOne, playerTwo);
        this.analyzer = new GameAnalyzer();
        this.moveNumber = 0;
        this.status = "start";
    }

    public void start() {
        ui.displayTitle();
        ui.displayBoard(record);
        this.status = "ready";
    }

    public GamePlayer getPlayerOne() { return playerOne; }

    public GamePlayer getPlayerTwo() { return playerTwo; }

    public MoveHistory getRecord() {
        return record;
    }

    public StatusChecker getAnalyzer() { return analyzer; }

    public void updateMove(GamePlayer player) {
        boolean validate = false;
        while (!validate) {
            int move = player.move(record);
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
