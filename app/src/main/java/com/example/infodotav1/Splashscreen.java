package com.example.infodotav1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splashscreen extends AppCompatActivity {

    private int wkt=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan pindah ke HomeActivity

                Intent home = new Intent(Splashscreen.this, MainActivity.class);

                startActivity(home);
                finish();
            }
        }, wkt);
    }
}
