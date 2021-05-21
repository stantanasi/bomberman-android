package com.tanasi.bomberman.Game.objects.bomb;

import android.os.Handler;

import com.tanasi.bomberman.Game.Game;
import com.tanasi.bomberman.Game.objects.bomber.Bomber;

public class Bomb {

    private Game game;
    private Bomber bomber;
    private int x;
    private int y;

    private boolean hasExploded = false;

    public Bomb(Game game, Bomber bomber) {
        this.game = game;
        this.bomber = bomber;

        x = bomber.getX();
        y = bomber.getY();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                explode();
            }
        }, 5000);
    }


    public void explode() {
        if (hasExploded)
            return;

        hasExploded = true;

        game.addExplosion(new Explosion(game, x, y));

        for (int i=x+1; i<x+1 + bomber.getPower(); i++) {
            if (game.getTileAt(i, y) == null || game.getWallAt(i, y) != null)
                break;

            game.addExplosion(new Explosion(game, i, y));
        }
        for (int i=x-1; i>x-1 - bomber.getPower(); i--) {
            if (game.getTileAt(i, y) == null || game.getWallAt(i, y) != null)
                break;

            game.addExplosion(new Explosion(game, i, y));
        }

        for (int i=y+1; i<y+1 + bomber.getPower(); i++) {
            if (game.getTileAt(x, i) == null || game.getWallAt(x, i) != null)
                break;

            game.addExplosion(new Explosion(game, x, i));
        }
        for (int i=y-1; i>y-1 - bomber.getPower(); i--) {
            if (game.getTileAt(x, i) == null || game.getWallAt(x, i) != null)
                break;

            game.addExplosion(new Explosion(game, x, i));
        }


        game.removeBomb(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
