package com.example.financial_management.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.financial_management.R;
import com.example.financial_management.model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnCadastrar;
    EditText editTextNome;
    EditText editTextDataNascimento;
    EditText editTextEmail;
    EditText editTextSenha;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        token = "fabricadedesenvolvedor";

        editTextNome = findViewById(R.id.editTextNome);
        editTextDataNascimento = findViewById(R.id.editTextDataNascimento);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
    }

    public void btnCadastrar(View view) {
        Usuario obj = new Usuario();
        obj.setNome(editTextNome.getText().toString());
        obj.setData_nascimento(editTextDataNascimento.getText().toString());
        obj.setEmail(editTextEmail.getText().toString());
        obj.setSenha(editTextSenha.getText().toString());

        IncluirUsuarioAsyncTask task = new IncluirUsuarioAsyncTask(token, obj);
        task.execute();
    }

    public class IncluirUsuarioAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = "http://financial-management.000webhostapp.com/APIIncluirUsuario.php";

        final int READ_TIMEOUT = 10000;
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;

        //CONSTRUTOR RECEBE UM OBJETO USUARIO
        public IncluirUsuarioAsyncTask(String token, Usuario usuario) {

            this.api_token = token;
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_token", api_token);
            builder.appendQueryParameter("api_nome", String.valueOf(usuario.getNome()));
            builder.appendQueryParameter("api_data_nascimento", String.valueOf(usuario.getData_nascimento()));
            builder.appendQueryParameter("api_email", String.valueOf(usuario.getEmail()));
            builder.appendQueryParameter("api_senha", String.valueOf(usuario.getSenha()));
        }

        @Override
        protected void onPreExecute() {
            Log.i("APIIncluirUsuario", "onPreExecute()");
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.i("APIIncluirUsuario", "doInBackground()01");

            //GERAR O CONTEUDO PARA URL
            try {
                url = new URL(URL_WEB_SERVICES);
            } catch (MalformedURLException e) {
                Log.i("APIIncluirUsuario", "doInBackground()02 --> " + e.getMessage());
            } catch (Exception e) {
                Log.i("APIIncluirUsuario", "doInBackground()03 --> " + e.getMessage());
            }

            //GERAR UMA REQUSICAO HTTP - POST - RESULT SERA UM ARRAY JSON
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset", "utf-8");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();

            } catch (Exception e) {
                Log.i("APIIncluirUsuario", "HttpURLConnection --> " + e.getMessage());
            }

            //ADICIONAR O TOKEN E/OU  OUTROS PARAMETROS COM POR EXEMPLO
            //UM OBJETO A SER INCLUIDO, DELETADO OU ALTERADO
            //CRUD COMPLETO
            try {
                query = builder.build().getEncodedQuery();
                OutputStream stream = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream, "utf-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                stream.close();

                conn.connect();
            } catch (Exception e) {
                Log.i("APIIncluirUsuario", "BufferedWriter --> " + e.getMessage());
            }

            //RECEBER O RESPONSE - ARRAY JSON
            // HTTP - CODIGO DO RESPONSE | 200 | 404 | 503
            try {
                response_code = conn.getResponseCode();
                if(response_code == HttpURLConnection.HTTP_OK) {
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    StringBuilder result = new StringBuilder();
                    String linha = null;

                    while ((linha = reader.readLine()) != null) {
                        result.append(linha);
                    }

                    return  result.toString();
                } else {
                    return "HTTP ERRO: " + response_code;
                }
            } catch (Exception e) {
                Log.i("APIIncluirUsuario", "StringBuilder --> " + e.getMessage());
            } finally {
                conn.disconnect();
            }
            return "Processado com sucesso...";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("APIIncluirUsuario", "onPosExecute()01 --> Result: " + result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                if(jsonObject.getBoolean("adicionado")) {
                    Toast toast = Toast.makeText(getBaseContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 250);
                    toast.show();
                    Log.i("APIIncluirUsuario", "onPosExecute()02 --> Adicionado com Sucesso");
                } else {
                    Toast toast = Toast.makeText(getBaseContext(), "Falha ao cadastrar", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 250);
                    toast.show();
                    Log.i("APIIncluirUsuario", "onPosExecute()03 --> Falha ao casdastar usuario");
                    Log.i("APIIncluirUsuario", "onPosExecute()04 --> " + jsonObject.getString("SQL"));
                }

            } catch (Exception e) {
                Log.i("APIIncluirUsuario", "onPosExecute()05 --> " + e.getMessage());
            }
        }
    }

}