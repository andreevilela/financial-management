package com.example.financial_management.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.financial_management.R;
import com.example.financial_management.model.RetrofitService;
import com.example.financial_management.controller.Saida;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoricoActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private Spinner spinner;
    private TextView campo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(this);

        addItemsOnSpinner();
        campo = findViewById(R.id.historico);
        buscarDados();

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

        spinner = (Spinner) findViewById(R.id.spinnerHistorico);
        List<String> list = new ArrayList<String>();
        list.add("OUTUBRO - 20");
        list.add("NOVEMBRO - 20");
        list.add("DEZEMBRO - 20");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void buscarDados() {
        RetrofitService.getServico().obterSaidas().enqueue(new Callback<List<Saida>>() {
            @Override
            public void onResponse(Call<List<Saida>> call, Response<List<Saida>> response) {
                List<Saida> lista = response.body();
                for (Saida out : lista) {
                    campo.append("\n\nCategoria: "+out.getCategoryOutName()+
                            "\nDescrição: "+out.getDescricao()+
                            "\nData: "+out.getDataFormat()+
                            "\nValor: "+out.getValor());
                }
            }

            @Override
            public void onFailure(Call<List<Saida>> call, Throwable t) {
                Log.e("ResApp", t.getStackTrace().toString());
            }
        });
    }

}