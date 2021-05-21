package com.tanasi.bomberman.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Toast;

import com.tanasi.bomberman.Utils.Functions;
import com.tanasi.bomberman.Game.objects.bomb.Bomb;
import com.tanasi.bomberman.Game.objects.bomb.Explosion;
import com.tanasi.bomberman.Game.objects.bomber.Bomber;
import com.tanasi.bomberman.Game.objects.bomber.IA;
import com.tanasi.bomberman.Game.objects.bomber.Player;
import com.tanasi.bomberman.Game.objects.tile.Brick;
import com.tanasi.bomberman.Game.objects.tile.Ground;
import com.tanasi.bomberman.Game.objects.tile.Tile;
import com.tanasi.bomberman.Game.objects.tile.Wall;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int width;
    private int height;

    private List<Tile> tiles = new ArrayList<>();
    private List<Bomber> bombers = new ArrayList<>();
    private List<Bomb> bombs = new ArrayList<>();
    private List<Explosion> explosions = new ArrayList<>(), mExplosions = new ArrayList<>();

    public Game(int width, int height) {
        // Si c'est pair on ajoute +1 pour qu'il soit impaire
        this.width = (width%2 == 0 ? width+1 : width);
        this.height = (height%2 == 0 ? height+1 : height);

        init();
    }

    private void init() {

        addBomber(new Player(this, 0, 0));
        addBomber(new IA(this, 0, height-1));
        addBomber(new IA(this, width-1, 0));
        addBomber(new IA(this, width-1, height-1));


        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {

                addTile(new Ground(x, y));

                if (y%2 == 1 && x%2 == 1)
                    addTile(new Wall(x, y));
                else {
                    if (Functions.getRandom(0, 10) < 8 && // Probabilité de 8/10 d'avoir une brique
                            // On ne met pas de briques sur ou a cote d'un perso au debut du jeu
                            getBomberAt(x, y) == null &&
                            getBomberAt(x+1, y) == null &&
                            getBomberAt(x-1, y) == null &&
                            getBomberAt(x, y+1) == null &&
                            getBomberAt(x, y-1) == null)
                        addTile(new Brick(x, y));
                }

            }
        }
    }


    public void update() {
        for (Bomber bomber : bombers)
            bomber.update();

        for (Explosion explosion : explosions)
            explosion.update();
        explosions.addAll(mExplosions);
        mExplosions = new ArrayList<>();
    }

    public void render(Canvas canvas, int viewWidth, int viewHeight) {
        Paint background_Paint = new Paint();
        background_Paint.setColor(Color.BLACK);
        background_Paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(0, 0, viewWidth, viewHeight, background_Paint);

        float SIZE_X = (float) viewWidth/width;
        float SIZE_Y = 50;


        for (Tile tile : tiles) {
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL);
            if (tile instanceof Ground)
                canvas.drawRect(
                        tile.getX()*SIZE_X,
                        tile.getY()*SIZE_Y,
                        tile.getX()*SIZE_X + SIZE_X,
                        tile.getY()*SIZE_Y + SIZE_Y,
                        paint);
        }

        for (Tile tile : tiles) {
            Paint paint1 = new Paint();
            paint1.setColor(Color.GRAY);
            paint1.setStyle(Paint.Style.FILL);
            if (tile instanceof Wall)
                canvas.drawRect(
                        tile.getX()*SIZE_X,
                        tile.getY()*SIZE_Y,
                        tile.getX()*SIZE_X + SIZE_X,
                        tile.getY()*SIZE_Y + SIZE_Y,
                        paint1);


            Paint paint2 = new Paint();
            paint2.setColor(Color.YELLOW);
            paint2.setStyle(Paint.Style.FILL);
            if (tile instanceof Brick)
                canvas.drawRect(
                        tile.getX()*SIZE_X,
                        tile.getY()*SIZE_Y,
                        tile.getX()*SIZE_X + SIZE_X,
                        tile.getY()*SIZE_Y + SIZE_Y,
                        paint2);

        }

        for (Bomb bomb : bombs) {
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(
                    bomb.getX()*SIZE_X,
                    bomb.getY()*SIZE_Y,
                    bomb.getX()*SIZE_X + SIZE_X,
                    bomb.getY()*SIZE_Y + SIZE_Y,
                    paint);

        }

        for (Explosion explosion : explosions) {
            Paint paint = new Paint();
            paint.setColor(Color.MAGENTA);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(
                    explosion.getX()*SIZE_X,
                    explosion.getY()*SIZE_Y,
                    explosion.getX()*SIZE_X + SIZE_X,
                    explosion.getY()*SIZE_Y + SIZE_Y,
                    paint);

        }

        for (Bomber bomber : bombers) {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            if (bomber instanceof IA)
                canvas.drawRect(
                        bomber.getX()*SIZE_X,
                        bomber.getY()*SIZE_Y,
                        bomber.getX()*SIZE_X + SIZE_X,
                        bomber.getY()*SIZE_Y + SIZE_Y,
                        paint);

        }

        for (Bomber bomber : bombers) {
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);
            if (bomber instanceof Player)
                canvas.drawRect(
                        bomber.getX()*SIZE_X,
                        bomber.getY()*SIZE_Y,
                        bomber.getX()*SIZE_X + SIZE_X,
                        bomber.getY()*SIZE_Y + SIZE_Y,
                        paint);


        }
    }



    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public void removeTile(Tile tile) {
        tiles.remove(tile);
    }

    public void addBomber(Bomber bomber) {
        bombers.add(bomber);
    }

    public void removeBomber(Bomber bomber) {
        bombers.remove(bomber);
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public void removeBomb(Bomb bomb) {
        bombs.remove(bomb);
    }

    public void addExplosion(Explosion explosion) {
        // Je l'ajoute dans une autre list pour éviter ConcurrentModificationException qui survient lorsque une explosion explose une bombe
        mExplosions.add(explosion);
    }

    public void removeExplosion(Explosion explosion) {
        explosions.remove(explosion);
    }


    public List<Bomber> getBombers() {
        return bombers;
    }

    public Wall getWallAt(int x, int y) {
        for (Tile tile : tiles) {
            if (tile instanceof Wall && tile.getX() == x && tile.getY() == y)
                return (Wall) tile;
        }

        return null;
    }

    public Brick getBrickAt(int x, int y) {
        for (Tile tile : tiles) {
            if (tile instanceof Brick && tile.getX() == x && tile.getY() == y)
                return (Brick) tile;
        }

        return null;
    }

    public Tile getTileAt(int x, int y) {
        for (Tile tile : tiles) {
            if (tile.getX() == x && tile.getY() == y)
                return tile;
        }

        return null;
    }

    public Bomber getBomberAt(int x, int y) {
        for (Bomber bomber : bombers) {
            if (bomber.getX() == x && bomber.getY() == y)
                return bomber;
        }

        return null;
    }

    public Bomb getBombAt(int x, int y) {
        for (Bomb bomb : bombs) {
            if (bomb.getX() == x && bomb.getY() == y)
                return bomb;
        }

        return null;
    }

}
