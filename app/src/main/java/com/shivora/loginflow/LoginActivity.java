package com.shivora.loginflow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shivora.loginflow.util.UsersUtil;

public class LoginActivity extends AppCompatActivity {

    public static final String KEY_SAVED_USER_ID = "saved_user_id";
    TextView tvRegister,tvForgotPassword;
    Button btnLogin;
    EditText etEmail,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).getInt(KEY_SAVED_USER_ID,0)>0){
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }

        tvRegister = findViewById(R.id.tv_register);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        btnLogin = findViewById(R.id.btn_login);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrationActivity = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(registrationActivity);
                finish();
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPasswordActivity = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(forgotPasswordActivity);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (!TextUtils.isEmpty(email)&& !TextUtils.isEmpty(password)){int userId = UsersUtil.loginUser(LoginActivity.this,email,password);
                    if (userId>0) {
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt(KEY_SAVED_USER_ID,userId);
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        showSnackbar(LoginActivity.this,"Authentication Failed!");
                    }
                }
                else{
                    showSnackbar(LoginActivity.this,"Please fill both email and password!");
                }
            }
        });
    }

    public static void showSnackbar(Activity context, String msg){
        View view = context.findViewById(android.R.id.content);
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
    }
}
