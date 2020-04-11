package com.example.skyreach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Links extends AppCompatActivity {
    DatabaseReference db;
    String action="";
    ArrayList<LinkModel> list;
    LinksAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        action= Objects.requireNonNull(getIntent().getExtras()).getString("data").toString();
        db= FirebaseDatabase.getInstance().getReference();
        RecyclerView rec=findViewById(R.id.linksrec);
        rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list=new ArrayList<>();
        adapter=new LinksAdapter(getApplicationContext(),list);
        rec.setAdapter(adapter);
        Log.d("class",action);
        db.child("Admin").child("Links").child(action).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    list.clear();
                    for (DataSnapshot datav:dataSnapshot.getChildren()){

                            if(datav.exists()) {
                                String head=datav.child("head").getValue().toString();
                                String body=datav.child("link").getValue().toString();
                                LinkModel l = new LinkModel();
                                l.setLinkheading(head);
                                l.setLinkbody(body);
//                                Log.d("databody", l.getLinkbody());
                                list.add(l);
                            }

                    }
                    adapter.update(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
