package tictactoe;

import tictactoe.board.Board;
import tictactoe.board.SquareBoard;
import tictactoe.player.ComputerPlayer;
import tictactoe.player.GamePlayer;
import tictactoe.player.HumanPlayer;
import tictactoe.rules.StandardRules;
import tictactoe.rules.StatusChecker;
import tictactoe.scoring.MinimaxScorer;
import tictactoe.scoring.Scorer;
import tictactoe.ui.CommandLineUI;
import tictactoe.ui.GameUI;

public class TicTacToe {

    public static void main(String[] args) {
        GameUI ui = new CommandLineUI();
        Board board = new SquareBoard(3);
        StatusChecker rules = new StandardRules(board);
        Scorer scorer = new MinimaxScorer(rules);
        GamePlayer playerOne = new HumanPlayer(ui);
        GamePlayer playerTwo = new ComputerPlayer(scorer);
        GameControlCenter game = new GameControlCenter(ui,
                                                       playerOne,
                                                       playerTwo,
                                                       rules);
        game.start();
        game.run();
    }

}
