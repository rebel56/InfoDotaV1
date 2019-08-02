package com.dismember.infodotav1.Menu;

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
import com.dismember.infodotav1.Interface.ItemClickListener;
import com.dismember.infodotav1.Menu.Detail.DetailMenuItem;
import com.dismember.infodotav1.Menu.Detail.DetailMenuSkill;
import com.dismember.infodotav1.Model.Item;
import com.dismember.infodotav1.Model.Skill;
import com.dismember.infodotav1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MenuSkill extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference mDatabase;

    FirebaseRecyclerAdapter<Skill, SkillViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_skill);

        mDatabase = FirebaseDatabase.getInstance().getReference("skill");
        mDatabase.keepSynced(true);

        recyclerView = findViewById(R.id.skill_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new FirebaseRecyclerAdapter<Skill, SkillViewHolder>(
                Skill.class,
                R.layout.activity_menu_skill_isi,
                SkillViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(SkillViewHolder skillViewHolder, Skill skill, final int position) {
                skillViewHolder.setDetails(getApplicationContext(), skill.getTitle(), skill.getDesc(),
                        skill.getImage());

                skillViewHolder.cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MenuSkill.this, DetailMenuSkill.class);
                        intent.putExtra("SkillId", adapter.getRef(position).getKey());
                        startActivity(intent);

                    }


                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

        public static class SkillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            View view, cardview;
            private ItemClickListener itemClickListener;

            public void setItemClickListener(ItemClickListener itemClickListener) {
                this.itemClickListener = itemClickListener;
            }

            public SkillViewHolder(@NonNull View itemView) {
                super(itemView);
                view = itemView;
                view.setOnClickListener(this);
                cardview = itemView.findViewById(R.id.skill_cardview);
            }

            public void setDetails(Context ctx, String title, String deskripsi, String image) {

                TextView txt_title = view.findViewById(R.id.skill_title);

                ImageView img = view.findViewById(R.id.skill_image);

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