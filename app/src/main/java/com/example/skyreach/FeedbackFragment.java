package com.example.skyreach;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackFragment extends Fragment {
    private EditText feedemail, subject, message;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);
        feedemail = v.findViewById(R.id.feed_email);
        subject = v.findViewById(R.id.feed_subject);
        message = v.findViewById(R.id.feed_message);
        Button btn_send = v.findViewById(R.id.btnFeedSend);
        ScrollTextView textView = v.findViewById(R.id.text_feedback);
        textView.setHorizontallyScrolling(true);
        textView.setText("Feedback");
        textView.startScroll();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedEmail = feedemail.getText().toString().trim();
                String Subject = subject.getText().toString().trim();
                String Message = message.getText().toString().trim();

                if (TextUtils.isEmpty(Subject)) {
                    Toast.makeText(getActivity(), "Please Write the Subject", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Message)) {
                    Toast.makeText(getActivity(), "Please Write Feedback", Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] email_display = feedEmail.split(",");
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL, email_display);
                i.putExtra(Intent.EXTRA_SUBJECT, Subject);
                i.putExtra(Intent.EXTRA_TEXT, Message);
                i.setType("message/rfc822");
                i.setPackage("com.google.android.gm");
                startActivity(i);
                onBackPressed();

            }
        });

        return v;
    }

    public void onBackPressed() {
        subject.setText("");
        message.setText("");
    }

}
