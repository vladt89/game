package com.nosto.fun.game1.Vladimir;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author vladimir.tikhomirov
 */
public class DefenceServiceImplTest {

    DefenceService defenceService = new DefenceServiceImpl();

    /**
     * Tests {@link DefenceService#defendRows(Piece[][], Piece)} when the opponent has "open three" in some row.
     */
    @Test
    public void testDefendRowsWithOpenThree() throws Exception {
        //SETUP SUT
        final int rowWithOpenThree = 5;
        Piece[][] board = new Piece[10][10];
        board[rowWithOpenThree][0] = null; //place to defend
        board[rowWithOpenThree][1] = Piece.ROUND;
        board[rowWithOpenThree][2] = Piece.ROUND;
        board[rowWithOpenThree][3] = Piece.ROUND;
        board[rowWithOpenThree][4] = null;

        //EXERCISE
        final ArenaPosition defendAction = defenceService.defendRows(board, Piece.CROSS);

        //VERIFY
        Assert.assertEquals(rowWithOpenThree, defendAction.getRow());
        Assert.assertEquals(0, defendAction.getColumn());
    }

    /**
     * Tests {@link DefenceService#defendRows(Piece[][], Piece)} when the opponent has "open right four" in some row.
     */
    @Test
    public void testDefendRowsWithOpenRightFour() throws Exception {
        //SETUP SUT
        final int rowWithOpenRightFour = 3;
        Piece[][] board = new Piece[10][10];
        board[rowWithOpenRightFour][0] = Piece.CROSS;
        board[rowWithOpenRightFour][1] = Piece.CROSS;
        board[rowWithOpenRightFour][2] = Piece.CROSS;
        board[rowWithOpenRightFour][3] = Piece.CROSS;
        board[rowWithOpenRightFour][4] = null; //place to defend

        //EXERCISE
        final ArenaPosition defendAction = defenceService.defendRows(board, Piece.ROUND);

        //VERIFY
        Assert.assertEquals(rowWithOpenRightFour, defendAction.getRow());
        Assert.assertEquals(4, defendAction.getColumn());
    }

    /**
     * Tests {@link DefenceService#defendRows(Piece[][], Piece)} when the opponent has "open left four" in some row.
     */
    @Test
    public void testDefendRowsWithOpenLeftFour() throws Exception {
        //SETUP SUT
        final int rowWithOpenLeftFour = 4;
        Piece[][] board = new Piece[10][10];
        board[rowWithOpenLeftFour][5] = null; //place to defend
        board[rowWithOpenLeftFour][6] = Piece.CROSS;
        board[rowWithOpenLeftFour][7] = Piece.CROSS;
        board[rowWithOpenLeftFour][8] = Piece.CROSS;
        board[rowWithOpenLeftFour][9] = Piece.CROSS;

        //EXERCISE
        final ArenaPosition defendAction = defenceService.defendRows(board, Piece.ROUND);

        //VERIFY
        Assert.assertEquals(rowWithOpenLeftFour, defendAction.getRow());
        Assert.assertEquals(5, defendAction.getColumn());
    }

    /**
     * Tests {@link DefenceService#defendRows(Piece[][], Piece)} when the opponent has "open right four" in some column.
     */
    @Test
    public void testDefendColumnsWithOpenRightFour() throws Exception {
        //SETUP SUT
        final int columnWithOpenRightFour = 0;
        Piece[][] board = new Piece[10][10];
        board[5][columnWithOpenRightFour] = null; //place to defend
        board[6][columnWithOpenRightFour] = Piece.CROSS;
        board[7][columnWithOpenRightFour] = Piece.CROSS;
        board[8][columnWithOpenRightFour] = Piece.CROSS;
        board[9][columnWithOpenRightFour] = Piece.CROSS;

        //EXERCISE
        final ArenaPosition defendAction = defenceService.defendColumns(board, Piece.ROUND);

        //VERIFY
        Assert.assertEquals(5, defendAction.getRow());
        Assert.assertEquals(columnWithOpenRightFour, defendAction.getColumn());
    }

    /**
     * Tests {@link DefenceService#defendRows(Piece[][], Piece)} when the opponent has "open left four" in some column.
     */
    @Test
    public void testDefendColumnsWithOpenLeftFour() throws Exception {
        //SETUP SUT
        final int columnWithOpenRightFour = 3;
        Piece[][] board = new Piece[10][10];
        board[0][columnWithOpenRightFour] = Piece.CROSS;
        board[1][columnWithOpenRightFour] = Piece.CROSS;
        board[2][columnWithOpenRightFour] = Piece.CROSS;
        board[3][columnWithOpenRightFour] = Piece.CROSS;
        board[4][columnWithOpenRightFour] = null; //place to defend

        //EXERCISE
        final ArenaPosition defendAction = defenceService.defendColumns(board, Piece.ROUND);

        //VERIFY
        Assert.assertEquals(4, defendAction.getRow());
        Assert.assertEquals(columnWithOpenRightFour, defendAction.getColumn());
    }
}