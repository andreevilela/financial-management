package com.example.financial_management.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.financial_management.R;

public class DespesaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);
    }

    public void btnSalvarDespesa(View view) {
        Toast.makeText(getApplicationContext(), "Despesa", Toast.LENGTH_SHORT).show();
    }
}