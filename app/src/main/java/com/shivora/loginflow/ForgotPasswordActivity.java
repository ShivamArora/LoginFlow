package com.shivora.loginflow;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shivora.loginflow.util.UsersUtil;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText etEmail,etSecretKey;
    TextView tvStatus;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = findViewById(R.id.et_email);
        etSecretKey = findViewById(R.id.et_secret_code);
        tvStatus = findViewById(R.id.tv_status);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvStatus.setText("");

                String email = etEmail.getText().toString();
                String secretKey = etSecretKey.getText().toString();

                boolean secretKeyVerified = UsersUtil.verifySecretKey(ForgotPasswordActivity.this,email,secretKey);
                if (secretKeyVerified){
                    String password = UsersUtil.getPassword(ForgotPasswordActivity.this,email);
                    tvStatus.setText("Your password is: "+password);
                }
                else{
                    View rootView = findViewById(android.R.id.content);
                    Snackbar.make(rootView,"Incorrect email or secret key",Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}
