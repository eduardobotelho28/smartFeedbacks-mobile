package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
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

        SharedPreferences prefs = getSharedPreferences("SmartPrefs", MODE_PRIVATE);

        boolean verNps = prefs.getBoolean("verNps", true);
        boolean verCsat = prefs.getBoolean("verCsat", true);

        cbVerNps.setChecked(verNps);
        cbVerCsat.setChecked(verCsat);

        btnSalvar.setOnClickListener(v -> {
            boolean novoVerNps = cbVerNps.isChecked();
            boolean novoVerCsat = cbVerCsat.isChecked();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("verNps", novoVerNps);
            editor.putBoolean("verCsat", novoVerCsat);
            editor.apply();

            Toast.makeText(this, "Preferências salvas com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
