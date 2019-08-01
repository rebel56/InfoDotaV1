package com.example.infodotav1.Menu.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.infodotav1.Model.Hero;
import com.example.infodotav1.Model.Skill;
import com.example.infodotav1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailMenuSkill extends AppCompatActivity {

    private TextView txt_title,txt_deskripsi;
    private ImageView imageView;
    private FirebaseDatabase mFireDatabase;
    private DatabaseReference mDatabase;

    private String skillId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu_skill);

        mFireDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFireDatabase.getReference("skill");

        txt_title = findViewById(R.id.detail_deskripsi_skill);
        txt_deskripsi = findViewById(R.id.detail_deskripsi_skill);
        imageView = findViewById(R.id.detail_img_skill);


        if (getIntent() != null)
            skillId = getIntent().getStringExtra("SkillId");
        if (!skillId.isEmpty()){
            getDetailItem(skillId);
        }
    }
    private void getDetailItem(String Itemid) {
        mDatabase.child(skillId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                final Skill skill = dataSnapshot.getValue(Skill.class);
                txt_title.setText(skill.getTitle());
                txt_deskripsi.setText(skill.getDesc());

                Glide.with(getBaseContext()).load(skill.getImage())
                        .into(imageView);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });

    }
}
