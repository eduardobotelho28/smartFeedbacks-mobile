package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String senha = etPassword.getText().toString();

            if (!email.isEmpty() && !senha.isEmpty()) {

                // ==============================
                // FUTURAMENTE: chamada API Login
                // 1. Enviar email/senha via Retrofit
                // 2. Receber JWT de autenticação
                // 3. Guardar token em SharedPreferences
                // 4. Buscar dados do usuário autenticado
                // ==============================

                // MOCK: redirecionar direto para Home
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("nomeUsuario", "Eduardo Botelho"); // mock
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
