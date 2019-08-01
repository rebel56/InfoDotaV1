package com.example.infodotav1.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.infodotav1.Interface.ItemClickListener;
import com.example.infodotav1.Menu.Detail.DetailMenuHero;
import com.example.infodotav1.Model.Hero;
import com.example.infodotav1.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MenuHero extends AppCompatActivity {




    RecyclerView recyclerView;
    DatabaseReference mDatabase;

    FirebaseRecyclerAdapter<Hero, HeroViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_hero);


        mDatabase = FirebaseDatabase.getInstance().getReference("hero");
        mDatabase.keepSynced(true);

        recyclerView = findViewById(R.id.hero_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));




    }


    @Override
    protected void onStart() {
        super.onStart();

        adapter = new FirebaseRecyclerAdapter<Hero, HeroViewHolder>(
                Hero.class,
                R.layout.activity_menu_isi,
                HeroViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(HeroViewHolder heroViewHolder, Hero hero, final int position) {
                heroViewHolder.setDetails(getApplicationContext(), hero.getTitle_hero(), hero.getDesc_hero(),
                        hero.getImage_hero());

                heroViewHolder.cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MenuHero.this, DetailMenuHero.class);
                        intent.putExtra("HeroId", adapter.getRef(position).getKey());
                        startActivity(intent);

                    }

                });


            }
        };
        recyclerView.setAdapter(adapter);
    }



    public static class HeroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view, cardview;
        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public HeroViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(this);
            cardview = itemView.findViewById(R.id.hero_cardview);
        }

        public void setDetails(Context ctx, String title, String deskripsi, String image) {

            TextView txt_title = view.findViewById(R.id.hero_title);

            ImageView img = view.findViewById(R.id.hero_image);

            txt_title.setText(title);

            Glide.with(ctx).load(image)
                    .into(img);


        }

        @Override
        public void onClick(View view) {
            itemClickListener.onCLick(view, getAdapterPosition());
        }
    }

}