package com.nosto.fun.game1.player;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;

import java.util.*;

/**
 * @author vladimir.tikhomirov
 */
public class DefenceServiceImpl extends RandomServiceImpl implements DefenceService {

    /**
     * Library of bad cases.
     * "-" - free space
     * "X" - place which is taken by some piece which is the same in the list
     */

    // case - X X X -
    private static final List<Piece> OPEN_THREE_CROSS_LIST = new ArrayList<Piece>(
            Arrays.asList(null, Piece.CROSS, Piece.CROSS, Piece.CROSS, null));
    public static final List<Piece> OPEN_THREE_ROUND_LIST = new ArrayList<Piece>(
            Arrays.asList(null, Piece.ROUND, Piece.ROUND, Piece.ROUND, null));

    // case X X X X -
    private static final List<Piece> OPEN_RIGHT_FOUR_CROSS_LIST = new ArrayList<Piece>(
            Arrays.asList(Piece.CROSS, Piece.CROSS, Piece.CROSS, Piece.CROSS, null));
    public static final List<Piece> OPEN_RIGHT_FOUR_ROUND_LIST = new ArrayList<Piece>(
            Arrays.asList(Piece.ROUND, Piece.ROUND, Piece.ROUND, Piece.ROUND, null));

    // case - X X X X
    private static final List<Piece> OPEN_LEFT_FOUR_CROSS_LIST = new ArrayList<Piece>(
            Arrays.asList(null, Piece.CROSS, Piece.CROSS, Piece.CROSS, Piece.CROSS));
    public static final List<Piece> OPEN_LEFT_FOUR_ROUND_LIST = new ArrayList<Piece>(
            Arrays.asList(null, Piece.ROUND, Piece.ROUND, Piece.ROUND, Piece.ROUND));

    // case X . X X X
    private static final List<Piece> OPEN_LEFT_MIDDLE_CROSS_LIST = new ArrayList<Piece>(
            Arrays.asList(Piece.CROSS, null, Piece.CROSS, Piece.CROSS, Piece.CROSS));
    public static final List<Piece> OPEN_LEFT_MIDDLE_ROUND_LIST = new ArrayList<Piece>(
            Arrays.asList(Piece.ROUND, null, Piece.ROUND, Piece.ROUND, Piece.ROUND));

    @Override
    public ArenaPosition defendRows(List<ArenaPosition> currentPlayerMoveList, Piece[][] board, Piece myPiece) {

        int length = board.length;
        Piece opponentPiece = myPiece == Piece.CROSS ? Piece.ROUND : Piece.CROSS;

        List<Piece> openThree = opponentPiece == Piece.ROUND ? OPEN_THREE_ROUND_LIST : OPEN_THREE_CROSS_LIST;
        List<Piece> openRightFour = opponentPiece == Piece.ROUND ? OPEN_RIGHT_FOUR_ROUND_LIST : OPEN_RIGHT_FOUR_CROSS_LIST;
        List<Piece> openLeftFour = opponentPiece == Piece.ROUND ? OPEN_LEFT_FOUR_ROUND_LIST : OPEN_LEFT_FOUR_CROSS_LIST;
        List<Piece> openLeftMiddle = opponentPiece == Piece.ROUND ? OPEN_LEFT_MIDDLE_ROUND_LIST : OPEN_LEFT_MIDDLE_CROSS_LIST;

        Set<List<Piece>> libraryOfBadCases = new HashSet<List<Piece>>();
        libraryOfBadCases.add(openThree);
        libraryOfBadCases.add(openRightFour);
        libraryOfBadCases.add(openLeftFour);
        libraryOfBadCases.add(openLeftMiddle);

        for (List<Piece> badCase : libraryOfBadCases) {
            for (int row = 0; row < length; row++) {
                Piece[] rowValues = board[row];
                final ArenaPosition defendAction = findListInRow(length, badCase, row, rowValues);
                if (defendAction != null) return defendAction;
            }
        }
        return findRandomFreePlace(board);
    }

    private ArenaPosition findListInRow(int length, List<Piece> listToSearchFor, int row, Piece[] rowValues) {
        List<Piece> values = new ArrayList<Piece>(length);
        Collections.addAll(values, rowValues);

        final int size = listToSearchFor.size();
        for (int i = 0; i < length - size; i++) {
            final int toIndex = i + size;
            final List<Piece> subList = values.subList(i, toIndex);
            if (listToSearchFor.equals(subList)) {

                return new ArenaPosition(row, toIndex - 1); //always defend from the right
            }
        }
        return null;
    }
}
