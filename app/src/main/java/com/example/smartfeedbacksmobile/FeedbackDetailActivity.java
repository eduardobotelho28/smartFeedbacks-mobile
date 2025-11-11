package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackDetailActivity extends AppCompatActivity {

    private TextView tvFeedbackUser, tvNpsDetail, tvCsatDetail, tvCliDetail, tvCesDetail,
            tvStarsDetail, tvExitSurveyDetail, tvQuestion1, tvAnswer1, tvQuestion2, tvAnswer2;

    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_detail);

        // Bind
        tvFeedbackUser   = findViewById(R.id.tvFeedbackUser);
        tvNpsDetail      = findViewById(R.id.tvNpsDetail);
        tvCsatDetail     = findViewById(R.id.tvCsatDetail);
        tvCliDetail      = findViewById(R.id.tvCliDetail);
        tvCesDetail      = findViewById(R.id.tvCesDetail);
        tvStarsDetail    = findViewById(R.id.tvStarsDetail);
        tvExitSurveyDetail = findViewById(R.id.tvExitSurveyDetail);
        tvQuestion1      = findViewById(R.id.tvQuestion1);
        tvAnswer1        = findViewById(R.id.tvAnswer1);
        tvQuestion2      = findViewById(R.id.tvQuestion2);
        tvAnswer2        = findViewById(R.id.tvAnswer2);

        btnVoltar = findViewById(R.id.btnVoltar);

        // Pega o hash
        String hash = getIntent().getStringExtra("hash");

        // Token
        SharedPreferences prefs = getSharedPreferences("SmartPrefs", MODE_PRIVATE);
        String token = prefs.getString("token", "");

        // Chamada API
        Call<FeedbackDetailResponse> call = RetrofitClient.getInstance()
                .getMyApi()
                .getFeedbackDetail("Bearer " + token, hash);

        call.enqueue(new Callback<FeedbackDetailResponse>() {
            @Override
            public void onResponse(Call<FeedbackDetailResponse> call, Response<FeedbackDetailResponse> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(FeedbackDetailActivity.this,
                            "Erro ao carregar detalhes", Toast.LENGTH_SHORT).show();
                    return;
                }

                FeedbackDetailResponse resp = response.body();

                if (!"ok".equals(resp.getStatus())) {
                    Toast.makeText(FeedbackDetailActivity.this,
                            "Retorno inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                FeedbackDetailData data = resp.getData();

                tvFeedbackUser.setText("Feedback do usuário");

                // =============== TRATAR MÉTRICAS ===============
                setMetric(tvNpsDetail,      "NPS: ",          data.getNps());
                setMetric(tvCsatDetail,     "CSAT: ",         data.getCsat());
                setMetric(tvCliDetail,      "CLI: ",          data.getCli());
                setMetric(tvCesDetail,      "CES: ",          data.getCes());
                setMetric(tvStarsDetail,    "Estrelas: ",     data.getSimpleStar());
                setMetric(tvExitSurveyDetail,"Exit Survey: ", data.getExitSurvey());

                // =============== PERGUNTAS ABERTAS ===============
                tvQuestion1.setText("Pergunta 1:");
                tvAnswer1.setText("Resposta: " +
                        (data.getFreeQuestion1() == null ? "Não respondida" : data.getFreeQuestion1()));

                tvQuestion2.setText("Pergunta 2:");
                tvAnswer2.setText("Resposta: " +
                        (data.getFreeQuestion2() == null ? "Não respondida" : data.getFreeQuestion2()));
            }

            @Override
            public void onFailure(Call<FeedbackDetailResponse> call, Throwable t) {
                Toast.makeText(FeedbackDetailActivity.this,
                        "Erro de rede: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Botão voltar
        btnVoltar.setOnClickListener(v -> finish());
    }

    /**
     * Exibe ou esconde métricas que vierem como "0"
     */
    private void setMetric(TextView textView, String label, String value) {
        if (value == null || value.equals("0")) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(label + value);
        }
    }
}
