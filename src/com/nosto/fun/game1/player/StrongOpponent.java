package com.nosto.fun.game1.player;

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

    public ArenaPosition move(Piece[][] board, ArenaPosition lastPosition) {
        final ArenaPosition arenaPosition = defenceService.defendRows(myMoveQueue, board, myPiece);
        myMoveQueue.add(arenaPosition);
        return arenaPosition;
    }

    public String toString() {
        return getName();
    }

}
