package com.example.financial_management.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.financial_management.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class DespesaActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(this);

        addItemsOnSpinner();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_historico: {
                Intent intent = new Intent(this, HistoricoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.navigation_grafico: {
                Intent intent = new Intent(this, GraficoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.navigation_entrada: {
                Intent intent = new Intent(this, EntradaActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.navigation_despesa: {
                Intent intent = new Intent(this, DespesaActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.navigation_conta: {
                Intent intent = new Intent(this, AlteraUsuarioActivity.class);
                startActivity(intent);
                break;
            }
        }

        return true;
    }

    public void addItemsOnSpinner() {

        spinner = (Spinner) findViewById(R.id.spinnerDespesa);
        List<String> list = new ArrayList<String>();
        list.add("Selecione uma categoria");
        list.add("Bares / restaurantes");
        list.add("Compras");
        list.add("Contas residenciais");
        list.add("Educação");
        list.add("Lazer");
        list.add("Mercado");
        list.add("Moradia");
        list.add("Presentes");
        list.add("Saúde");
        list.add("Serviços");
        list.add("Taxas bancarias");
        list.add("Transporte");
        list.add("TV / Internet / Telefones");
        list.add("Viagem");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void btnSalvarDespesa(View view) {
        Toast.makeText(getApplicationContext(), "Despesas", Toast.LENGTH_SHORT).show();
    }
}