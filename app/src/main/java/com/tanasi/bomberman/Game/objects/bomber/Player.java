package com.tanasi.bomberman.Game.objects.bomber;

import com.tanasi.bomberman.Game.Game;

public class Player extends Bomber {

    public Player(Game game, int x, int y) {
        super(game, x, y);

//        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.pacman);
//        b = Bitmap.createBitmap(b,20,705,50,65);
    }

    @Override
    public void update() {

    }

    @Override
    public int getPower() {
        return 2;
    }

    private void detectMove() {

    }
}
