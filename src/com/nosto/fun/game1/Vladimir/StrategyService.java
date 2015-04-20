package com.nosto.fun.game1.Vladimir;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;

import java.util.List;

/**
 * @author vladimir.tikhomirov
 */
public interface StrategyService {

    /**
     * Searches for a simple good position to continue winning.
     *
     * @param currentPlayerMoveList list of the positions which were made by {@link WeakOpponent}.
     * @param board current situation of the game
     * @return position where to put the new piece
     */
    ArenaPosition chooseWeakAttackingMove(List<ArenaPosition> currentPlayerMoveList, Piece[][] board);
}
