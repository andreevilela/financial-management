package com.example.financial_management.view;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.financial_management.R;

public class EntradaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);
    }

    public void btnSalvaEntrada(View view) {
        Toast.makeText(getApplicationContext(), "Remunerações", Toast.LENGTH_SHORT).show();
    }
}