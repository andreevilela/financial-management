package com.example.financial_management.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.financial_management.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btnEntrar(View view) {
        Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
    }
}