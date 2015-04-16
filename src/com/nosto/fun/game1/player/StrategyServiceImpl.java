package com.nosto.fun.game1.player;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;

import java.util.List;

/**
 * @author vladimir.tikhomirov
 */
public class StrategyServiceImpl implements StrategyService {

    @Override
    public ArenaPosition chooseGoodAttackingMove(List<ArenaPosition> currentPlayerMoveList, Piece[][] board, Piece myPiece) {

        final ArenaPosition currentPlayerLastMove;
        if (!currentPlayerMoveList.isEmpty()) {
            currentPlayerLastMove = currentPlayerMoveList.get(currentPlayerMoveList.size() - 1);

            final int lastMoveRow = currentPlayerLastMove.getRow();
            final int lastMoveColumn = currentPlayerLastMove.getColumn();

            final int moveDown = lastMoveRow + 1;
            if (moveDown < board.length) {
                if (board[moveDown][lastMoveColumn] == null) {
                    return new ArenaPosition(moveDown, lastMoveColumn);
                }
            }

            final int moveRight = lastMoveColumn + 1;
            if (moveRight < board.length) {
                if (board[lastMoveRow][moveRight] == null) {
                    return new ArenaPosition(lastMoveRow, moveRight);
                }
            }

        } else {
            final int boardCenter = board.length / 2;
            return new ArenaPosition(boardCenter, boardCenter);
        }



        return null;
    }
}
