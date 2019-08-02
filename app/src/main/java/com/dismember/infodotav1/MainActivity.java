package com.dismember.infodotav1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.dismember.infodotav1.Interface.ItemClickListener;
import com.dismember.infodotav1.Menu.Detail.DetailMenuMainNews;
import com.dismember.infodotav1.Menu.MenuHero;
import com.dismember.infodotav1.Menu.MenuItem;
import com.dismember.infodotav1.Menu.MenuSkill;
import com.dismember.infodotav1.Menu.MenuUpdate;
import com.dismember.infodotav1.Model.Main;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView cvHero, cvItem, cvSkill, cvUpdate;

    RecyclerView recyclerView;
    DatabaseReference mDatabase;

    FirebaseRecyclerAdapter<Main, MainViewHolder> adapter;



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


       //firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("news");
        mDatabase.keepSynced(true);
        //recycle kanan
        recyclerView = findViewById(R.id.main_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new FirebaseRecyclerAdapter<Main, MainViewHolder>(
                Main.class,
                R.layout.activity_main_isi,
                MainViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(MainViewHolder mainViewHolder, Main main, final int position) {

                mainViewHolder.setDetails(getApplicationContext(), main.getTitle(), main.getDesc(),
                        main.getImage());

                mainViewHolder.cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DetailMenuMainNews.class);
                        intent.putExtra("MainId", adapter.getRef(position).getKey());
                        startActivity(intent);

                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }


    public static class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View view, cardview;
        private ItemClickListener itemClickListener;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(this);
            cardview = itemView.findViewById(R.id.main_cardview);
        }

        public void setDetails(Context ctx, String title, String deskripsi, String image) {

            TextView txt_title = view.findViewById(R.id.main_title);

            ImageView img = view.findViewById(R.id.main_image);

            txt_title.setText(title);

            Picasso.with(ctx).load(image)
                    .fit()
                    .into(img);


        }

        @Override
        public void onClick(View view) {
            itemClickListener.onCLick(view, getAdapterPosition());
        }
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

