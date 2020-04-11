package com.example.skyreach;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LinksAdapter extends RecyclerView.Adapter<linkHolder> {
    private final Context context;
    private ArrayList<LinkModel> list;

    public LinksAdapter(Context context, ArrayList<LinkModel> list) {
        this.context=context;
        this.list=list;
    }
void  update(ArrayList<LinkModel> list){
        this.list=list;
        notifyDataSetChanged();
}
    @NonNull
    @Override
    public linkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.link_rec,parent,false);
        return new linkHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull linkHolder holder, final int position) {
        holder.texthead.setText(list.get(position).getLinkheading());
        holder.textbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(list.get(position).getLinkbody()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class linkHolder extends RecyclerView.ViewHolder {
    TextView texthead,textbody;
    public linkHolder(View itemview) {
        super(itemview);
        texthead=itemview.findViewById(R.id.linheading);
        textbody=itemview.findViewById(R.id.linkbody);
    }
}
class LinkModel{
    String linkheading,linkbody;

    public String getLinkheading() {
        return linkheading;
    }

    public void setLinkheading(String linkheading) {
        this.linkheading = linkheading;
    }

    public String getLinkbody() {
        return linkbody;
    }

    public void setLinkbody(String linkbody) {
        this.linkbody = linkbody;
    }
}