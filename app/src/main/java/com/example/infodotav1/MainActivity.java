package com.example.infodotav1;

import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.infodotav1.Menu.MenuHero;
import com.example.infodotav1.Menu.MenuItem;
import com.example.infodotav1.Menu.MenuSkill;
import com.example.infodotav1.Menu.MenuUpdate;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView cvHero, cvItem, cvSkill, cvUpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvHero = findViewById(R.id.cv_hero);
        cvItem = findViewById(R.id.cv_item);
        cvSkill = findViewById(R.id.cv_skill);
        cvUpdate = findViewById(R.id.cv_update);

        cvHero.setOnClickListener(this);
        cvItem.setOnClickListener(this);
        cvSkill.setOnClickListener(this);
        cvUpdate.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_hero:
                Intent intent = new Intent(MainActivity.this, MenuHero.class);
                startActivity(intent);
                break;
            case R.id.cv_item:
                Intent intent1 = new Intent(MainActivity.this, MenuItem.class);
                startActivity(intent1);
                break;
            case R.id.cv_skill:
                Intent intent2 = new Intent(MainActivity.this, MenuSkill.class);
                startActivity(intent2);
                break;
            case R.id.cv_update:
                Intent intent3 = new Intent(MainActivity.this, MenuUpdate.class);
                startActivity(intent3);
                break;
        }
    }
}

