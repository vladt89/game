package com.nosto.fun.game1.player;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;

import java.util.List;

/**
 * @author vladimir.tikhomirov
 */
public class StrategyServiceImpl implements StrategyService {

    @Override
    public ArenaPosition chooseWeakAttackingMove(List<ArenaPosition> currentPlayerMoveList, Piece[][] board) {

        final ArenaPosition currentPlayerLastMove;
        final int length = board.length;

        if (currentPlayerMoveList.isEmpty()) {
            final int boardCenter = length / 2;
            final ArenaPosition arenaPosition = new ArenaPosition(boardCenter, boardCenter);
            return board[boardCenter][boardCenter] == null ? arenaPosition : findRandomFreePlace(board);
        } else {
            currentPlayerLastMove = currentPlayerMoveList.get(currentPlayerMoveList.size() - 1);

            final int lastMoveRow = currentPlayerLastMove.getRow();
            final int lastMoveColumn = currentPlayerLastMove.getColumn();

            final int moveDown = lastMoveRow + 1;
            if (chooseRow(board, lastMoveColumn, moveDown)) {
                return new ArenaPosition(moveDown, lastMoveColumn);
            }

            final int moveRight = lastMoveColumn + 1;
            if (chooseColumn(board, lastMoveRow, moveRight)) {
                return new ArenaPosition(lastMoveRow, moveRight);
            }

            final int moveUp = lastMoveRow - 1;
            if (chooseRow(board, lastMoveColumn, moveUp)) {
                return new ArenaPosition(moveUp, lastMoveColumn);
            }

            final int moveLeft = lastMoveColumn - 1;
            if (chooseColumn(board, lastMoveRow, moveLeft)) {
                return new ArenaPosition(lastMoveRow, moveLeft);
            }
        }

        return findRandomFreePlace(board);
    }

    private ArenaPosition findRandomFreePlace(Piece[][] board) {
        while (true) {
            int x = (int) (Math.random() * board.length);
            int y = (int) (Math.random() * board.length);
            if (board[x][y] == null) {
                return new ArenaPosition(x, y);
            }
        }
    }

    private boolean chooseColumn(Piece[][] board, int lastMoveRow, int columnToMoveTo) {
        if (columnToMoveTo < board.length) {
            if (board[lastMoveRow][columnToMoveTo] == null) {
                return true;
            }
        }
        return false;
    }

    private boolean chooseRow(Piece[][] board, int lastMoveColumn, int rowToMoveTo) {
        if (rowToMoveTo < board.length) {
            if (board[rowToMoveTo][lastMoveColumn] == null) {
                return true;
            }
        }
        return false;
    }
}
