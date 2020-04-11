package com.example.skyreach;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutusFragment extends Fragment {
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_aboutus, container, false);
//            textView.setSelected(true);
        ScrollTextView scrollText = v.findViewById(R.id.scrollText);
        scrollText.setHorizontallyScrolling(true);
        scrollText.setText("About Us");
        scrollText.startScroll();
        return v;
    }

}

