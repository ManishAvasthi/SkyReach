package com.example.skyreach;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.ViewHolder> {

    private ArrayList<Achievements_List_item> item;
    public AchievementsAdapter(ArrayList<Achievements_List_item> item) {
        this.item=item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_achievements__list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.desc.setText(item.get(position).getDesc());
        Glide.with(holder.itemView).load(item.get(position).getImgurl()).into(holder.imageView_image);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void update(ArrayList<Achievements_List_item> item) {
        this.item=item;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView_image;
        TextView desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_image=(ImageView) itemView.findViewById(R.id.AchievementsImages);
            desc=itemView.findViewById(R.id.a_desc);
        }

    }

}

