package com.example.day09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day09.Api.ApiClient;
import com.example.day09.Api.Apiinterface;
import com.example.day09.Model.Register.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register_activity extends AppCompatActivity {

    EditText etregisterusername, etregistername,etregisterpassword;

    TextView tvLog;

    String username, password,name;

    Button btnRegister;

    Apiinterface apiinterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);

        etregisterusername=findViewById (R.id.etRegisterUsername);
        etregistername=findViewById (R.id.etRegisterName);
        etregisterpassword=findViewById (R.id.etRegisterPassword);
        btnRegister = findViewById (R.id.btnRegister);
        tvLog =findViewById (R.id.tvLog);

        tvLog.setOnClickListener (v -> {
            Intent intentRegister = new Intent (register_activity.this,login_activity.class);
            startActivity (intentRegister);

        });

        btnRegister.setOnClickListener (v -> {
            username = etregisterusername.getText ().toString ();
            password = etregisterpassword.getText ().toString ();
            name = etregistername.getText ().toString ();
            Register(username,password,name);
        });
        }

        private void Register(@NonNull String username, @NonNull String password, @NonNull String name){
        apiinterface = ApiClient.getClient ().create (Apiinterface.class);
            Call<Register> registerCall=apiinterface.registerResponse (username, password, name);
            registerCall.enqueue (new Callback<Register> () {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                        Toast.makeText (register_activity.this, response.body ().getData ().getName (), Toast.LENGTH_SHORT).show ();
                        Intent intentToregister = new Intent (register_activity.this, MainActivity.class);
                        startActivity (intentToregister);
                        finish ();
                    }else {
                        Toast.makeText (register_activity.this, "Register failed", Toast.LENGTH_SHORT).show ();
                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {
                    Toast.makeText (register_activity.this, t.getLocalizedMessage (), Toast.LENGTH_SHORT).show ();

                }
            });

        }
        }

