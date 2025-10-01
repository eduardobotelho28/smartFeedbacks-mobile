package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome, tvNps, tvCsat;
    private Button btnFeedbacks, btnPerfil, btnConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Bind
        tvWelcome = findViewById(R.id.tvWelcomeHome);
        tvNps = findViewById(R.id.tvNps);
        tvCsat = findViewById(R.id.tvCsat);
        btnFeedbacks = findViewById(R.id.btnFeedbacks);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnConfig = findViewById(R.id.btnConfig);

        // Pegando nome mockado passado pela Main
        String nomeUsuario = getIntent().getStringExtra("nomeUsuario");
        tvWelcome.setText("Bem-vindo, " + nomeUsuario + "!");

        // Mock de métricas
        tvNps.setText("NPS Atual: 72");
        tvCsat.setText("CSAT Atual: 89%");

        // Por enquanto só mocks de navegação futura
        btnFeedbacks.setOnClickListener(v ->
                tvWelcome.setText("Em breve: Tela de Feedbacks!")
        );

        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, UserActivity.class);
            startActivity(intent);
        });


        btnConfig.setOnClickListener(v ->
                tvWelcome.setText("Em breve: Tela de Configurações!")
        );
    }
}
