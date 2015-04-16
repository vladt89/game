package com.nosto.fun.game1.player;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author vladimir.tikhomirov
 */
public class DefenceServiceImplTest {

    DefenceService defenceService = new DefenceServiceImpl();

    @Test
    public void testDefendWithOpenThree() throws Exception {
        //SETUP SUT
        Piece[][] board = new Piece[10][10];
        board[0][0] = null;
        board[0][1] = Piece.ROUND;
        board[0][2] = Piece.ROUND;
        board[0][3] = Piece.ROUND;
        board[0][4] = null;

        //EXERCISE
        final ArenaPosition defend = defenceService.defend(null, board, Piece.CROSS);

        //VERIFY
        Assert.assertEquals(0, defend.getRow());
        Assert.assertEquals(5, defend.getColumn());
    }
}