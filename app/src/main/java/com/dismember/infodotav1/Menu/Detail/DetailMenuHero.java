package com.dismember.infodotav1.Menu.Detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dismember.infodotav1.Model.Hero;
import com.dismember.infodotav1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailMenuHero extends AppCompatActivity {

    private TextView txt_title,txt_deskripsi;
    private ImageView imageView;
    private FirebaseDatabase mFireDatabase;
    private DatabaseReference mDatabase;

    private String heroId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu_hero);

        mFireDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFireDatabase.getReference("hero");

        txt_title = findViewById(R.id.detail_title_hero);
        txt_deskripsi = findViewById(R.id.detail_deskripsi_hero);
        imageView = findViewById(R.id.detail_img_hero);


        if (getIntent() != null)
            heroId = getIntent().getStringExtra("HeroId");
            if (!heroId.isEmpty()){
                getDetailHero(heroId);
            }

    }

    private void getDetailHero(String heroId) {
        mDatabase.child(heroId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                final Hero hero = dataSnapshot.getValue(Hero.class);
                txt_title.setText(hero.getTitle_hero());
                txt_deskripsi.setText(hero.getDesc_hero());

                Glide.with(getBaseContext()).load(hero.getImage_hero())
                        .into(imageView);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });

    }
}
