package com.example.skyreach;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StaffFragment extends Fragment {
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_staff,container,false);
        CardView teachingCard=v.findViewById(R.id.teaching_card);
        CardView nonTeachingCard=v.findViewById(R.id.non_teaching_card);
        textView =v.findViewById(R.id.text_staff);
        textView.setHorizontallyScrolling(true);
        textView.setSelected(true);

        teachingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new teaching_list()).addToBackStack(null).commit();
            }
        });

        nonTeachingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new NonTeaching()).addToBackStack(null).commit();
            }
        });
        return v;

    }
}
