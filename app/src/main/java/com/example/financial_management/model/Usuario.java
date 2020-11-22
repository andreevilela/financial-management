package com.example.financial_management.model;

public class Usuario {

    private int id;
    private String nome;
    private String data_nascimento;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id, String nome, String data_nascimento, String email, String senha) {
        this.setId(id);
        this.setNome(nome);
        this.setData_nascimento(data_nascimento);
        this.setEmail(email);
        this.setSenha(senha);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
