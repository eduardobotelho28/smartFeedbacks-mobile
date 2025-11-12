package com.example.smartfeedbacksmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    private EditText etNome, etSobrenome, etEmail;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        etNome      = findViewById(R.id.etNome);
        etSobrenome = findViewById(R.id.etSobrenome);
        etEmail     = findViewById(R.id.etEmail);
        btnSalvar   = findViewById(R.id.btnSalvar);

        SharedPreferences prefs = getSharedPreferences("SmartPrefs", MODE_PRIVATE);

        String firstname = prefs.getString("firstname", "");
        String lastname  = prefs.getString("lastname", "");
        String email     = prefs.getString("email", "");
        int userId       = prefs.getInt("user_id", -1);
        String token     = prefs.getString("token", "");

        etNome.setText(firstname);
        etSobrenome.setText(lastname);
        etEmail.setText(email);

        btnSalvar.setOnClickListener(v -> {

            String newFirst = etNome.getText().toString().trim();
            String newLast  = etSobrenome.getText().toString().trim();
            String newEmail = etEmail.getText().toString().trim();

            if (newFirst.isEmpty() || newLast.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            UserUpdateRequest body = new UserUpdateRequest(newFirst, newLast, newEmail);

            Call<UserUpdateResponse> call = RetrofitClient.getInstance()
                    .getMyApi()
                    .updateUser("Bearer " + token, userId, body);

            call.enqueue(new Callback<UserUpdateResponse>() {
                @Override
                public void onResponse(Call<UserUpdateResponse> call, Response<UserUpdateResponse> response) {


                    if (!response.isSuccessful()) {

                        try {
                            String json = response.errorBody().string();

                            JsonObject obj = new Gson().fromJson(json, JsonObject.class);


                            if (obj.has("errors")) {
                                JsonObject errors = obj.getAsJsonObject("errors");

                                StringBuilder msg = new StringBuilder();

                                if (errors.has("firstname"))
                                    msg.append(errors.get("firstname").getAsString()).append("\n");

                                if (errors.has("lastname"))
                                    msg.append(errors.get("lastname").getAsString()).append("\n");

                                if (errors.has("email"))
                                    msg.append(errors.get("email").getAsString()).append("\n");

                                Toast.makeText(UserActivity.this, msg.toString(), Toast.LENGTH_LONG).show();
                                return;
                            }


                            if (obj.has("message")) {
                                Toast.makeText(UserActivity.this,
                                        obj.get("message").getAsString(),
                                        Toast.LENGTH_LONG).show();
                                return;
                            }

                        } catch (Exception e) {
                            Toast.makeText(UserActivity.this,
                                    "Erro inesperado ao processar retorno.",
                                    Toast.LENGTH_LONG).show();
                        }

                        return;
                    }

                    UserUpdateResponse resp = response.body();

                    if (resp == null) {
                        Toast.makeText(UserActivity.this,
                                "Erro: resposta vazia.",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if ("ok".equals(resp.getStatus())) {

                        Toast.makeText(UserActivity.this,
                                resp.getMessage(),
                                Toast.LENGTH_SHORT).show();

                        // Atualiza localmente
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("firstname", newFirst);
                        editor.putString("lastname", newLast);
                        editor.putString("email", newEmail);
                        editor.apply();

                        // Retorna pra home
                        Intent intent = new Intent(UserActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<UserUpdateResponse> call, Throwable t) {
                    Toast.makeText(UserActivity.this,
                            "Erro de rede: " + t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
