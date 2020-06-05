package io.agora.openlive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.agora.openlive.R;
import io.agora.openlive.utils.PrefManager;

public class LoginActivity extends AppCompatActivity {

    EditText count;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        count = findViewById(R.id.count);
        password = findViewById(R.id.password);
        count.setText(PrefManager.get(this, "count"));
        password.setText(PrefManager.get(this, "password"));
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count.getText().toString().equals("12345") && password.getText().toString().equals("admin")) {
                    PrefManager.put(LoginActivity.this, "count", count.getText().toString());
                    PrefManager.put(LoginActivity.this, "password", password.getText().toString());

                    startActivity(new Intent(LoginActivity.this, AppMainActivity.class));
                    finish();                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
