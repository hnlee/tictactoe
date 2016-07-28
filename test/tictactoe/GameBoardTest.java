package tictactoe;

import static org.junit.Assert.*;
import org.junit.Test;
//import org.junit.Before;

public class GameBoardTest {

//    private GameBoard board;
//
//    @Before
//    public void setUp() {
//        GameBoard board = new GameBoard();
//    }

    @Test
    public void testCreateTwoByTwoBoard() {
        GameBoard board = new GameBoard(2);
        assertNotNull(board);
        assertEquals(2, board.getNumRows());
        assertEquals(4, board.getSpaces().length);
    }

    @Test
    public void testCreateThreeByThreeBoard() {
        GameBoard board = new GameBoard();
        assertEquals(3, board.getNumRows());
        assertEquals(9, board.getSpaces().length);
    }


//    @Test
//    public void testCreateGameBoard() {
//        int empty = 0;
//        for (String space : board.getBoard()) {
//            if (space.equals("")) {
//                empty++;
//            }
//        }
//        assertEquals(9, empty);
//    }
//
//    @Test
//    public void testGetSpace() {
//        assertEquals("", board.getSpace(1));
//    }
//
//    @Test
//    public void testMove() {
//        board.move(1, "X");
//        assertEquals("X", board.getSpace(1));
//    }
//
//    @Test
//    public void testGetBoardNumRows() {
//        assertEquals(3, board.getNumRows());
//    }
//
//    @Test
//    public void testGetBoardRows() {
//        int[][] rows = {{0, 1, 2}, {3, 4, 5},
//                        {6, 7, 8}, {0, 3, 6},
//                        {1, 4, 7}, {2, 5, 8},
//                        {0, 4, 8}, {2, 4, 6}};
//        assertArrayEquals(rows, board.getRows());
//    }
    


}
