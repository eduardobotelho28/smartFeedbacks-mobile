package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

        // ---------- PEGANDO OS DADOS DO SHARED ----------
        SharedPreferences prefs = getSharedPreferences("SmartPrefs", MODE_PRIVATE);

        int userId = prefs.getInt("user_id", -1);
        String token = prefs.getString("token", "Token não encontrado");

        // Por enquanto exibimos só para debug:
        tvWelcome.setText(
                "User ID: " + userId + "\n" +
                        "Token: " + token
        );

        // ---------- MOCK DAS MÉTRICAS ----------
        tvNps.setText("NPS Atual: 72");
        tvCsat.setText("CSAT Atual: 89%");


        // ---------- NAVEGAÇÃO ----------
        btnFeedbacks.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FeedbacksActivity.class);
            startActivity(intent);
        });

        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, UserActivity.class);
            startActivity(intent);
        });

        btnConfig.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ConfigActivity.class);
            startActivity(intent);
        });
    }
}
