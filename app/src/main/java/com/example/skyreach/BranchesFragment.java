package com.example.skyreach;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BranchesFragment extends Fragment {
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_branches, container, false);
        ScrollTextView textView = v.findViewById(R.id.text_branches);
        textView.setHorizontallyScrolling(true);
        textView.setText("Branches");
        textView.startScroll();
        return v;
    }
}
