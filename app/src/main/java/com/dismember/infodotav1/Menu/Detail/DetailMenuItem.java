package com.dismember.infodotav1.Menu.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dismember.infodotav1.Model.Item;
import com.dismember.infodotav1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailMenuItem extends AppCompatActivity {

    private TextView txt_title,txt_deskripsi;
    private ImageView imageView;
    private FirebaseDatabase mFireDatabase;
    private DatabaseReference mDatabase;

    private String itemId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu_item);

        mFireDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFireDatabase.getReference("item");

        txt_title = findViewById(R.id.detail_title_item);
        txt_deskripsi = findViewById(R.id.detail_deskripsi_item);
        imageView = findViewById(R.id.detail_img_item);


        if (getIntent() != null)
            itemId = getIntent().getStringExtra("ItemId");
        if (!itemId.isEmpty()){
            getDetailItem(itemId);
        }
    }

    private void getDetailItem(String Itemid) {
        mDatabase.child(itemId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                final Item item = dataSnapshot.getValue(Item.class);
                txt_title.setText(item.getTitle());
                txt_deskripsi.setText(item.getDesc());

                Glide.with(getBaseContext()).load(item.getImage())
                        .into(imageView);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });

    }
}
