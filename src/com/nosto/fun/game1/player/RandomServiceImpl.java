package com.nosto.fun.game1.player;

import com.nosto.fun.game1.ArenaPosition;
import com.nosto.fun.game1.Piece;

/**
 * @author vladimir.tikhomirov
 */
public class RandomServiceImpl implements RandomService {

    @Override
    public ArenaPosition findRandomFreePlace(Piece[][] board) {
        while (true) {
            int x = (int) (Math.random() * board.length);
            int y = (int) (Math.random() * board.length);
            if (board[x][y] == null) {
                return new ArenaPosition(x, y);
            }
        }
    }

}
