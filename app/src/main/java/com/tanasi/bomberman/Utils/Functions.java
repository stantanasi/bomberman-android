package com.tanasi.bomberman.Utils;

public class Functions {

    public static int getRandom(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
