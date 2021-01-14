package com.example.financial_management.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.financial_management.R;
import com.example.financial_management.controller.Usuario;

public class UsuarioActivity extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextDataNascimento;
    EditText editTextEmail;
    EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        editTextNome = findViewById(R.id.editTextNome);
        editTextDataNascimento = findViewById(R.id.editTextDataNascimento);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
    }

    public void btnCadastraUsuario(View view) {
        Usuario obj = new Usuario();
        obj.setName(editTextNome.getText().toString());
        obj.setData_nascimento(editTextDataNascimento.getText().toString());
        obj.setEmail(editTextEmail.getText().toString());
        obj.setSenha(editTextSenha.getText().toString());

        Toast toast = Toast.makeText(getBaseContext(), "Cadastra", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 250);
        toast.show();
    }



}