package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome, tvNps, tvCsat, tvStars, tvFormsCount;
    private Button btnFeedbacks, btnPerfil, btnConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Bind
        tvWelcome    = findViewById(R.id.tvWelcomeHome);
        tvNps        = findViewById(R.id.tvNps);
        tvCsat       = findViewById(R.id.tvCsat);
        tvStars      = findViewById(R.id.tvStars);
        tvFormsCount = findViewById(R.id.tvFormsCount);

        btnFeedbacks = findViewById(R.id.btnFeedbacks);
        btnPerfil    = findViewById(R.id.btnPerfil);
        btnConfig    = findViewById(R.id.btnConfig);

        // ---------- PEGAR TOKEN E USER_ID ----------
        SharedPreferences prefs = getSharedPreferences("SmartPrefs", MODE_PRIVATE);

        String token = prefs.getString("token", "");
        int userId   = prefs.getInt("user_id", -1);

        if (token.isEmpty() || userId == -1) {
            Toast.makeText(this, "Erro: usuário não autenticado.", Toast.LENGTH_LONG).show();
            return;
        }

        // Preferências de visualização
        boolean verNps = prefs.getBoolean("verNps", true);
        boolean verCsat = prefs.getBoolean("verCsat", true);

        // Oculta se necessário
        tvNps.setVisibility(verNps ? View.VISIBLE : View.GONE);
        tvCsat.setVisibility(verCsat ? View.VISIBLE : View.GONE);

        tvWelcome.setText("Carregando dados...");

        // ============= CHAMADA REAL PARA A API =============
        Call<UserInfo> call = RetrofitClient.getInstance()
                .getMyApi()
                .getUserInfo("Bearer " + token, userId);

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(HomeActivity.this,
                            "Erro ao carregar dados. Código: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    tvWelcome.setText("Erro ao carregar dados.");
                    return;
                }

                UserInfo user = response.body();

                if (!"ok".equals(user.getStatus())) {
                    Toast.makeText(HomeActivity.this,
                            "Erro: status inválido",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // ============= SUCESSO =============
                tvWelcome.setText("Bem-vindo, usuário #" + user.getUserId());

                if (verNps) tvNps.setText("NPS Atual: " + user.getNps());
                if (verCsat) tvCsat.setText("CSAT Atual: " + user.getCsat() + "%");

                tvStars.setText("Avaliação (Stars): " + user.getStars() + " ★");
                tvFormsCount.setText("Formulários Criados: " + user.getFormsCount());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Toast.makeText(HomeActivity.this,
                        "Erro de rede: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
                tvWelcome.setText("Falha ao comunicar com servidor.");
            }
        });

        // ---------- NAVEGAÇÃO ----------
        btnFeedbacks.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, FeedbacksActivity.class));
        });

        btnPerfil.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, UserActivity.class));
        });

        btnConfig.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, ConfigActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("SmartPrefs", MODE_PRIVATE);
        boolean verNps = prefs.getBoolean("verNps", true);
        boolean verCsat = prefs.getBoolean("verCsat", true);

        tvNps.setVisibility(verNps ? View.VISIBLE : View.GONE);
        tvCsat.setVisibility(verCsat ? View.VISIBLE : View.GONE);
    }
}
