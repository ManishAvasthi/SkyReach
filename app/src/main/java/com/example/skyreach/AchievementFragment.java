package com.example.skyreach;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.data.DataRewinder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AchievementFragment extends Fragment {
   private ArrayList<Achievements_List_item> item;
   private DatabaseReference db;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_achievement,container,false);
        ScrollTextView textView = v.findViewById(R.id.text_achievement);
        textView.setHorizontallyScrolling(true);
        textView.setText("Achievements");
        textView.startScroll();
        RecyclerView recyclerView=v.findViewById(R.id.Recycler_Achievements);
        db= FirebaseDatabase.getInstance().getReference();
        item= new ArrayList<>();
        final AchievementsAdapter achievementsAdapter=new AchievementsAdapter(item);
//        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(achievementsAdapter);
        db.child("Admin").child("Achievements").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    item.clear();
                    for (DataSnapshot data :dataSnapshot.getChildren()){
                        Achievements_List_item ach=new Achievements_List_item();
                        ach.setImgurl(data.child("url").getValue().toString());
                        ach.setDesc(data.child("desc").getValue().toString());
                        item.add(ach);
                    }
                    achievementsAdapter.update(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        achievementsAdapter.update(item);
        achievementsAdapter.notifyDataSetChanged();
        return v;
    }
}
