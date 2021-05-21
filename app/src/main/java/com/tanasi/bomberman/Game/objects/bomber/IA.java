package com.tanasi.bomberman.Game.objects.bomber;

import android.os.Handler;

import com.tanasi.bomberman.Utils.Functions;
import com.tanasi.bomberman.Game.Game;

public class IA extends Bomber {

    public IA(Game game, int x, int y) {
        super(game, x, y);
    }

    @Override
    public void update() {
        detectMove();
    }

    @Override
    public int getPower() {
        return 2;
    }


    private void detectMove() {
        int direction = Functions.getRandom(0, 4);

        switch (direction)  {
            case 0: // Haut
                move(x, y+1);
                break;
            case 1: // Bas
                move(x, y-1);
                break;
            case 2: // Gauche
                move(x-1, y);
                break;
            case 3: // Droite
                move(x+1, y);
                break;

            case 4: // Bombe
                putBomb();
                break;

            default: // Rien
                break;
        }
    }
}
