package com.nosto.fun.game1.player;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;

import java.util.List;

/**
 * @author vladimir.tikhomirov
 */
public interface StrategyService {

    ArenaPosition chooseGoodAttackingMove(List<ArenaPosition> currentPlayerMoveList, Piece[][] board, Piece myPiece);
}
