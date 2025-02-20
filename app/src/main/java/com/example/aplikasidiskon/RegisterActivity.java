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

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etPassword, etConfirmPassword;
    Button btnRegister;
    TextView redLogin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.signUpEmail);
        etPassword = findViewById(R.id.signUpPassword);
        etConfirmPassword = findViewById(R.id.signUpConfirmPassword);
        btnRegister = findViewById(R.id.registerButton);
        redLogin = findViewById(R.id.loginRedirectText);

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);

        btnRegister.setOnClickListener(v -> registerUser());

        redLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void registerUser() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill the blank fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONObject userObject = new JSONObject();
            userObject.put("email", email);
            userObject.put("password", password);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user", userObject.toString());
            editor.apply();

            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to register user", Toast.LENGTH_SHORT).show();
        }
    }
}