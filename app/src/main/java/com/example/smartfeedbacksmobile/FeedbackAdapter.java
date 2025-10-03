package com.example.smartfeedbacksmobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class FeedbackAdapter extends BaseAdapter {
    private Context context;
    private List<Feedback> feedbackList;

    public FeedbackAdapter(Context context, List<Feedback> feedbackList) {
        this.context = context;
        this.feedbackList = feedbackList;
    }

    @Override
    public int getCount() {
        return feedbackList.size();
    }

    @Override
    public Object getItem(int position) {
        return feedbackList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Feedback feedback = feedbackList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.feedback_item, parent, false);
        }

        TextView tvFormName = convertView.findViewById(R.id.tvFormName);
        TextView tvUser = convertView.findViewById(R.id.tvUser);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvUrl = convertView.findViewById(R.id.tvUrl);
        Button btnVer = convertView.findViewById(R.id.btnVerFeedback);

        tvFormName.setText("Form: " + feedback.getFormName());
        tvUser.setText("Usuário: " + feedback.getUser());
        tvDate.setText("Data: " + feedback.getDate());
        tvUrl.setText("URL: " + feedback.getUrl());

        btnVer.setOnClickListener(v -> {
            Intent intent = new Intent(context, FeedbackDetailActivity.class);
            intent.putExtra("hash", feedback.getHash());
            context.startActivity(intent);
        });


        return convertView;
    }
}
