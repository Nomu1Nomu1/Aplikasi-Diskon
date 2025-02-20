package com.example.aplikasidiskon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView redRegister;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.signInEmail);
        etPassword = findViewById(R.id.signInPassword);
        btnLogin = findViewById(R.id.signInButton);
        redRegister = findViewById(R.id.registerRedirectText);

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);

        btnLogin.setOnClickListener(v -> loginUser());

        redRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill the blank fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String userJson = sharedPreferences.getString("user", null);
        if (userJson == null) {
            Toast.makeText(this, "User not found. Please register first", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            JSONObject userObject = new JSONObject(userJson);
            String emailUser = userObject.getString("email");
            String passwordUser = userObject.getString("password");

            if (email.equals(emailUser) && password.equals(passwordUser)) {
                Intent intent = new Intent(LoginActivity.this, Diskon.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Email or password is incorrect", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error retreiving data", Toast.LENGTH_SHORT).show();
        }
    }
}