package com.example.loginapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UsersActivity extends AppCompatActivity {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/users";

    private Button btnActualizarUsuarios;
    private TextView tvEstadoUsuarios;
    private TextView tvUsuarios;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        btnActualizarUsuarios = findViewById(R.id.btnActualizarUsuarios);
        tvEstadoUsuarios = findViewById(R.id.tvEstadoUsuarios);
        tvUsuarios = findViewById(R.id.tvUsuarios);

        btnActualizarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarUsuarios();
            }
        });

        cargarUsuarios();
    }

    private void cargarUsuarios() {
        btnActualizarUsuarios.setEnabled(false);
        tvEstadoUsuarios.setText(R.string.cargando_usuarios);
        tvUsuarios.setText("");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String respuesta = consumirServicio();
                    String usuariosFormateados = formatearUsuarios(respuesta);
                    mostrarResultado("Usuarios cargados correctamente desde JSONPlaceholder.", usuariosFormateados);
                } catch (Exception e) {
                    mostrarResultado("Error al consumir JSON: " + e.getMessage(), "");
                }
            }
        }).start();
    }

    private String consumirServicio() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setConnectTimeout(10000);
        conexion.setReadTimeout(10000);

        int codigoRespuesta = conexion.getResponseCode();
        if (codigoRespuesta != HttpURLConnection.HTTP_OK) {
            throw new Exception("codigo HTTP " + codigoRespuesta);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        StringBuilder respuesta = new StringBuilder();
        String linea;

        while ((linea = reader.readLine()) != null) {
            respuesta.append(linea);
        }

        reader.close();
        conexion.disconnect();
        return respuesta.toString();
    }

    private String formatearUsuarios(String json) throws Exception {
        JSONArray usuarios = new JSONArray(json);
        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < usuarios.length(); i++) {
            JSONObject usuario = usuarios.getJSONObject(i);
            JSONObject direccion = usuario.getJSONObject("address");
            JSONObject empresa = usuario.getJSONObject("company");

            texto.append(i + 1).append(". ").append(usuario.getString("name")).append("\n");
            texto.append("Correo: ").append(usuario.getString("email")).append("\n");
            texto.append("Ciudad: ").append(direccion.getString("city")).append("\n");
            texto.append("Empresa: ").append(empresa.getString("name")).append("\n\n");
        }

        return texto.toString();
    }

    private void mostrarResultado(final String estado, final String usuarios) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                tvEstadoUsuarios.setText(estado);
                tvUsuarios.setText(usuarios);
                btnActualizarUsuarios.setEnabled(true);
            }
        });
    }
}
