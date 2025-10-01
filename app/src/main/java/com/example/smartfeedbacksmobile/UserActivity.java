package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    private EditText etNome, etEmail;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        btnSalvar = findViewById(R.id.btnSalvar);

        // MOCK: preencher com dados de usuário (mock)
        etNome.setText("Eduardo Botelho");
        etEmail.setText("eduardo@example.com");

        // Clique no botão salvar (por enquanto só mock)
        btnSalvar.setOnClickListener(v ->
                Toast.makeText(this, "Dados salvos (mock)!", Toast.LENGTH_SHORT).show()
        );
    }
}
