package com.tanasi.bomberman.Game.objects.bomb;

import android.os.Handler;

import com.tanasi.bomberman.Game.Game;
import com.tanasi.bomberman.Game.objects.bomber.Bomber;
import com.tanasi.bomberman.Game.objects.tile.Brick;

public class Explosion {

    private Game game;
    private int x;
    private int y;

    public Explosion(Game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finishToExplode();
            }
        }, 1000);
    }

    public void update() {
        Brick brick = game.getBrickAt(x, y);
        if (brick != null)
            game.removeTile(brick);

        Bomb bomb = game.getBombAt(x, y);
        if (bomb != null)
            bomb.explode();

        Bomber bomber = game.getBomberAt(x, y);
        if (bomber != null)
            bomber.die();
    }

    private void finishToExplode() {
        game.removeExplosion(this);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
