package com.dismember.infodotav1.Menu.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dismember.infodotav1.Model.Skill;
import com.dismember.infodotav1.Model.Update;
import com.dismember.infodotav1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailMenuUpdate extends AppCompatActivity {

    private TextView txt_title,txt_deskripsi;
    private ImageView imageView;
    private FirebaseDatabase mFireDatabase;
    private DatabaseReference mDatabase;

    private String updateId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu_update);
        mFireDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFireDatabase.getReference("update");

        txt_title = findViewById(R.id.detail_title_update);
        txt_deskripsi = findViewById(R.id.detail_deskripsi_update);
        imageView = findViewById(R.id.detail_img_update);


        if (getIntent() != null)
            updateId = getIntent().getStringExtra("UpdateId");
        if (!updateId.isEmpty()){
            getDetailItem(updateId);
        }
    }

    private void getDetailItem(String UpdateId) {
        mDatabase.child(updateId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                final Update update = dataSnapshot.getValue(Update.class);
                txt_title.setText(update.getTitle());
                txt_deskripsi.setText(update.getDesc());

                Glide.with(getBaseContext()).load(update.getImage())
                        .into(imageView);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });

    }
}
