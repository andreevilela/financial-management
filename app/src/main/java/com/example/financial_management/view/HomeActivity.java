package com.example.financial_management.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.financial_management.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void btnCadastro(View view) {
        Intent intent = new Intent(this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}