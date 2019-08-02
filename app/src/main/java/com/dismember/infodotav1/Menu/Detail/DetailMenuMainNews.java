package com.dismember.infodotav1.Menu.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dismember.infodotav1.Model.Item;
import com.dismember.infodotav1.Model.Main;
import com.dismember.infodotav1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailMenuMainNews extends AppCompatActivity {

    private TextView txt_title, txt_deskripsi;
    private ImageView imageView;
    private FirebaseDatabase mFireDatabase;
    private DatabaseReference mDatabase;

    private String mainId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu_main_news);

        mFireDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFireDatabase.getReference("news");

        txt_title = findViewById(R.id.detail_title_main);
        txt_deskripsi = findViewById(R.id.detail_deskripsi_main);
        imageView = findViewById(R.id.detail_img_main);


        if (getIntent() != null)
            mainId = getIntent().getStringExtra("MainId");
        if (!mainId.isEmpty()) {
            getDetailNews(mainId);
        }
    }

    private void getDetailNews(String mainId) {
        mDatabase.child(mainId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                final Main main = dataSnapshot.getValue(Main.class);
                txt_title.setText(main.getTitle());
                txt_deskripsi.setText(main.getDesc());

                Glide.with(getBaseContext()).load(main.getImage())
                        .into(imageView);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });

    }
}
