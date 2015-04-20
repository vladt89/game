package com.nosto.fun.game1.Vladimir;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;
import com.nosto.fun.game1.Player;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Quick hackety hack game for 5-in-a-row game. Here's an example Player
 * implementation which relies on good fortune.
 *
 * @author vladimir.tikhomirov
 */
public class StrongOpponent implements Player, Cloneable {
    private Piece myPiece;
    private String name;
    private List<ArenaPosition> myMoveQueue = new LinkedList<ArenaPosition>();
    private DefenceService defenceService = new DefenceServiceImpl();
    private StrategyService strategyService = new StrategyServiceImpl();

    public StrongOpponent(String name) {
        this.name = name;
    }

    public void setSide(Piece p) {
        myPiece = p;
    }

    public Piece getSide() {
        return myPiece;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Tries to find a good move. First check if there is a need for defending and then
     * searches for good place to attack if there is no need to defend.
     */
    public ArenaPosition move(Piece[][] board, ArenaPosition lastPosition) {
        final ArenaPosition defendRows = defenceService.defendRows(board, myPiece);
        if (defendRows != null) {
            myMoveQueue.add(defendRows);
            return defendRows;
        }
        final ArenaPosition defendColumns = defenceService.defendColumns(board, myPiece);
        if (defendColumns != null) {
            myMoveQueue.add(defendColumns);
            return defendColumns;
        }

        final ArenaPosition attackingMove = strategyService.chooseWeakAttackingMove(myMoveQueue, board);
        myMoveQueue.add(attackingMove);
        return attackingMove;
    }

    public String toString() {
        return getName();
    }

}
