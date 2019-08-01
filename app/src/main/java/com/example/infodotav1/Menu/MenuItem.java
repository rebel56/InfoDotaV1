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
import com.example.infodotav1.Menu.Detail.DetailMenuItem;
import com.example.infodotav1.Model.Item;
import com.example.infodotav1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MenuItem extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference mDatabase;

    FirebaseRecyclerAdapter<Item, ItemViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        mDatabase = FirebaseDatabase.getInstance().getReference("item");
        mDatabase.keepSynced(true);

        recyclerView = findViewById(R.id.item_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

        @Override
        protected void onStart() {
            super.onStart();

            adapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(
                    Item.class,
                    R.layout.activity_menu_item_isi,
                    ItemViewHolder.class, mDatabase) {

                @Override
                protected void populateViewHolder(ItemViewHolder itemViewHolder, Item item, final int position) {
                    itemViewHolder.setDetails(getApplicationContext(), item.getTitle(), item.getDesc(),
                            item.getImage());

                    itemViewHolder.cardview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MenuItem.this, DetailMenuItem.class);
                            intent.putExtra("ItemId", adapter.getRef(position).getKey());
                            startActivity(intent);

                        }


                    });


                }
            };
            recyclerView.setAdapter(adapter);
        }



        public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            View view, cardview;
            private ItemClickListener itemClickListener;

            public void setItemClickListener(ItemClickListener itemClickListener) {
                this.itemClickListener = itemClickListener;
            }

            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                view = itemView;
                view.setOnClickListener(this);
                cardview = itemView.findViewById(R.id.item_cardview);
            }

            public void setDetails(Context ctx, String title, String deskripsi, String image) {

                TextView txt_title = view.findViewById(R.id.item_title);

                ImageView img = view.findViewById(R.id.item_image);

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