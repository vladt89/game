package com.nosto.fun.game1.player;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;

/**
 * @author vladimir.tikhomirov
 */
public interface RandomService {

    ArenaPosition findRandomFreePlace(Piece[][] board);
}
