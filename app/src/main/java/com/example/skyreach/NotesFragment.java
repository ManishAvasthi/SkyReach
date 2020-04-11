package com.example.skyreach;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class NotesFragment extends Fragment {
    ArrayList<Notes_List_Item> item;
    DatabaseReference db;
    NotesAdapter notesAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_notes,container,false);
        RecyclerView recyclerView=v.findViewById(R.id.Recycler_Notes);
       item=new ArrayList<>();
        db= FirebaseDatabase.getInstance().getReference();
        notesAdapter=new NotesAdapter(item,getContext());
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(notesAdapter);
        db.child("Admin").child("Links").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    item.clear();
                    for (DataSnapshot data :dataSnapshot.getChildren()){
                        Notes_List_Item nt=new Notes_List_Item();
                        nt.setStandard(Objects.requireNonNull(data.getKey().toString()));
                      item.add(nt);

                    }
                    notesAdapter.update(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }
}
