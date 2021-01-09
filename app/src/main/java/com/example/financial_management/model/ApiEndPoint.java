package com.example.financial_management.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {

        @GET("users")
        Call<List<Usuario>> obterUsuarios();


}
