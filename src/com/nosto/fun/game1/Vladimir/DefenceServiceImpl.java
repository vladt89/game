package com.nosto.fun.game1.Vladimir;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;

import java.util.*;

/**
 * Class which provide the functionality for defending in Tic Tac Toe game.
 *
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

    // case X - X X X
    private static final List<Piece> OPEN_LEFT_MIDDLE_CROSS_LIST = new ArrayList<Piece>(
            Arrays.asList(Piece.CROSS, null, Piece.CROSS, Piece.CROSS, Piece.CROSS));
    public static final List<Piece> OPEN_LEFT_MIDDLE_ROUND_LIST = new ArrayList<Piece>(
            Arrays.asList(Piece.ROUND, null, Piece.ROUND, Piece.ROUND, Piece.ROUND));

    // case X X X - X
    private static final List<Piece> OPEN_RIGHT_MIDDLE_CROSS_LIST = new ArrayList<Piece>(
            Arrays.asList(Piece.CROSS, Piece.CROSS, Piece.CROSS, null, Piece.CROSS));
    public static final List<Piece> OPEN_RIGHT_MIDDLE_ROUND_LIST = new ArrayList<Piece>(
            Arrays.asList(Piece.ROUND, Piece.ROUND, Piece.ROUND, null, Piece.ROUND));

    // case X X - X X
    private static final List<Piece> OPEN_MIDDLE_CROSS_LIST = new ArrayList<Piece>(
            Arrays.asList(Piece.CROSS, Piece.CROSS, null, Piece.CROSS, Piece.CROSS));
    public static final List<Piece> OPEN_MIDDLE_ROUND_LIST = new ArrayList<Piece>(
            Arrays.asList(Piece.ROUND, Piece.ROUND, null, Piece.ROUND, Piece.ROUND));

    @Override
    public ArenaPosition defendRows(Piece[][] board, Piece myPiece) {
        for (List<Piece> badCase : createLibraryOfBadCases(myPiece)) {
            for (int row = 0; row < board.length; row++) {
                Piece[] rowValues = board[row];
                final ArenaPosition defendAction = findList(badCase, row, rowValues, true);
                if (defendAction != null) {
                    return defendAction;
                }
            }
        }
        return null;
    }

    @Override
    public ArenaPosition defendColumns(Piece[][] board, Piece myPiece) {
        int length = board.length;
        for (List<Piece> badCase : createLibraryOfBadCases(myPiece)) {
            for (int column = 0; column < length; column++) {
                Piece[] columnValues = new Piece[length];
                for (int row = 0; row < length; row++) {
                    columnValues[row] = board[row][column];
                }
                final ArenaPosition defendAction = findList(badCase, column, columnValues, false);
                if (defendAction != null) {
                    return defendAction;
                }
            }
        }
        return null;
    }

    private Collection<List<Piece>> createLibraryOfBadCases(Piece myPiece) {
        Piece opponentPiece = myPiece == Piece.CROSS ? Piece.ROUND : Piece.CROSS;
        Set<List<Piece>> libraryOfBadCases = new HashSet<List<Piece>>();
        if (opponentPiece == Piece.ROUND) {
            libraryOfBadCases.add(OPEN_THREE_ROUND_LIST);
            libraryOfBadCases.add(OPEN_RIGHT_FOUR_ROUND_LIST);
            libraryOfBadCases.add(OPEN_LEFT_FOUR_ROUND_LIST);
            libraryOfBadCases.add(OPEN_LEFT_MIDDLE_ROUND_LIST);
            libraryOfBadCases.add(OPEN_RIGHT_MIDDLE_ROUND_LIST);
            libraryOfBadCases.add(OPEN_MIDDLE_ROUND_LIST);
        } else {
            libraryOfBadCases.add(OPEN_THREE_CROSS_LIST);
            libraryOfBadCases.add(OPEN_RIGHT_FOUR_CROSS_LIST);
            libraryOfBadCases.add(OPEN_LEFT_FOUR_CROSS_LIST);
            libraryOfBadCases.add(OPEN_LEFT_MIDDLE_CROSS_LIST);
            libraryOfBadCases.add(OPEN_RIGHT_MIDDLE_CROSS_LIST);
            libraryOfBadCases.add(OPEN_MIDDLE_CROSS_LIST);
        }
        return libraryOfBadCases;
    }

    /**
     * Tries to find a list of pieces in provided set and provide a defender action position if the list was found.
     *
     * @param listToSearchFor the list of pieces which we are looking for
     * @param rowOrColumn the number of column or row where the defender action is going to be
     * @param pieces set of pieces where we are searching
     * @param forRows {@code true} if we are working for rows, {@code false} otherwise
     * @return defender action if the list was found {@code null} otherwise
     */
    private ArenaPosition findList(List<Piece> listToSearchFor, int rowOrColumn, Piece[] pieces, boolean forRows) {
        List<Piece> values = new ArrayList<Piece>(pieces.length);
        Collections.addAll(values, pieces);

        final int size = listToSearchFor.size();
        for (int i = 0; i < pieces.length - size + 1; i++) {
            final int toIndex = i + size;
            final List<Piece> subList = values.subList(i, toIndex);
            if (listToSearchFor.equals(subList)) {
                final Integer freePlaceIndex = findFreePlace(subList);
                if (freePlaceIndex != null) {
                    if (forRows) {
                        return new ArenaPosition(rowOrColumn, i + freePlaceIndex);
                    } else {
                        return new ArenaPosition(i + freePlaceIndex, rowOrColumn);
                    }
                }
            }
        }
        return null;
    }

    private Integer findFreePlace(List<Piece> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                return i;
            }
        }
        return null;
    }
}
