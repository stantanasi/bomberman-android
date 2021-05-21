package com.tanasi.bomberman.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tanasi.bomberman.R;
import com.tanasi.bomberman.View.BombermanView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BombermanView bombermanView = findViewById(R.id.bombermanView);
    }
}
