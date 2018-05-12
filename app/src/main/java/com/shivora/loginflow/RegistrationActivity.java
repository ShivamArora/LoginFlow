package com.shivora.loginflow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shivora.loginflow.util.UsersUtil;

public class RegistrationActivity extends AppCompatActivity {

    EditText etName,etEmail,etPhone,etPassword,etSecretKey;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etName = findViewById(R.id.et_person_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        etSecretKey = findViewById(R.id.et_secret_code);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String password = etPassword.getText().toString();
                String secretKey = etSecretKey.getText().toString();

                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(secretKey)) {
                    boolean success = UsersUtil.registerUser(RegistrationActivity.this, name, email, phone, password, secretKey);
                    if (success) {
                        Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_LONG).show();
                        Intent loginActivity = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(loginActivity);
                        finish();
                    }
                    else{
                        LoginActivity.showSnackbar(RegistrationActivity.this,"Failed to register user with given details!");
                    }
                }
                else{
                    LoginActivity.showSnackbar(RegistrationActivity.this,"Please fill all the fields!");
                }
            }
        });

    }
}
