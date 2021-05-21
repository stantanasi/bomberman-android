package com.tanasi.bomberman.Game.objects.tile;

public abstract class Tile {

    int x;
    int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
