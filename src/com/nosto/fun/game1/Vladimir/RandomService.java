package com.nosto.fun.game1.Vladimir;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;

/**
 * @author vladimir.tikhomirov
 */
public interface RandomService {

    ArenaPosition findRandomFreePlace(Piece[][] board);
}
