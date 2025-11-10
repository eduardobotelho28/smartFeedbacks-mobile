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
    private List<FeedbackItem> feedbackList;

    public FeedbackAdapter(Context context, List<FeedbackItem> feedbackList) {
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
        FeedbackItem feedback = feedbackList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.feedback_item, parent, false);
        }

        TextView tvFormName = convertView.findViewById(R.id.tvFormName);
        TextView tvUser     = convertView.findViewById(R.id.tvUser);
        TextView tvDate     = convertView.findViewById(R.id.tvDate);
        TextView tvUrl      = convertView.findViewById(R.id.tvUrl);
        Button btnVer       = convertView.findViewById(R.id.btnVerFeedback);

        tvFormName.setText("Formulário: " + feedback.getFormName());
        tvUser.setText("Usuário: " + (feedback.getClientName() == null ? "Anônimo" : feedback.getClientName()));
        tvDate.setText("Data: " + feedback.getDate());
        tvUrl.setText("URL: " + feedback.getFormUrl());

        btnVer.setOnClickListener(v -> {
            Intent intent = new Intent(context, FeedbackDetailActivity.class);
            intent.putExtra("hash", feedback.getReplyHash());
            context.startActivity(intent);
        });

        return convertView;
    }
}
