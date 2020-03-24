package com.example.skyreach;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NotesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_notes,container,false);

        RecyclerView recyclerView=v.findViewById(R.id.Recycler_Notes);
        Notes_List_Item[] item=new Notes_List_Item[]{
                new Notes_List_Item("I STANDARD"),
                new Notes_List_Item("II STANDARD"),
                new Notes_List_Item("III STANDARD"),
                new Notes_List_Item("IV STANDARD"),
                new Notes_List_Item("V STANDARD"),
                new Notes_List_Item("VI STANDARD"),
                new Notes_List_Item("VII STANDARD"),
                new Notes_List_Item("VIII STANDARD"),
                new Notes_List_Item("IX STANDARD"),
                new Notes_List_Item("X STANDARD"),
                new Notes_List_Item("XI COMMERCE"),
                new Notes_List_Item("XII COMMERCE"),
                new Notes_List_Item("XI SCIENCE"),
                new Notes_List_Item("XII SCIENCE"),
        };

        NotesAdapter notesAdapter=new NotesAdapter(item);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(notesAdapter);
        notesAdapter.update(item);
        notesAdapter.notifyDataSetChanged();
        return v;
    }
}
