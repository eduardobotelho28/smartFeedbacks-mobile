package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    private CheckBox cbVerNps, cbVerCsat;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        cbVerNps = findViewById(R.id.cbVerNps);
        cbVerCsat = findViewById(R.id.cbVerCsat);
        btnSalvar = findViewById(R.id.btnSalvarConfigs);

        // MOCK inicial: tudo marcado
        cbVerNps.setChecked(true);
        cbVerCsat.setChecked(true);

        btnSalvar.setOnClickListener(v -> {
            boolean verNps = cbVerNps.isChecked();
            boolean verCsat = cbVerCsat.isChecked();

            // Aqui futuramente salvaremos em SharedPreferences ou via API
            // Exemplo (SharedPreferences):
            // SharedPreferences prefs = getSharedPreferences("configs", MODE_PRIVATE);
            // prefs.edit().putBoolean("verNps", verNps).putBoolean("verCsat", verCsat).apply();

            Toast.makeText(this, "Preferências salvas (mock)", Toast.LENGTH_SHORT).show();
            finish(); // fecha a tela após salvar
        });
    }
}
