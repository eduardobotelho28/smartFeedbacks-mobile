package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail    = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin   = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String senha = etPassword.getText().toString().trim();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cria objeto de envio
            UserLogin loginRequest = new UserLogin(email, senha);

            // Chamada Retrofit
            Call<UserLogin> call = RetrofitClient
                    .getInstance()
                    .getMyApi()
                    .login(loginRequest);

            call.enqueue(new Callback<UserLogin>() {
                @Override
                public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {

                    UserLogin resp;

                    if (response.body() != null) {
                        // API retornou JSON normal
                        resp = response.body();
                    }
                    else {
                        // API retornou erro (401, 400, etc) mas com JSON dentro de errorBody
                        try {
                            String json = response.errorBody().string();
                            resp = new Gson().fromJson(json, UserLogin.class);
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this,
                                    "Erro inesperado no servidor.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    if (resp == null) {
                        Toast.makeText(MainActivity.this,
                                "Erro inesperado. Sem resposta da API.",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Agora temos o JSON DA API mesmo com status 401
                    if (!resp.isSuccess()) {
                        Toast.makeText(MainActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // SUCESSO
                    getSharedPreferences("SmartPrefs", MODE_PRIVATE)
                            .edit()
                            .putString("token", resp.getToken())
                            .putInt("user_id",
                                    resp.getUserId() != null ? resp.getUserId() : -1)
                            .apply();

                    Toast.makeText(MainActivity.this,
                            "Login realizado com sucesso!",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("nomeUsuario", "Usuário");
                    startActivity(intent);
                    finish();
                }


                @Override
                public void onFailure(Call<UserLogin> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            "Falha na conexão: " + t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });

        });
    }
}
