package com.nosto.fun.game1.player;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;

import java.util.*;

/**
 * @author vladimir.tikhomirov
 */
public class DefenceServiceImpl extends RandomServiceImpl implements DefenceService {

    private static final List<Piece> OPEN_THREE_CROSS_LIST = new ArrayList<Piece>(
            Arrays.asList(null, Piece.CROSS, Piece.CROSS, Piece.CROSS, null));
    public static final List<Piece> OPEN_THREE_ROUND_LIST = new ArrayList<Piece>(
            Arrays.asList(null, Piece.ROUND, Piece.ROUND, Piece.ROUND, null));

    @Override
    public ArenaPosition defend(List<ArenaPosition> currentPlayerMoveList, Piece[][] board, Piece myPiece) {

        int length = board.length;
        Piece opponentPiece = myPiece == Piece.CROSS ? Piece.ROUND : Piece.CROSS;
        List<Piece> listToSearchFor = opponentPiece == Piece.ROUND ? OPEN_THREE_ROUND_LIST : OPEN_THREE_CROSS_LIST;

        for (int row = 0; row < length; row++) {
            List<Piece> values = new ArrayList<Piece>(board.length);
            Piece[] rowValues = board[row];
            Collections.addAll(values, rowValues);

            for (int i = 0; i < length - listToSearchFor.size(); i++) {
                final int toIndex = i + listToSearchFor.size();
                final List<Piece> subList = values.subList(i, toIndex);
                if (listToSearchFor.equals(subList)) {
                    if (board[row][(toIndex)] == null) { // seems no need because we know it
                        return new ArenaPosition(row, toIndex);
                    }
                    // seems will never come here
                    if (board[row][i] == null) {
                        return new ArenaPosition(row, i);
                    }
                }
            }
        }
        return findRandomFreePlace(board);
    }
}
