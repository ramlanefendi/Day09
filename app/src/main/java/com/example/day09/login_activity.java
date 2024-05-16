package com.example.day09;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull; // Tambahkan impor ini

import com.example.day09.Api.ApiClient;
import com.example.day09.Api.Apiinterface;
import com.example.day09.Model.Login.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_activity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvCa;
    String username, password;
    Apiinterface apiinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnlogin);
        tvCa = findViewById(R.id.tvCA);

        tvCa.setOnClickListener(v -> {
            Intent intentRegister = new Intent(login_activity.this, register_activity.class);
            startActivity(intentRegister);
        });

        btnLogin.setOnClickListener(v -> {
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            login(username, password);
        });
    }

    private void login(@NonNull String username, @NonNull String password) {
        apiinterface = ApiClient.getClient().create(Apiinterface.class);
        Call<Login> loginCall = apiinterface.loginResponse(username, password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    Toast.makeText(login_activity.this, response.body().getData().getName(), Toast.LENGTH_SHORT).show();
                    Intent intentTologin = new Intent(login_activity.this, MainActivity.class);
                    startActivity(intentTologin);
                    finish();
                } else {
                    Toast.makeText(login_activity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                Toast.makeText(login_activity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}