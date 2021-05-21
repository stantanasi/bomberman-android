package com.tanasi.bomberman.Game.objects.bomber;

import com.tanasi.bomberman.Game.Game;
import com.tanasi.bomberman.Game.objects.bomb.Bomb;

public abstract class Bomber {

    Game game;
    int x;
    int y;

    Bomber(Game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    void move(int toX, int toY) {
        if (canMove(toX, toY)) {
            x = toX;
            y = toY;
        }
    }

    private boolean canMove(int toX, int toY) {
        if (game.getTileAt(toX, toY) == null)
            return false;

        if (game.getWallAt(toX, toY) != null)
            return false;

        if (game.getBrickAt(toX, toY) != null)
            return false;

        if (game.getBombAt(toX, toY) != null)
            return false;

        return true;
    }

    void putBomb() {
        if (game.getBombAt(x, y) == null)
            game.addBomb(new Bomb(game, this));
    }

    public void die() {
        game.removeBomber(this);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract int getPower();
}
