package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbacksActivity extends AppCompatActivity {

    private ListView listFeedbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacks);

        listFeedbacks = findViewById(R.id.listFeedbacks);

        // ---------- PEGANDO TOKEN E USER_ID ----------
        SharedPreferences prefs = getSharedPreferences("SmartPrefs", MODE_PRIVATE);

        String token = prefs.getString("token", "");
        int userId   = prefs.getInt("user_id", -1);

        if (token.isEmpty() || userId == -1) {
            Toast.makeText(this, "Erro: usuário não autenticado.", Toast.LENGTH_LONG).show();
            return;
        }

        // ============= CHAMADA REAL PARA A API =============
        Call<FeedbackResponse> call = RetrofitClient.getInstance()
                .getMyApi()
                .getUserFeedbacks("Bearer " + token, userId);

        call.enqueue(new Callback<FeedbackResponse>() {
            @Override
            public void onResponse(Call<FeedbackResponse> call, Response<FeedbackResponse> response) {

                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(FeedbacksActivity.this,
                            "Erro ao carregar feedbacks. Código: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                FeedbackResponse resp = response.body();

                if (!"ok".equals(resp.getStatus())) {
                    Toast.makeText(FeedbacksActivity.this,
                            "Erro ao carregar feedbacks (status inválido).",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                List<FeedbackItem> lista = resp.getData();

                FeedbackAdapter adapter = new FeedbackAdapter(FeedbacksActivity.this, lista);
                listFeedbacks.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<FeedbackResponse> call, Throwable t) {
                Toast.makeText(FeedbacksActivity.this,
                        "Erro de comunicação: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
