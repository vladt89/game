package com.nosto.fun.game1.Vladimir;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;

/**
 * Interface which declares the methods for defending.
 *
 * @author vladimir.tikhomirov
 */
public interface DefenceService {

    /**
     * Defends columns from the worst cases.
     *
     * @param board the current situation of the game
     * @param myPiece the piece of the current player
     * @return next place where new piece should be set, can be {@code null} which means
     * that there is no bad case in the current situation and the player can start attacking
     */
    ArenaPosition defendRows(Piece[][] board, Piece myPiece);

    /**
     * Defends columns from the worst cases.
     *
     * @param board the current situation of the game
     * @param myPiece the piece of the current player
     * @return next place where new piece should be set, can be {@code null} which means
     * that there is no bad case in the current situation and the player can start attacking
     */
    ArenaPosition defendColumns(Piece[][] board, Piece myPiece);
}
