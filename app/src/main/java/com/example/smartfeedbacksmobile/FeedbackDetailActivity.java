package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FeedbackDetailActivity extends AppCompatActivity {

    private TextView tvFeedbackUser, tvNpsDetail, tvCsatDetail, tvQuestion1, tvAnswer1, tvQuestion2, tvAnswer2;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_detail);

        // Bind
        tvFeedbackUser = findViewById(R.id.tvFeedbackUser);
        tvNpsDetail = findViewById(R.id.tvNpsDetail);
        tvCsatDetail = findViewById(R.id.tvCsatDetail);
        tvQuestion1 = findViewById(R.id.tvQuestion1);
        tvAnswer1 = findViewById(R.id.tvAnswer1);
        tvQuestion2 = findViewById(R.id.tvQuestion2);
        tvAnswer2 = findViewById(R.id.tvAnswer2);
        btnVoltar = findViewById(R.id.btnVoltar);

        // Pegando o hash (futuro uso na API)
        String hash = getIntent().getStringExtra("hash");

        // Mock dos dados
        tvFeedbackUser.setText("Feedback do usuário: Anônimo");
        tvNpsDetail.setText("NPS: 75");
        tvCsatDetail.setText("CSAT: 90%");
        tvQuestion1.setText("Pergunta 1: O atendimento foi satisfatório?");
        tvAnswer1.setText("Resposta: Sim, foi ótimo!");
        tvQuestion2.setText("Pergunta 2: Você recomendaria nosso serviço?");
        tvAnswer2.setText("Resposta: Sim, com certeza!");

        // Botão voltar
        btnVoltar.setOnClickListener(v -> finish());
    }
}
