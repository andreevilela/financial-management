package com.example.financial_management.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Saida {

    private Long id;
    private String descricao;
    private Date data;
    private double valor;
    private String categoryOutName;

    public Saida() {
    }

    public Saida(Long id, String descricao, Date data, double valor, String categoryOutName) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.categoryOutName = categoryOutName;
    }

    public String getDataFormat() {
        SimpleDateFormat fdata = new SimpleDateFormat("dd/MM/yyyy");
        return fdata.format(data);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoryOutName() {
        return categoryOutName;
    }

    public void setCategoryOutName(String categoryOutName) {
        this.categoryOutName = categoryOutName;
    }
}
