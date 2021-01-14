package com.example.financial_management.model;

import com.example.financial_management.controller.Saida;
import com.example.financial_management.controller.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {

        @GET("users")
        Call<List<Usuario>> obterUsuarios();

        @GET("outputs")
        Call<List<Saida>> obterSaidas();
}
