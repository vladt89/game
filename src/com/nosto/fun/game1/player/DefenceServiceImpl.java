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
        for (List<Piece> badCase : createLibraryOfBadCases(myPiece)) {
            for (int row = 0; row < length; row++) {
                Piece[] rowValues = board[row];
                final ArenaPosition defendAction = findListInRow(length, badCase, row, rowValues);
                if (defendAction != null) {
                    return defendAction;
                }
            }
        }
        return null;
    }

    @Override
    public ArenaPosition defendColumns(List<ArenaPosition> myMoveQueue, Piece[][] board, Piece myPiece) {
        int length = board.length;
        for (List<Piece> badCase : createLibraryOfBadCases(myPiece)) {
            for (int column = 0; column < length; column++) {
                Piece[] columnValues = new Piece[length];
                for (int row = 0; row < length; row++) {
                    columnValues[row] = board[row][column];
                }
                final ArenaPosition defendAction = findListInColumn(length, badCase, column, columnValues);
                if (defendAction != null) {
                    return defendAction;
                }
            }
        }
        return findRandomFreePlace(board);
    }

    private Set<List<Piece>> createLibraryOfBadCases(Piece myPiece) {
        Piece opponentPiece = myPiece == Piece.CROSS ? Piece.ROUND : Piece.CROSS;
        Set<List<Piece>> libraryOfBadCases = new HashSet<List<Piece>>();
        if (opponentPiece == Piece.ROUND) {
            libraryOfBadCases.add(OPEN_THREE_ROUND_LIST);
            libraryOfBadCases.add(OPEN_RIGHT_FOUR_ROUND_LIST);
            libraryOfBadCases.add(OPEN_LEFT_FOUR_ROUND_LIST);
            libraryOfBadCases.add(OPEN_LEFT_MIDDLE_ROUND_LIST);
        } else {
            libraryOfBadCases.add(OPEN_THREE_CROSS_LIST);
            libraryOfBadCases.add(OPEN_RIGHT_FOUR_CROSS_LIST);
            libraryOfBadCases.add(OPEN_LEFT_FOUR_CROSS_LIST);
            libraryOfBadCases.add(OPEN_LEFT_MIDDLE_CROSS_LIST);
        }
        return libraryOfBadCases;
    }

    private ArenaPosition findListInRow(int length, List<Piece> listToSearchFor, int row, Piece[] rowValues) {
        List<Piece> values = new ArrayList<Piece>(length);
        Collections.addAll(values, rowValues);

        final int size = listToSearchFor.size();
        for (int i = 0; i < length - size + 1; i++) {
            final int toIndex = i + size;
            final List<Piece> subList = values.subList(i, toIndex);
            if (listToSearchFor.equals(subList)) {
                final Integer freePlaceIndex = findFreePlace(subList);
                    return new ArenaPosition(row, i + freePlaceIndex); //always defend from the right
            }
        }
        return null;
    }

    // TODO merge this method with #findListInRow
    private ArenaPosition findListInColumn(int length, List<Piece> listToSearchFor, int column, Piece[] rowValues) {
        List<Piece> values = new ArrayList<Piece>(length);
        Collections.addAll(values, rowValues);

        final int size = listToSearchFor.size();
        for (int i = 0; i < length - size + 1; i++) {
            final int toIndex = i + size;
            final List<Piece> subList = values.subList(i, toIndex);
            if (listToSearchFor.equals(subList)) {
                final Integer freePlaceIndex = findFreePlace(subList);
                return new ArenaPosition(i + freePlaceIndex, column); //always defend from the right
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
