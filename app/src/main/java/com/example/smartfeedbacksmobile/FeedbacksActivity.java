package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class FeedbacksActivity extends AppCompatActivity {

    private ListView listFeedbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacks);

        listFeedbacks = findViewById(R.id.listFeedbacks);

        // Mock de feedbacks
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(new Feedback("Pesquisa Satisfação", "Anônimo", "2025-09-28", "abc123"));
        feedbacks.add(new Feedback("Avaliação Atendimento", "Anônimo", "2025-09-30", "def456"));
        feedbacks.add(new Feedback("Feedback Produto", "Anônimo", "2025-10-01", "ghi789"));

        FeedbackAdapter adapter = new FeedbackAdapter(this, feedbacks);
        listFeedbacks.setAdapter(adapter);
    }
}
