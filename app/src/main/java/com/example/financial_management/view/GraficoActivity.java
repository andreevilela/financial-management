package com.example.financial_management.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.financial_management.R;
import com.example.financial_management.controller.Saida;
import com.example.financial_management.model.RetrofitService;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraficoActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(this);

        addItemsOnSpinner();
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

        spinner = (Spinner) findViewById(R.id.spinnerGrafico);
        List<String> list = new ArrayList<String>();
        list.add("OUTUBRO - 20");
        list.add("NOVEMBRO - 20");
        list.add("DEZEMBRO - 20");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void grafico(List<Saida> lista) {
        PieChart pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> categorias = new ArrayList<>();
        /*categorias.add(new PieEntry(600, "Mercado"));
        categorias.add(new PieEntry(100, "Transporte"));
        categorias.add(new PieEntry(180, "TV / Internet / Telefone"));
        categorias.add(new PieEntry(220, "Contas residenciais"));
        categorias.add(new PieEntry(90, "Bares / Restaurantes"));*/

        for (Saida out : lista) {
            categorias.add(new PieEntry((float) out.getValor(), out.getCategoryOutName()));
        }

        PieDataSet pieDataSet = new PieDataSet(categorias, "Despesas");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Despesas");
        pieChart.animate();
    }

    private void buscarDados() {
        RetrofitService.getServico().obterSaidas().enqueue(new Callback<List<Saida>>() {
            @Override
            public void onResponse(Call<List<Saida>> call, Response<List<Saida>> response) {
                List<Saida> lista = response.body();
                grafico(lista);
                /*for (Saida out : lista) {
                    campo.append("\n\nCategoria: "+out.getCategoryOutName()+
                            "\nDescrição: "+out.getDescricao()+
                            "\nData: "+out.getDataFormat()+
                            "\nValor: "+out.getValor());
                }*/
            }

            @Override
            public void onFailure(Call<List<Saida>> call, Throwable t) {
                Log.e("ResApp", t.getStackTrace().toString());
            }
        });
    }

    public void despesaRemuneracao(View view) {
        Toast toast = Toast.makeText(getBaseContext(), "Despesas / Remunerações", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 250);
        toast.show();
    }

}