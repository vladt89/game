package com.nosto.fun.game1.player;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author vladimir.tikhomirov
 */
public class DefenceServiceImplTest {

    DefenceService defenceService = new DefenceServiceImpl();

    /**
     * Tests {@link DefenceService#defendRows(List, Piece[][], Piece)} when the opponent has "open three" in some row.
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
        final ArenaPosition defendAction = defenceService.defendRows(null, board, Piece.CROSS);

        //VERIFY
        Assert.assertEquals(rowWithOpenThree, defendAction.getRow());
        Assert.assertEquals(0, defendAction.getColumn());
    }

    /**
     * Tests {@link DefenceService#defendRows(List, Piece[][], Piece)} when the opponent has "open right four" in some row.
     */
    @Test
    public void testDefendRowsWithOpenRightFour() throws Exception {
        //SETUP SUT
        final int rowWithOpenThree = 3;
        Piece[][] board = new Piece[10][10];
        board[rowWithOpenThree][0] = Piece.CROSS;
        board[rowWithOpenThree][1] = Piece.CROSS;
        board[rowWithOpenThree][2] = Piece.CROSS;
        board[rowWithOpenThree][3] = Piece.CROSS;
        board[rowWithOpenThree][4] = null; //place to defend

        //EXERCISE
        final ArenaPosition defendAction = defenceService.defendRows(null, board, Piece.ROUND);

        //VERIFY
        Assert.assertEquals(rowWithOpenThree, defendAction.getRow());
        Assert.assertEquals(4, defendAction.getColumn());
    }

    /**
     * Tests {@link DefenceService#defendRows(List, Piece[][], Piece)} when the opponent has "open left four" in some row.
     */
    @Test
    public void testDefendRowsWithOpenLeftFour() throws Exception {
        //SETUP SUT
        final int rowWithOpenThree = 4;
        Piece[][] board = new Piece[10][10];
        board[rowWithOpenThree][5] = null; //place to defend
        board[rowWithOpenThree][6] = Piece.CROSS;
        board[rowWithOpenThree][7] = Piece.CROSS;
        board[rowWithOpenThree][8] = Piece.CROSS;
        board[rowWithOpenThree][9] = Piece.CROSS;

        //EXERCISE
        final ArenaPosition defendAction = defenceService.defendRows(null, board, Piece.ROUND);

        //VERIFY
        Assert.assertEquals(rowWithOpenThree, defendAction.getRow());
        Assert.assertEquals(5, defendAction.getColumn());
    }
}