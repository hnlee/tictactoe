package tictactoe;

import tictactoe.board.Board;
import tictactoe.board.SquareBoard;
import tictactoe.player.ComputerPlayer;
import tictactoe.player.GamePlayer;
import tictactoe.player.HumanPlayer;
import tictactoe.player.StringMarker;
import tictactoe.record.GameRecord;
import tictactoe.record.MoveHistory;
import tictactoe.rules.StandardRules;
import tictactoe.rules.StatusChecker;
import tictactoe.scoring.MinimaxScorer;
import tictactoe.scoring.Scorer;
import tictactoe.ui.CommandLineUI;
import tictactoe.ui.GameUI;

/**
 * Created by hanalee on 8/15/16.
 */
public class TicTacToe {

    public static void main(String[] args) {
        GameUI ui = new CommandLineUI();
        Board board = new SquareBoard(3);
        StatusChecker rules = new StandardRules();
        Scorer scorer = new MinimaxScorer(rules);
        GamePlayer playerOne = new HumanPlayer(new StringMarker("X"), ui);
        GamePlayer playerTwo = new ComputerPlayer(new StringMarker("O"), scorer);
        MoveHistory record = new GameRecord(board, playerOne, playerTwo);

        GameControlCenter game = new GameControlCenter(ui, record, rules);
        game.start();
        game.run();
    }

}
