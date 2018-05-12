package com.shivora.loginflow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.shivora.loginflow.util.User;
import com.shivora.loginflow.util.UsersUtil;

public class HomeActivity extends AppCompatActivity {

    TextView tvName,tvEmail,tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPhone);

        int userId = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this).getInt(LoginActivity.KEY_SAVED_USER_ID,0);

        User user = UsersUtil.getUserDetails(HomeActivity.this,userId);

        if (user!=null){
            tvName.setText(user.getName());
            tvEmail.setText(user.getEmail());
            tvPassword.setText(user.getPhone());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Toast.makeText(HomeActivity.this,"Logout Successful!",Toast.LENGTH_LONG).show();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(LoginActivity.KEY_SAVED_USER_ID,0);
        editor.apply();

        Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
