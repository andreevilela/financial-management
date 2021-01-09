package com.example.financial_management.model;

public class Usuario {

    private int id;
    private String name;
    private String data_nascimento;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id, String name, String data_nascimento, String email, String senha) {
        this.setId(id);
        this.setName(name);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
