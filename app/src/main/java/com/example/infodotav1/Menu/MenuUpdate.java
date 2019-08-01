package com.example.infodotav1.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infodotav1.Interface.ItemClickListener;
import com.example.infodotav1.Menu.Detail.DetailMenuSkill;
import com.example.infodotav1.Model.Skill;
import com.example.infodotav1.Model.Update;
import com.example.infodotav1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MenuUpdate extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference mDatabase;

    FirebaseRecyclerAdapter<Update, UpdateViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_update);

        mDatabase = FirebaseDatabase.getInstance().getReference("update");
        mDatabase.keepSynced(true);

        recyclerView = findViewById(R.id.update_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new FirebaseRecyclerAdapter<Update, UpdateViewHolder>(
                Update.class,
                R.layout.activity_menu_update_isi,
                UpdateViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(UpdateViewHolder updateViewHolder, Update update,final int position) {

                updateViewHolder.setDetails(getApplicationContext(), update.getTitle(), update.getDesc(),
                        update.getImage());

                updateViewHolder.cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MenuUpdate.this, DetailMenuSkill.class);
                        intent.putExtra("UpdateId", adapter.getRef(position).getKey());
                        startActivity(intent);

            }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }


    public static class UpdateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View view, cardview;
        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public UpdateViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(this);
            cardview = itemView.findViewById(R.id.update_cardview);
        }

        public void setDetails(Context ctx, String title, String deskripsi, String image) {

            TextView txt_title = view.findViewById(R.id.update_title);

            ImageView img = view.findViewById(R.id.update_image);

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
}
