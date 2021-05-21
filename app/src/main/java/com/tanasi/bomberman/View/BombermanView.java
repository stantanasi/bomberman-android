package com.tanasi.bomberman.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.tanasi.bomberman.Game.Game;

public class BombermanView extends View {

    private Game game;

    private int ETAT = 0;
    private static final int TITLE_SCREEN = 0;
    private static final int GAME = 1;
    private static final int END_GAME = 2;


    public BombermanView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 100);
                invalidate();
            }
        }, 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        toGraphic(canvas);
    }


    private void toGraphic(Canvas canvas) {
        switch (ETAT) {
            case TITLE_SCREEN:
                game = new Game(13, 13);
                if (true)
                    ETAT = GAME;
                break;

            case GAME:
                game.update();
                game.render(canvas, getWidth(), getHeight());

                if (game.getBombers().size() <= 1) {
                    ETAT = END_GAME;
                }
                break;

            case END_GAME:
                ETAT = TITLE_SCREEN;
                break;
        }
    }
}
